package 间.工具;

import java.util.Random;

public enum 数学 {
	;

	public static int 随机数(int _最小, int _最大, int _种子) {

		Random _随机数 = new Random(_种子);
		return _随机数.nextInt(_最大) % (_最大 - _最小 + 1) + _最小;
	}

	public static int 随机数(int _最小, int _最大) {

		return Double.valueOf(Math.random() * (_最大 - _最小 + 1) + _最小).intValue();
	}

	public static int 绝对值(int _值) {

		return Math.abs(_值);
	}

	public static double 绝对值(double _值) {

		return Math.abs(_值);
	}

	public static float 绝对值(float _值) {

		return Math.abs(_值);
	}

	public static long 绝对值(long _值) {

		return Math.abs(_值);
	}

	public static double 整数大(double _值) {

		return Math.ceil(_值);
	}

	public static double 整数小(double _值) {

		return Math.floor(_值);
	}

	public static double 大(double _值, double _值二) {

		return Math.max(_值, _值二);
	}

	public static int 大(int _值, int _值二) {

		return Math.max(_值, _值二);
	}

	public static float 大(float _值, float _值二) {

		return Math.max(_值, _值二);
	}

	public static long 大(long _值, long _值二) {

		return Math.max(_值, _值二);
	}

	public static double 小(double _值, double _值二) {

		return Math.min(_值, _值二);
	}

	public static int 小(int _值, int _值二) {

		return Math.min(_值, _值二);
	}

	public static float 小(float _值, float _值二) {

		return Math.min(_值, _值二);
	}

	public static long 小(long _值, long _值二) {

		return Math.min(_值, _值二);
	}

	public static int 取位(int _数字, int _位数) {

		String _位 = Integer.valueOf(_数字).toString();
		return Integer.valueOf(字符.取中间(_位, _位.length() - _位数, _位.length()));
	}

}
