package 间.接口;

import 间.工具.处理;
import 间.收集.哈希表;
import 间.方法.函数;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Map;

public class 动态接口 implements InvocationHandler {

	private 哈希表<String, 函数> 函数表;
	private 函数 通用;

	public 动态接口(Map<String, 函数> _列表) {

		函数表 = new 哈希表<>(_列表);
	}

	public 动态接口(函数 _函数) {

		函数表 = new 哈希表<>();
		通用 = _函数;
	}

	public Object 代理(Class<?> _类) {

		return Proxy.newProxyInstance(_类.getClassLoader(), _类.getInterfaces(), this);
	}

	@Override
	public Object invoke(Object _对象, Method _函数, Object[] _参数) {

		函数 _内容 = 函数表.读取(_函数.getName());

		if (_内容 == null) return 处理.同步(通用, _参数);

		return 处理.同步(_内容, _参数);

	}

}
