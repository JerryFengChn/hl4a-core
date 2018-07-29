package 间.收集;

import 间.工具.线程;

@SuppressWarnings({"NonAsciiCharacters", "unchecked"})
public class 线程二级表<键值, 内容> extends 二级表<Thread, 键值, 内容> {

	public 内容 读取(Object _键值) {

		return super.读取(线程.取当前线程(), _键值);
	}

	public 内容 设置(键值 _键值, 内容 _内容) {

		return super.设置(线程.取当前线程(), _键值, _内容);
	}

}
