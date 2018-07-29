package 间.收集;

import java.util.LinkedHashMap;
import java.util.Map;

@SuppressWarnings({"NonAsciiCharacters", "unchecked"})
public class 有序哈希表<键值, 内容> extends LinkedHashMap<键值, 内容> implements I哈希表<键值, 内容> {

	public 有序哈希表() {

		super();
	}

	public 有序哈希表(Map<键值, 内容> _表) {

		super(_表);
	}

}
