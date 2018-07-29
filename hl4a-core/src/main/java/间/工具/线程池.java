package 间.工具;

import 间.方法.函数;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class 线程池 {

	public static final 线程池 默认 = new 线程池(10);
	private Executor 线程池;

	private 线程池() {

		this(Executors.newCachedThreadPool());
	}

	public 线程池(int _最大) {

		this(Executors.newFixedThreadPool(_最大));
	}

	public 线程池(Executor _线程池) {

		线程池 = _线程池;
	}

	public static void 执行(线程池 _线程池, 函数 _方法, Object... _参数) {

		(_线程池 == null ? 默认 : _线程池).执行(_方法, _参数);
	}

	public void 执行(final 函数 _方法, final Object... _参数) {

		线程池.execute(() -> 处理.同步(_方法, _参数));
	}

}
