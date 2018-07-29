package 间.工具;

import 间.收集.线程二级表;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

@SuppressWarnings({"NonAsciiCharacters", "deprecation"})
public enum 时间 {
	;

	public static final String 默认 = "yyyy-MM-dd HH:mm:ss";
	public static final String 中文 = "yyyy年-MM月-dd日 HH时:mm分:ss秒";
	public static final String ISO = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";

	private static final 线程二级表<String, SimpleDateFormat> 格式表 = new 线程二级表<>();

	public static String 格式() {

		return 格式(new Date());
	}

	public static String 格式(Date _时间) {

		return _时间.toLocaleString();
	}

	public static String 格式(String _格式) {

		return 格式(new Date(), _格式);
	}

	public static String 格式(long _时间戳, String _格式) {

		return 格式(new Date(_时间戳), _格式);
	}

	public static String 格式(Date _时间, String _格式) {

		SimpleDateFormat _格式器 = 格式表.读取(_格式);
		if (_格式器 == null) {
			_格式器 = new SimpleDateFormat(_格式, Locale.getDefault());
			格式表.设置(_格式, _格式器);
		}
		return _格式器.format(_时间);
	}

	public static Date 解析(long _时间) {

		return new Date(_时间);
	}

	public static Date 解析(String _时间, String _格式) {

		SimpleDateFormat _格式器 = 格式表.读取(_格式);
		if (_格式器 == null) {
			_格式器 = new SimpleDateFormat(_格式, Locale.getDefault());
			格式表.设置(_格式, _格式器);
		}
		try {
			return _格式器.parse(_时间);
		} catch (ParseException _错误) {
			错误.抛出(_错误);
		}
		return null;
	}

	public static long 时间戳() {

		return System.currentTimeMillis();
	}

	public static long 时间戳(Date _时间) {

		return _时间.getTime();
	}

	public static Integer 年份() {

		return 年份(new Date());
	}

	public static Integer 年份(long _时间戳) {

		return 年份(new Date(_时间戳));
	}

	public static Integer 年份(Date _时间) {

		return _时间.getYear();
	}

	public static Integer 月份() {

		return 月份(new Date());
	}

	public static Integer 月份(long _时间戳) {

		return 月份(new Date(_时间戳));
	}

	public static Integer 月份(Date _时间) {

		return _时间.getMonth();
	}

	public static Integer 天数() {

		return 天数(new Date());
	}

	public static Integer 天数(long _时间戳) {

		return 天数(new Date(_时间戳));
	}

	public static Integer 天数(Date _时间) {

		return _时间.getDate();
	}

	public static Integer 星期() {

		return 星期(new Date());
	}

	public static Integer 星期(long _时间戳) {

		return 星期(new Date(_时间戳));
	}

	public static Integer 星期(Date _时间) {

		return _时间.getDay();
	}

	public static Integer 小时() {

		return 小时(new Date());
	}

	public static Integer 小时(long _时间戳) {

		return 小时(new Date(_时间戳));
	}

	public static Integer 小时(Date _时间) {

		return _时间.getHours();
	}

	public static Integer 分钟() {

		return 分钟(new Date());
	}

	public static Integer 分钟(long _时间戳) {

		return 分钟(new Date(_时间戳));
	}

	public static Integer 分钟(Date _时间) {

		return _时间.getMinutes();
	}

	public static Integer 秒钟() {

		return 秒钟(new Date());
	}

	public static Integer 秒钟(long _时间戳) {

		return 秒钟(new Date(_时间戳));
	}

	public static Integer 秒钟(Date _时间) {

		return _时间.getSeconds();
	}

	public static String 比较(Date _时间) {

		return 比较(时间戳(_时间));
	}

	public static String 比较(long _时间) {

		String _比较 = "";

		long _当前 = 时间戳();

		int _年份 = 年份(_当前);
		int _月份 = 月份(_当前);
		int _天数 = 天数(_当前);
		int _小时 = 小时(_当前);
		int _分钟 = 分钟(_当前);

		int _旧年 = 年份(_时间);
		int _旧月 = 月份(_时间);
		int _旧天 = 天数(_时间);
		int _旧时 = 小时(_时间);
		int _旧分 = 分钟(_时间);

		int _权重 = 1;

		if (_分钟 != _旧分) _比较 = ((_分钟 - _旧分) + "分钟");

		if (_小时 != _旧时) _比较 = ((_小时 - _旧时) + "小时");
		else _权重++;

		if (_天数 != _旧天) _比较 = ((_天数 - _旧天) + "天");
		else _权重++;

		if (_月份 != _旧月) _比较 = ((_月份 - _旧月) + "月");
		else _权重++;

		if (_年份 != _旧年) _比较 = ((_年份 - _旧年) + "年");
		else _权重++;

		if (_当前 < _时间) _比较 += "后";
		else _比较 += "前";

		if (_权重 == 1) _比较 = "刚刚";

		return _比较;

	}

}
