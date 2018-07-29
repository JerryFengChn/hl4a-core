package 间.收集;

import java.util.Map;

public interface I哈希表<键值, 内容> extends Map<键值, 内容> {

	default int 长度() {

		return size();
	}

	default boolean 为空() {

		return isEmpty();
	}

	default boolean 检查(Object _键值) {

		return containsKey(_键值);
	}

	default boolean 检查内容(Object _内容) {

		return containsValue(_内容);
	}

	default 内容 读取(Object _键值) {

		return get(_键值);
	}

	default 内容 设置(键值 _键值, 内容 _内容) {

		return put(_键值, _内容);
	}

	default 内容 删除(Object _键值) {

		return remove(_键值);
	}

	default void 清空() {

		clear();
	}


}
