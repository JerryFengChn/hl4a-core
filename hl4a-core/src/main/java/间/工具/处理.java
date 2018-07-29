package 间.工具;

import 间.方法.函数;

public class 处理 {

	public static Object 同步(函数 _方法, Object... _参数) {
		if (_方法 == null) return null;
		return _方法.调用(_参数);
	}

	public static void 异步(函数 _方法, Object... _参数) {
		异步(null, _方法, _参数);
	}

	public static void 异步(线程池 _线程池, 函数 _方法, Object... _参数) {
		线程池.执行(_线程池, _方法, _参数);
	}

	public static void 线程(函数 _方法, Object... _参数) {

		new 线程(_方法).启动();
	}

	public static void 主线程(函数 _方法, Object... _参数) {
		设备.取当前系统().主线程执行(_方法);
	}

}
