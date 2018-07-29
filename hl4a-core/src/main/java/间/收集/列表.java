package 间.收集;

import java.util.ArrayList;
import java.util.Collection;

@SuppressWarnings({"NonAsciiCharacters", "unchecked"})
public class 列表<类型> extends ArrayList<类型> implements I列表<类型> {

	public 列表() {

		super();
	}

	public 列表(Collection<类型> _列表) {

		this();
		添加所有(_列表);
	}

	public 列表(类型[] _列表) {

		this();
		添加所有(_列表);
	}

}
