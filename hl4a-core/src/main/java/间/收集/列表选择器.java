package 间.收集;

public abstract class 列表选择器<内容> extends 有序列表<内容> {

	private transient Integer 截止;

	protected abstract boolean 处理(内容 _内容);

	protected abstract void 重置();

	public 列表选择器<内容> 截止(int _截止) {

		截止 = _截止;
		return this;

	}

	public 有序列表<内容> 匹配() {
		有序列表<内容> _结果 = new 有序列表<>();
		for (内容 _单个 : this) {
			if (截止 != null && 截止 < 1) break;
			if (处理(_单个)) {
				_结果.添加(_单个);
				if (截止 != null) 截止--;
			}

		}
		return _结果;

	}

}
