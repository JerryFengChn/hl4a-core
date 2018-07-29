package 间.桌面;

import com.jfoenix.controls.JFXDecorator;
import javafx.collections.ObservableList;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;
import 间.内容.界面;
import 间.工具.反射;
import 间.工具.断言;
import 间.桌面.视图.桌面基本视图;
import 间.视图.基本.基本视图;

public class 桌面界面实现 extends Stage implements 界面.实现 {


	public static void 启动界面(Class<? extends 界面> _界面) {

		桌面界面实现 _实现 = new 桌面界面实现();

		断言.不为空(_界面, "界面为空");

		界面 _实例 = 反射.实例化(_界面);

		_实例.初始化实现(_实现);

		_实例.界面创建事件();

		_实现.显示();

	}

	@Override
	public void 置内容(基本视图 _视图) {

		if (!(_视图.取实现() instanceof 桌面基本视图)) {
			throw new IllegalStateException("不支持的视图实现");
		}

		桌面基本视图 _内容 = (桌面基本视图) _视图.取实现();

		double width = 800;
		double height = 600;
		try {
			Rectangle2D bounds = Screen.getScreens().get(0).getBounds();
			width = bounds.getWidth() / 2.5;
			height = bounds.getHeight() / 1.35;
		} catch (Exception ignored) {
		}

		JFXDecorator _底层 = new JFXDecorator(this, _内容.取内容());

		this.setScene(new Scene(_底层, width, height));

		final ObservableList<String> stylesheets = getScene().getStylesheets();
		stylesheets.addAll(JFXDecorator.class.getResource("/css/jfoenix-fonts.css").toExternalForm(),
				JFXDecorator.class.getResource("/css/jfoenix-design.css").toExternalForm());

	}

	@Override
	public void 显示() {
		show();
	}

	@Override
	public void 关闭() {
		hide();
	}

	@Override
	public void 置标题(String _标题) {
		setTitle(_标题);
	}

	@Override
	public String 取标题() {
		return getTitle();
	}


}