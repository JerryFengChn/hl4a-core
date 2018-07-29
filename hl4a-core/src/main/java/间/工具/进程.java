package 间.工具;

public enum 进程 {
	;

	public static boolean 成功(Process _进程) {

		while (!已结束(_进程)) {
			线程.暂停(233);
		}
		return _进程 == null || _进程.exitValue() == 0;
	}

	public static void 结束(Process _进程) {

		if (_进程 == null) return;
		_进程.destroy();
	}

	public static boolean 已结束(Process _进程) {

		if (_进程 == null) return true;
		try {
			_进程.exitValue();
			return true;
		} catch (IllegalThreadStateException ignored) {
		}
		return false;
	}

}
