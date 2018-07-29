package 间.工具;


public enum 兼容 {
	;

	private static boolean 是安卓 = "Dalvik".equals(System.getProperty("java.vm.name"));

	public static boolean 取是安卓() {
		return 是安卓;
	}

}
