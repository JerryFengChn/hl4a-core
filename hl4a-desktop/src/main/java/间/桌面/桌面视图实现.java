package 间.桌面;

import 间.桌面.视图.桌面垂直布局;
import 间.桌面.视图.桌面基本布局;
import 间.桌面.视图.桌面基本视图;
import 间.桌面.视图.桌面文本视图;
import 间.视图.垂直布局;
import 间.视图.基本.基本布局;
import 间.视图.基本.基本视图;
import 间.视图.工具.视图实现;
import 间.视图.文本视图;
import 间.视图.水平布局;
import 间.视图.线性布局;

public class 桌面视图实现 extends 视图实现 {

	public static 桌面视图实现 实例 = new 桌面视图实现();

	private 桌面视图实现() {
	}

	@Override
	public 线性布局.实现 新线性布局实现(水平布局 _视图) {
		return null;
	}

	@Override
	public 基本视图.实现 新基本视图实现(基本视图 _视图) {
		return new 桌面基本视图() {
			@Override
			public 基本视图 取视图() {
				return _视图;
			}
		};
	}

	@Override
	public 基本布局.实现 新基本布局实现(基本布局 _视图) {
		return new 桌面基本布局() {
			@Override
			public 基本视图 取视图() {
				return _视图;
			}
		};
	}

	@Override
	public 线性布局.实现 新线性布局实现(垂直布局 _视图) {
		return new 桌面垂直布局() {
			@Override
			public 基本视图 取视图() {
				return _视图;
			}
		};
	}

	@Override
	public 文本视图.实现 新文本视图实现(文本视图 _视图) {
		return new 桌面文本视图() {
			@Override
			public 基本视图 取视图() {
				return _视图;
			}
		};
	}

}
