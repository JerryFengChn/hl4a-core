package 间.桌面;

import javafx.application.Platform;
import 间.兼容.系统;
import 间.内容.界面;
import 间.工具.颜色;
import 间.方法.函数;
import 间.视图.工具.视图实现;

import java.awt.*;
import java.awt.datatransfer.*;
import java.io.IOException;

public class 桌面系统 extends 系统 {

	public static final 桌面系统 实例 = new 桌面系统();

	protected 桌面系统() {
	}

	@Override
	public String 取系统() {
		return "桌面系统";
	}

	@Override
	public void 启动界面(Class<? extends 界面> _界面) {

		桌面界面实现.启动界面(_界面);

	}

	@Override
	public void 主线程执行(函数 _函数) {
		Platform.runLater(_函数);
	}

	@Override
	public 视图实现 取视图实现() {
		return 桌面视图实现.实例;
	}

	@Override
	public void 置剪切板(String _内容) {

		Clipboard _剪切板 = Toolkit.getDefaultToolkit().getSystemClipboard();
		Transferable _剪切板内容 = new StringSelection(_内容);
		_剪切板.setContents(_剪切板内容, null);

	}

	@Override
	public void 更换颜色(颜色 _颜色) {

	}

	@Override
	public String 取剪切板() {

		Transferable _内容 = Toolkit.getDefaultToolkit().getSystemClipboard().getContents(null);
		try {
			return _内容 == null ? null : (String) _内容.getTransferData(DataFlavor.stringFlavor);
		} catch (UnsupportedFlavorException e) {
			return "";
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

	}

	@Override
	public boolean 网络可用() {
		return true;
	}

}
