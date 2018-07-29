package 间.视图;

import 间.内容.上下文;
import 间.视图.基本.基本布局;
import 间.视图.工具.布局重力;

public abstract class 线性布局 extends 基本布局 {

	public 线性布局(上下文 _上下文) {
		super(_上下文);
	}

	public 线性布局(基本布局 _布局) {
		this(_布局.取上下文());
		_布局.加入子视图(this);
	}

	public void 置重力(布局重力 _重力) {
		取实现().置重力(_重力);
	}

	@Override
	public 实现 取实现() {
		return (线性布局.实现) super.取实现();
	}

	@Override
	protected abstract 实现 新实现();

	public interface 实现 extends 基本布局.实现 {

		void 置重力(布局重力 _布局重力);

	}

}