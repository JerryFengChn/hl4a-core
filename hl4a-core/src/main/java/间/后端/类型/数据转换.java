package 间.后端.类型;

import 间.后端.数据;
import 间.工具.反射;

import java.util.Date;
import java.util.Map;

public enum 数据转换 {
	;

	public static Object 解析(Object _对象) {

		if (_对象 == null) return null;

		if (反射.是原生类型(_对象.getClass())) return _对象;

		if (_对象 instanceof Map) {
			Map _表 = (Map) _对象;
			if (_表.containsKey("__type")) {
				switch ((String) _表.get("__type")) {
					case "Pointer":
						return new 指针数据(_表);
					case "Date":
						return new 日期数据(_表);
				}
			}
		}

		return _对象;

	}

	public static Object 转换(Object _对象) {

		if (_对象 == null) return null;

		if (反射.是原生类型(_对象.getClass())) return _对象;

		if (_对象 instanceof 数据) {
			return new 指针数据((数据) _对象);
		} else if (_对象 instanceof Date) {
			return new 日期数据((Date) _对象);
		}

		throw new RuntimeException("不支持的后端参数 : " + _对象.getClass());

	}

}
