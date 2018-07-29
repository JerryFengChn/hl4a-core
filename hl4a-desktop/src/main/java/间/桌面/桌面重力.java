package 间.桌面;

import javafx.geometry.Pos;
import 间.工具.断言;
import 间.视图.工具.布局重力;

import java.util.NoSuchElementException;

public class 桌面重力 {

	public static Pos 转换(布局重力 _重力) {

		断言.不为空(_重力, "重力为空");

		switch (_重力) {

			case 左上:
				return Pos.TOP_LEFT;
			case 左中:
				return Pos.CENTER_LEFT;
			case 左下:
				return Pos.BOTTOM_LEFT;

			case 中上:
				return Pos.TOP_CENTER;
			case 中间:
				return Pos.CENTER;
			case 中下:
				return Pos.BOTTOM_CENTER;

			case 右上:
				return Pos.TOP_RIGHT;
			case 右中:
				return Pos.CENTER_RIGHT;
			case 右下:
				return Pos.BOTTOM_RIGHT;

		}

		throw new NoSuchElementException("没有那样的重力 : " + _重力);

	}

}
