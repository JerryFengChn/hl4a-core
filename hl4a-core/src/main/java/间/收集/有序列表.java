package 间.收集;

import java.util.Collection;
import java.util.LinkedList;

@SuppressWarnings({"NonAsciiCharacters", "unchecked"})
public class 有序列表<类型> extends LinkedList<类型> implements I列表<类型> {

	public 有序列表() {

		super();
	}

	public 有序列表(Collection<类型> _列表) {

		this();
		添加所有(_列表);
	}

	@SafeVarargs
	public 有序列表(类型... _列表) {

		this();
		添加所有(_列表);
	}

}
