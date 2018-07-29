package 间.桌面;

import com.sun.javafx.application.PlatformImpl;
import javafx.application.Platform;
import 间.内容.应用;
import 间.工具.反射;
import 间.工具.线程;
import 间.工具.设备;

public class 桌面应用 extends 应用 {

	static {

		设备.置当前系统(桌面系统.实例);

	}

	static {

		PlatformImpl.startup(() -> {
		});

	}

	private static void 执行启动(Class<? extends 应用> _应用类) {

		执行启动实现(_应用类);

	}

	public static void 执行启动() {

		StackTraceElement[] _调用栈 = 线程.取调用栈();

		boolean _已找到 = false;
		String _应用类名 = null;

		for (StackTraceElement _单个 : _调用栈) {

			_应用类名 = _单个.getClassName();

			if (_已找到) break;


			if (桌面应用.class.getName().equals(_应用类名) && _单个.getMethodName().equals("执行启动")) {

				_已找到 = true;

			}

		}

		if (_应用类名 != null) {

			Class<? extends 应用> _应用类 = 反射.取类(_应用类名);

			if (_应用类 != null) {


				执行启动实现(_应用类);

				return;

			}

		}

		throw new IllegalStateException("找不到应用类");


	}

	private static void 执行启动实现(Class<? extends 应用> _应用类) {

		try {

			Platform.runLater(() -> {

				应用 _应用 = 反射.实例化(_应用类);
				_应用.应用启动事件();

			});


		} catch (Exception _错误) {

			_错误.printStackTrace();

		}

	}


}