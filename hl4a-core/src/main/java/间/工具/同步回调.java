package 间.工具;

public class 同步回调<类型> {

	private volatile 类型 内容;

	public 类型 等待() {

		synchronized (this) {
			if (内容 != null) return 内容;
			try {
				wait();
			} catch (InterruptedException _错误) {
				throw new RuntimeException(_错误);
			}
			return 内容;
		}

	}

	public void 返回(类型 _内容) {

		synchronized (this) {
			内容 = _内容;
			notifyAll();
		}
	}

}
