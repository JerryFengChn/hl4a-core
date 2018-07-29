package 间.桌面.视图;

import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import 间.桌面.桌面重力;
import 间.视图.工具.布局重力;
import 间.视图.线性布局;

public abstract class 桌面线性布局 extends 桌面基本布局 implements 线性布局.实现 {

	public 桌面线性布局(HBox _内容) {
		super(_内容);
	}

	public 桌面线性布局(VBox _内容) {
		super(_内容);
	}

	private boolean isHBox() {
		return 取内容() instanceof HBox;
	}

	private boolean isVBox() {
		return 取内容() instanceof VBox;
	}

	private HBox getHBox() {
		return (HBox) 取内容();
	}

	private VBox getVBox() {
		return (VBox) 取内容();
	}

	@Override
	public void 置重力(布局重力 _布局重力) {
		if (isHBox()) {
			getHBox().setAlignment(桌面重力.转换(_布局重力));
		} else if (isVBox()) {
			getVBox().setAlignment(桌面重力.转换(_布局重力));
		} else {
			throw new IllegalStateException("桌面线性布局不是HBox或VBox");
		}
	}

}
