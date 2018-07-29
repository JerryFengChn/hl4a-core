package 间.后端.类型;

import 间.工具.时间;
import 间.收集.哈希表;

import java.util.Date;
import java.util.Map;

public class 日期数据 extends 哈希表<String, String> {

	private 日期数据() {

		设置("__type", "Date");
	}

	public 日期数据(Map _表) {

		this((String) _表.get("iso"));
	}

	public 日期数据(Date _日期) {

		this(时间.格式(_日期, 时间.ISO));
	}

	public 日期数据(String _ISO) {

		this();
		置日期ISO(_ISO);
	}

	public String 取日期ISO() {

		return 读取("iso");
	}

	public void 置日期ISO(String _ISO) {

		设置("iso", _ISO);
	}

	public Date 取日期() {

		return 时间.解析(取日期ISO(), 时间.ISO);
	}

}
