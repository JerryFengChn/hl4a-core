package 间.数据;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import 间.收集.I列表;
import 间.收集.I哈希表;
import 间.收集.有序列表;
import 间.收集.有序哈希表;

import java.util.List;
import java.util.Map;

public enum JSON {
	;

	public static I哈希表 解析(String _内容) {

		return 数据转哈希表(_内容);
	}

	public static I列表 解析列表(String _内容) {

		return 数据转列表(_内容);
	}

	public static String 转换(Map _数据) {

		return new JSONObject(_数据).toString();
	}

	public static String 转换(List _数据) {

		return new JSONArray(_数据).toString();
	}

	private static I哈希表 数据转哈希表(String _数据) {

		try {
			JSONObject _对象 = new JSONObject(_数据);
			return JSON转哈希表(_对象);
		} catch (JSONException _错误) {
			return null;
		}
	}

	private static I列表 数据转列表(String _数据) {

		try {
			JSONArray _对象 = new JSONArray(_数据);
			return JSON转列表(_对象);
		} catch (JSONException _错误) {
			return null;
		}
	}

	private static I哈希表 JSON转哈希表(JSONObject _对象) throws JSONException {

		有序哈希表 _返回 = new 有序哈希表();
		JSONArray _键值表 = _对象.names();
		if (_键值表 == null) return _返回;
		int _所有 = _键值表.length();
		for (int _键值 = 0; _键值 < _所有; _键值++) {
			String _单个 = _键值表.getString(_键值);
			Object _内容 = _对象.get(_单个);
			if (_内容 instanceof JSONObject) {
				_内容 = JSON转哈希表((JSONObject) _内容);
			}
			if (_内容 instanceof JSONArray) {
				_内容 = JSON转列表((JSONArray) _内容);
			}
			_返回.设置(_单个, _内容);
		}
		return _返回;
	}

	private static I列表 JSON转列表(JSONArray _表) throws JSONException {

		有序列表 _返回 = new 有序列表();
		int _所有 = _表.length();
		for (int _键值 = 0; _键值 < _所有; _键值++) {
			Object _内容 = _表.get(_键值);
			if (_内容 instanceof JSONObject) {
				_内容 = JSON转哈希表((JSONObject) _内容);
			}
			if (_内容 instanceof JSONArray) {
				_内容 = JSON转列表((JSONArray) _内容);
			}
			_返回.添加(_内容);
		}
		return _返回;
	}

}
