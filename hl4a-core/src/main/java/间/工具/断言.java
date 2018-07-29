package 间.工具;

public enum 断言 {
	;

	public static void 不为空(Object _对象) {

		不为空(_对象, "不能为空 ！");
	}

	public static void 不为空(Object _对象, String _描述, Object... _模板) {

		if (_对象 != null) return;
		throw new NullPointerException(字符.格式化(_描述, _模板));
	}

	public static void 为真(Boolean _对象, String _描述, Object... _模板) {

		if (Boolean.valueOf(true).equals(_对象)) return;
		throw new IllegalArgumentException(字符.格式化(_描述, _模板));
	}

}
