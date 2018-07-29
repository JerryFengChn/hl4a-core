package 间.工具;

import java.math.BigDecimal;
import java.math.RoundingMode;

public enum 大数 {
	;

	public static String 相加(String _参数1, String _参数2) {

		BigDecimal aa = new BigDecimal(_参数1);
		BigDecimal bb = new BigDecimal(_参数2);
		BigDecimal cc = aa.add(bb);
		return cc.toPlainString();
	}

	public static String 相减(String _参数1, String _参数2) {

		BigDecimal aa = new BigDecimal(_参数1);
		BigDecimal bb = new BigDecimal(_参数2);
		BigDecimal cc = aa.subtract(bb);
		return cc.toPlainString();
	}

	public static String 相乘(String _参数1, String _参数2) {

		BigDecimal aa = new BigDecimal(_参数1);
		BigDecimal bb = new BigDecimal(_参数2);
		BigDecimal cc = aa.multiply(bb);
		return cc.toPlainString();
	}

	public static String 相除(String _参数1, String _参数2, int 保留小数位数) {

		BigDecimal aa = new BigDecimal(_参数1);
		BigDecimal bb = new BigDecimal(_参数2);
		BigDecimal cc = aa.divide(bb, 保留小数位数, RoundingMode.DOWN);
		return cc.toPlainString();
	}

	public static int 比较(String _参数1, String _参数2) {

		BigDecimal aa = new BigDecimal(_参数1);
		BigDecimal bb = new BigDecimal(_参数2);
		return aa.compareTo(bb);
	}

	public static String 求余(String _参数1, String _参数2) {

		BigDecimal aa = new BigDecimal(_参数1);
		BigDecimal bb = new BigDecimal(_参数2);
		BigDecimal cc = aa.remainder(bb);
		return cc.toPlainString();
	}

	public static String 科学转普通(String _参数) {

		BigDecimal aa = new BigDecimal(_参数);
		return aa.toPlainString();
	}
}


