package 间.视图;

import 间.内容.上下文;
import 间.工具.设备;
import 间.视图.基本.基本布局;
import 间.视图.基本.基本视图;

public class 文本视图 extends 基本视图 {

	public 文本视图(上下文 _上下文) {
		super(_上下文);
	}

	public 文本视图(基本布局 _布局) {
		this(_布局.取上下文());
		_布局.加入子视图(this);
	}

	public String 取文本() {
		return 取实现().取文本();
	}

	public void 置文本(String _文本) {
		取实现().置文本(_文本);
	}

	public void 置文本颜色(String _颜色) {
		取实现().置文本颜色(_颜色);
	}

	@Override
	protected 实现 新实现() {
		return 设备.取当前系统().取视图实现().新文本视图实现(this);
	}

	@Override
	public 实现 取实现() {
		return (文本视图.实现) super.取实现();
	}

	public interface 实现 extends 基本视图.实现 {

		String 取文本();

		void 置文本(String _文本);

		void 置文本颜色(String _颜色);

	}
}
