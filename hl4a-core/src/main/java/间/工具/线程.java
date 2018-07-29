package 间.工具;

import 间.收集.有序列表;
import 间.方法.函数;

public class 线程 extends Thread {

	private Object[] 参数;
	private 函数 方法;
	private UncaughtExceptionHandler 错误处理;

	public 线程() {

		错误处理 = 取错误处理对象();
	}

	public 线程(函数 _方法) {

		this();
		方法 = _方法;
	}

	public 线程(Object _对象, String _方法名) {

		this(反射.配置(_对象, _方法名));
	}

	public static void 暂停(long _长度) {

		try {
			Thread.sleep(_长度);
		} catch (InterruptedException ignored) {
		}
	}

	public static Thread 取当前线程() {

		return Thread.currentThread();
	}

	public static String 转换栈(StackTraceElement[] _栈) {

		StringBuilder _错误位置信息 = new StringBuilder();
		for (StackTraceElement _单个 : _栈) {
			_错误位置信息.append("\n" + "在: ").append(_单个);
		}
		return _错误位置信息.toString();
	}

	public static StackTraceElement[] 取调用栈() {

		return 取调用栈(1);
	}

	public static StackTraceElement[] 取调用栈(int _结束) {

		return 取调用栈(取当前线程(), _结束 + 1);
	}

	public static StackTraceElement[] 取调用栈(Thread _线程, int _结束) {

		有序列表<StackTraceElement> _调用栈 = new 有序列表<>(_线程.getStackTrace());
		for (int _键值 = -3; _键值 < _结束; _键值++) {
			_调用栈.removeFirst();
		}
		return _调用栈.到数组(StackTraceElement.class);
	}

	public static boolean 是线程() {

		return 取当前线程() instanceof 线程;
	}

	public static void 置错误处理(函数 _处理) {

		置错误处理对象(new 错误处理(_处理));
	}

	public static void 置错误处理对象(UncaughtExceptionHandler _处理) {

		Thread.setDefaultUncaughtExceptionHandler(_处理);
		取当前线程().setUncaughtExceptionHandler(_处理);
	}

	public static void 退出时执行(函数 _方法) {

		Runtime.getRuntime().addShutdownHook(new 线程(_方法));
	}

	public static UncaughtExceptionHandler 取错误处理对象() {

		return Thread.getDefaultUncaughtExceptionHandler();
	}

	public void 执行() {

		处理.同步(方法, 参数);
	}

	@Override
	public void run() {

		置错误处理对象(错误处理);
		执行();
	}

	public void 启动(Object... _参数) {

		参数 = _参数;
		start();
	}

	public void 等待() {

		try {
			join();
		} catch (InterruptedException ignored) {
		}
	}

	public static class 错误处理 implements Thread.UncaughtExceptionHandler {

		private 函数 事件;

		public 错误处理(函数 _方法) {

			事件 = _方法;
		}

		@Override
		public void uncaughtException(Thread _线程, Throwable _错误) {

			try {
				处理.同步(事件, _线程, _错误);
			} catch (Exception _新错误) {
				处理.同步(事件, _线程, _新错误);
			}
		}

	}

}
