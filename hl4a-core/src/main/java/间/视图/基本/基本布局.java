package 间.视图.基本;

import 间.内容.上下文;
import 间.工具.设备;

public class 基本布局 extends 基本视图 {

	public 基本布局(上下文 _上下文) {
		super(_上下文);
	}

	public 基本布局(基本布局 _布局) {
		this(_布局.取上下文());
		_布局.加入子视图(this);
	}

	@Override
	protected 实现 新实现() {
		return 设备.取当前系统().取视图实现().新基本布局实现(this);
	}

	@Override
	public 实现 取实现() {
		return (基本布局.实现) super.取实现();
	}

	public void 加入子视图(基本视图... _视图) {
		取实现().加入子视图(_视图);
	}

	public 基本视图 取子视图(int _键值) {
		return 取实现().取子视图(_键值);
	}

	public 基本视图[] 取子视图() {
		return 取实现().取子视图();
	}

	public 基本视图 删子视图(int _键值) {
		return 取实现().删子视图(_键值);
	}

	public 基本视图[] 删子视图() {
		return 取实现().删子视图();
	}

	public interface 实现 extends 基本视图.实现 {

		void 加入子视图(基本视图... _视图);

		基本视图 取子视图(int _键值);

		基本视图[] 取子视图();

		基本视图 删子视图(int _键值);

		基本视图[] 删子视图();

	}

}
