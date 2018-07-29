package 间.机器人;

import 间.工具.字符;
import 间.工具.编码;
import 间.接口.返回值;
import 间.网络.资源;
import 间.网络.连接;
import 间.网络.连接工厂;

public enum 茉莉 {
	;

	private static 连接工厂 工厂 = new 连接工厂();
	private static int 分析次数 = 8;
	private static String Key;
	private static String Secret;
	private static String 用户名称 = "用户114514";
	private static String 机器人名称 = "我";

	static {
		工厂.地址("http://i.itpk.cn/api.php?");
	}

	public static void 置分析次数(int _次数) {

		if (_次数 > 1 && _次数 < 9) {
			分析次数 = _次数;
		}
	}

	public static int 取分析次数() {

		return 分析次数;
	}

	public static String 取用户名称() {

		return 用户名称;
	}

	public static void 置用户名称(String _名称) {

		用户名称 = _名称;
	}

	public static void 置机器人默认名称(String _名称) {

		机器人名称 = _名称;
	}

	public static String 取机器人默认名称() {

		return 机器人名称;
	}

	public static void 置用户(String _Key, String _Secret) {

		Key = _Key;
		Secret = _Secret;
	}

	public static 返回值<String> 同步请求(String _内容) {

		StringBuilder _构建 = new StringBuilder();
		_构建.append("question=").append(编码.链接.编码(_内容));
		_构建.append("&limit=").append(分析次数);
		if (!字符.是空白(Key, Secret)) {
			_构建.append("&api_key=").append(Key);
			_构建.append("&api_secret=").append(Secret);
		}
		连接 _连接 = 工厂.新建(_构建.toString());
		资源 _返回 = _连接.同步();
		if (_返回.成功()) {
			String _文本 = _返回.文本();
			if (字符.是否出现(_文本, "[cqname]")) {
				_文本 = 字符.替换(_文本, "[cqname]", 机器人名称);
			}
			if (字符.是否出现(_文本, "[name]")) {
				_文本 = 字符.替换(_文本, "[name]", 用户名称);
			}
			return 返回值.创建(_文本);
		}
		return 返回值.创建("网络不给力 ~", false);
	}

}
