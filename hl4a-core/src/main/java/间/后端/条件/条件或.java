package 间.后端.条件;

import 间.收集.I列表;
import 间.收集.列表;
import 间.收集.哈希表;
import 间.数据.JSON;

public class 条件或 extends 哈希表<String, I列表<I条件>> implements I条件 {

	private I列表<I条件> 当前列表;

	public 条件或() {

		当前列表 = new 列表<>();
		设置("_or", 当前列表);
	}

	public 条件或(I条件... _条件) {

		this();
		添加(_条件);
	}

	@Override
	public int 类型() {

		return 2;
	}

	public 条件或 添加(I条件... _条件) {

		当前列表.添加所有(_条件);
		return this;
	}

	@Override
	public String toString() {

		return JSON.转换(this);
	}

}
