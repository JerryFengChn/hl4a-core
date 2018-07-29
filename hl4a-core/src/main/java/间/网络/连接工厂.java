package 间.网络;

import org.json.JSONObject;
import 间.工具.文件;
import 间.收集.有序哈希表;

import java.util.Map;

public class 连接工厂 {

	private String 地址;
	private String 后缀;
	private String 模式;
	private 有序哈希表<String, String> 请求头表 = new 有序哈希表<>();
	private 有序哈希表<String, String> Cookie表 = new 有序哈希表<>();
	private 有序哈希表<String, String> 参数表 = new 有序哈希表<>();
	private 有序哈希表<String, String> 链接参数表 = new 有序哈希表<>();
	private 有序哈希表<String, String> 文件表 = new 有序哈希表<>();
	private String JSON;
	private boolean JSON输出 = false;
	private String UA;

	public void 地址(String _地址) {

		地址 = _地址;
	}

	public void 后缀(String _地址) {

		后缀 = _地址;
	}

	public void 模式(String _模式) {

		模式 = _模式;
	}

	public void 标识(String _UA) {

		UA = _UA;
	}

	public void JSON(String _内容) {

		JSON = _内容;
	}

	public void JSON(Map _内容) {

		JSON(new JSONObject(_内容).toString());
	}

	public void JSON输出(boolean _状态) {

		JSON输出 = _状态;
	}

	public void 请求头(String _名称, String _内容) {

		请求头表.设置(_名称, _内容);
	}

	public void 链接参数(String _名称, String _内容) {

		链接参数表.设置(_名称, _内容);
	}

	public void 参数(String _名称, String _内容) {

		参数表.设置(_名称, _内容);
	}

	public void Cookie(String _名称, String _内容) {

		Cookie表.设置(_名称, _内容);
	}

	public void 文件(String _名称, String _文件) {

		文件表.设置(_名称, _文件);
	}

	public 连接 新建() {

		连接 _新建 = new 连接();
		if (UA != null) {
			_新建.标识(UA);
		}
		_新建.JSON输出(JSON输出);
		_新建.JSON(JSON);
		if (地址 != null) {
			_新建.地址(地址 + (后缀 == null ? "" : 后缀));
		}
		if (模式 != null) {
			_新建.模式(模式);
		}
		for (Map.Entry<String, String> _单个 : 请求头表.entrySet()) {
			_新建.请求头(_单个.getKey(), _单个.getValue());
		}
		for (Map.Entry<String, String> _单个 : Cookie表.entrySet()) {
			_新建.Cookie(_单个.getKey(), _单个.getValue());
		}
		for (Map.Entry<String, String> _单个 : 链接参数表.entrySet()) {
			_新建.链接参数(_单个.getKey(), _单个.getValue());
		}
		for (Map.Entry<String, String> _单个 : 参数表.entrySet()) {
			_新建.参数(_单个.getKey(), _单个.getValue());
		}
		for (Map.Entry<String, String> _单个 : 文件表.entrySet()) {
			_新建.文件(_单个.getKey(), new 文件(_单个.getValue()));
		}
		return _新建;
	}

	public 连接 新建(String _地址) {

		if (_地址 == null) return 新建();
		if (地址 != null) {
			_地址 = 地址 + _地址;
		}
		if (后缀 != null) {
			_地址 = _地址 + 后缀;
		}
		return 新建().地址(_地址);
	}

	public 连接 新建(String _地址, String _模式) {

		return 新建(_地址).模式(_模式);
	}

}
