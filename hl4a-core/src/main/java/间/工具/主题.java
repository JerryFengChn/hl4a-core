package 间.工具;

public class 主题 {

	private static 颜色 当前颜色 = 颜色.靛蓝;

	public static 颜色 取当前颜色() {
		return 当前颜色;
	}

	public static void 置当前颜色(颜色 _颜色) {
		当前颜色 = _颜色 != null ? _颜色 : 颜色.靛蓝;
		设备.取当前系统().更换颜色(_颜色);
	}

}
