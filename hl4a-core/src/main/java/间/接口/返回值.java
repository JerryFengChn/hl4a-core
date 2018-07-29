package 间.接口;

public class 返回值<内容> {

	public static 返回值 成功 = new 返回值().置状态(true);
	public static 返回值 失败 = new 返回值().置状态(false);
	private 内容 内容;
	private Boolean 状态;
	private Throwable 错误;

	public static <内容> 返回值<内容> 创建(内容 _内容, Throwable _错误) {

		return new 返回值<内容>().置内容(_内容).置错误(_错误);
	}

	public static <内容> 返回值<内容> 创建(内容 _内容, boolean _状态) {

		return new 返回值<内容>().置内容(_内容).置状态(_状态);
	}

	public static <内容> 返回值<内容> 创建(内容 _内容, boolean _状态, Throwable _错误) {

		return new 返回值<内容>().置内容(_内容).置状态(_状态).置错误(_错误);
	}

	public static <内容> 返回值<内容> 创建(内容 _内容) {

		return new 返回值<内容>().置内容(_内容);
	}

	public 返回值<内容> 置内容(内容 _内容) {

		内容 = _内容;
		return this;
	}

	public 返回值<内容> 置状态(boolean _状态) {

		状态 = _状态;
		return this;
	}

	public 返回值<内容> 置错误(Throwable _错误) {

		错误 = _错误;
		return this;
	}

	public 返回值<内容> 置错误(String _错误) {
		错误 = new RuntimeException(_错误);
		return this;
	}

	public 内容 取内容() {
		return 内容;
	}

	public String 取错误信息() {
		return 错误 == null ? "" : 间.工具.错误.取整个错误(错误);
	}

	public boolean 成功() {

		return 状态 == null ? 错误 == null : 状态;
	}

	@Override
	public String toString() {

		return "返回值 : " + (成功() ? "成功 : " + 取内容() : "失败\n" + 取错误信息());
	}

}
