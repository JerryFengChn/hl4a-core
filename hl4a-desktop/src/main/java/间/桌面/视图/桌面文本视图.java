package 间.桌面.视图;

import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import 间.视图.文本视图;

public class 桌面文本视图 extends 桌面基本视图 implements 文本视图.实现 {

	public 桌面文本视图() {
		this(new Label());
	}

	public 桌面文本视图(Label _内容) {
		super(_内容);
	}

	@Override
	public Label 取内容() {
		return (Label) super.取内容();
	}

	@Override
	public String 取文本() {
		return 取内容().getText();
	}

	@Override
	public void 置文本(String _文本) {
		取内容().setText(_文本);
	}

	@Override
	public void 置文本颜色(String _颜色) {
		取内容().setTextFill(Color.web(_颜色));
	}

}
