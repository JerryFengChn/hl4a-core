package 间.桌面.视图;

import javafx.scene.layout.VBox;

public class 桌面垂直布局 extends 桌面线性布局 {

	public 桌面垂直布局() {
		this(new VBox());
	}

	public 桌面垂直布局(VBox _内容) {
		super(_内容);
	}

}
