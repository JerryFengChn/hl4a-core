package 间.机器人;

import 间.工具.字符;
import 间.接口.返回值;
import 间.收集.*;
import 间.数据.JSON;
import 间.网络.资源;
import 间.网络.连接;
import 间.网络.连接工厂;

public enum 图灵 {
	;

	private static 有序列表<String> 所有用户 = new 有序列表<>();

	private static 连接工厂 工厂 = new 连接工厂();

	static {
		工厂.模式("POST");
		工厂.地址("http://openapi.tuling123.com/openapi/api/v2");
		工厂.JSON输出(true);
	}

	public static void 添加用户(String apiKey) {

		所有用户.添加(apiKey);
	}

	public static 返回值<String> 同步请求(String _内容) {

		for (String _单个 : 所有用户) {
			连接 _连接 = 工厂.新建();
			_连接.JSON(取参数表(_单个, _内容));
			资源 _资源 = _连接.同步();
			if (_资源.成功()) {
				String _返回 = 解析返回(_单个, _资源.文本());
				if (_返回 != null) {
					return 返回值.创建(_返回);
				}
			} else {
				return 返回值.创建("网络不给力 ~", false);
			}
		}
		return 返回值.创建("Key错误 ~", false);
	}

	private static String 解析返回(String _用户, String _内容) {

		if (_内容 == null) return null;
		I哈希表 _表 = JSON.解析(_内容);
		if (_表 == null) return null;
		if (!_表.检查("intent")) return null;
		哈希表 _意图 = (哈希表) _表.读取("intent");
		if (_意图 == null) return null;
		Integer _状态 = (Integer) _意图.读取("code");
		if (_状态 == null) return null;
		if (应移除(_状态)) {
			所有用户.删除对象(_用户);
			return null;
		}
		if (转换异常(_状态) != null) return null;
		I列表<哈希表> _结果 = (列表<哈希表>) _表.读取("results");
		if (_结果 == null) return null;
		for (哈希表 _单个 : _结果) {
			if ("text".equals(_单个.读取("resultType"))) {
				return (String) ((哈希表) _单个.读取("values")).读取("text");
			}
		}
		return null;
	}

	private static I哈希表 取参数表(String _用户, String _文本) {

		哈希表 _参数 = new 哈希表();
		_参数.设置("reqType", 0);
		哈希表 _输入 = new 哈希表();
		if (_文本.length() > 128) {
			_文本 = 字符.取开始前(_文本, 128);
		}
		哈希表 _内容 = new 哈希表();
		_内容.设置("text", _文本);
		//noinspection unchecked
		_输入.设置("inputText", _内容);
		哈希表 _验证 = new 哈希表();
		_验证.设置("apiKey", _用户);
		//  _验证.设置("userId", );
		_参数.设置("perception", _输入);
		_参数.设置("userInfo", _验证);
		return _参数;
	}

	public static String 转换异常(int _状态码) {

		switch (_状态码) {
			case 0:
				return "上传成功";
			case 8008:
				return "服务器错误";
			case 7002:
				return "上传信息失败";
			case 4602:
				return "输入内容过长";
			case 4600:
				return "输入内容为空";
			case 4500:
				return "id申请超限";
			case 4400:
				return "没有userId";
			case 4300:
				return "批量操作超限";
			case 4200:
				return "上传格式错误";
			case 4100:
				return "userId获取失败";
			case 4007:
				return "appKey错误";
			case 4005:
			case 4002:
				return "无功能权限";
			case 4003:
				return "没有可用次数";
			case 4001:
				return "加密方式错误";
			case 4000:
				return "请求格式错误";
			case 6000:
				return "暂不支持该功能";
			case 5000:
				return "没有解析结果";
		}
		return null;
	}

	private static boolean 应移除(int _状态码) {

		switch (_状态码) {
			case 4003:
			case 4007:
				return true;
		}
		return false;
	}

}
