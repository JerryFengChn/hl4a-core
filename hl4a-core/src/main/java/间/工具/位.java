package 间.工具;

public enum 位 {
	;

	public static boolean 取与(int _集, int _单个) {

		return (_集 & _单个) == _单个;
	}

	public static boolean 取或(int _集, int _单个) {

		return (_集 | _单个) == _单个;
	}

	public static int 与(int _参数1, int _参数2) {

		return _参数1 & _参数2;
	}

	public static int 或(int _参数1, int _参数2) {

		return _参数1 | _参数2;
	}

	public static int 异或(int _参数1, int _参数2) {

		return _参数1 ^ _参数2;
	}

	public static int 非(int _参数, int _参数2) {

		return _参数 ^ _参数2;
	}

	public static int 左移(int _参数1, int _参数2) {

		return _参数1 << _参数2;
	}

	public static int 右移(int _参数1, int _参数2) {

		return _参数1 >> _参数2;
	}

	public static int 无符号右移(int _参数1, int _参数2) {

		return _参数1 >>> _参数2;
	}

}
