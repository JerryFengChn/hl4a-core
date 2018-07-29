package 间.收集;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@SuppressWarnings({"NonAsciiCharacters", "unchecked"})
public class 哈希集<类型> extends HashSet<类型> implements Set<类型>, I集合<类型> {

	public 哈希集() {

		super();
	}

	public 哈希集(类型[] _集合) {

		this();
		添加所有(_集合);
	}

	public 哈希集(Collection<类型> _集合) {

		super(_集合);
	}
}
