package 间.视图;

import 间.内容.上下文;
import 间.工具.设备;
import 间.视图.基本.基本布局;

public class 水平布局 extends 线性布局 {

	public 水平布局(上下文 _上下文) {
		super(_上下文);
	}

	public 水平布局(基本布局 _布局) {
		this(_布局.取上下文());
		_布局.加入子视图(this);
	}

	@Override
	protected 线性布局.实现 新实现() {
		return 设备.取当前系统().取视图实现().新线性布局实现(this);
	}

}
