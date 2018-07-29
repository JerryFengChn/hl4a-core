package 间.后端;

import 间.后端.条件.I条件;
import 间.后端.类型.指针数据;
import 间.工具.字符;
import 间.工具.断言;
import 间.工具.错误;
import 间.接口.返回值;
import 间.收集.哈希表;
import 间.收集.哈希集;
import 间.收集.有序列表;

import java.util.List;
import java.util.Map;

@SuppressWarnings({"unchecked"})
public class 查询<内容 extends 数据> extends 哈希表 {

	private String 类名;
	private Class<? extends 数据> 类;

	public 查询(String _类名) {

		断言.不为空(_类名, "类名为空");
		类名 = _类名;
	}

	public 查询(String _类名, Class<内容> _类) {

		this(_类名);
		置类型(_类);
	}

	public 查询<内容> 置类型(Class<内容> _类) {

		类 = _类;
		return this;
	}

	public 查询<内容> 跳过(int _数量) {

		设置("skip", _数量);
		return this;
	}

	public 查询<内容> 截止(int _数量) {

		if (_数量 < 0) {
			错误.参数("截止数量不能小于0");
		} else if (_数量 > 1000) {
			错误.参数("截止数量不能大于1000");
		}
		设置("limit", _数量);
		return this;
	}

	public 查询<内容> 条件(I条件 _条件) {

		设置("where", _条件.toString());
		return this;
	}

	public 查询<内容> 包括键值(String... _键值) {

		设置("keys", new 哈希集<>(_键值));
		return this;
	}

	public 查询<内容> 包括内容(String... _键值) {
		设置("include", 字符.分解(_键值, ","));
		return this;
	}

	public 返回值<有序列表<数据>> 同步查询() {

		返回值<List<Map>> _返回 = (返回值) 后端.Companion.取当前后端().同步查询对象(类名, this);
		if (!_返回.成功()) return (返回值) _返回;
		有序列表<数据> _列表 = new 有序列表<>();

		for (Map _单个 : _返回.取内容()) {
			_单个.put("className", 类名);
			_列表.添加(new 指针数据(_单个).读取());
		}

		return ((返回值) _返回).置内容(_列表);

	}

}
