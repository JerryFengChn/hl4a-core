package 间.工具;

import 间.方法.函数;

public enum 调用 {
	;

	public static 函数 代理(Object _对象, String _名称) {

		return 反射.代理(_对象, _名称);
	}

	public static 函数 配置(Object _对象, String _名称, Object... _参数) {

		return 反射.配置(_对象, _名称, _参数);
	}

	public static void 直接(Object _对象, String _名称, Object... _参数) {

		处理.同步(配置(_对象, _名称, _参数));
	}

	public static void 同步(Object _对象, String _名称, Object... _参数) {

		处理.同步(配置(_对象, _名称, _参数));
	}

	public static void 异步(Object _对象, String _名称, Object... _参数) {

		处理.异步(配置(_对象, _名称, _参数));
	}

	public static void 线程(Object _对象, String _名称, Object... _参数) {

		处理.线程(配置(_对象, _名称, _参数));
	}

	public static void 线程池(线程池 _线程池, Object _对象, String _名称, Object... _参数) {

		_线程池.执行(配置(_对象, _名称, _参数));
	}

}
