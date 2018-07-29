package 间.视图.基本;

import 间.内容.上下文;
import 间.工具.设备;

public class 基本视图 {

	private 实现 实现 = 新实现();
	private 上下文 上下文;

	public 基本视图(上下文 _上下文) {
		上下文 = _上下文;
	}

	public 基本视图(基本布局 _布局) {
		this(_布局.取上下文());
		_布局.加入子视图(this);
	}

	protected 实现 新实现() {
		return 设备.取当前系统().取视图实现().新基本视图实现(this);
	}

	public 实现 取实现() {
		return 实现;
	}

	public 上下文 取上下文() {
		return 上下文;
	}

	public 基本视图 取视图() {
		return this;
	}

	public void 置宽度(int _宽度) {
		实现.置宽度(_宽度);
	}

	public int 取宽度() {
		return 实现.取宽度();
	}

	public void 置高度(int _高度) {
		实现.置高度(_高度);
	}

	public int 取高度() {
		return 实现.取高度();
	}

	public void 置填充(int _填充) {
		实现.置填充(_填充);
	}

	public interface 实现 {

		default 基本视图 取视图() {
			throw new IllegalStateException("没有视图上下文");
		}

		void 置宽度(int _宽度);

		int 取宽度();

		void 置高度(int _高度);

		int 取高度();

		void 置宽高(int _宽度, int _高度);

		void 置填充(int _填充);

		int 取上填充();

		void 置上填充(int _填充);

		int 取下填充();

		void 置下填充(int _填充);

		int 取左填充();

		void 置左填充(int _填充);

		int 取右填充();

		void 置右填充(int _填充);

		void 置填充(int _上, int _下, int _左, int _右);

	}

}