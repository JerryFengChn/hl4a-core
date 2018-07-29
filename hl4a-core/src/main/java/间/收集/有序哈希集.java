package 间.收集;

import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Set;

@SuppressWarnings({"NonAsciiCharacters", "unchecked"})
public class 有序哈希集<类型> extends LinkedHashSet<类型> implements Set<类型>, I集合<类型> {

	public 有序哈希集() {

		super();
	}

	public 有序哈希集(类型[] _集合) {

		this();
		添加所有(_集合);
	}

	public 有序哈希集(Collection<类型> _集合) {

		super(_集合);
	}

}
