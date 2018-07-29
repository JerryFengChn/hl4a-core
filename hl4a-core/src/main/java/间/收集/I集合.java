package 间.收集;

import 间.工具.数组;

import java.util.Collection;

public interface I集合<类型> extends Collection<类型> {

	default int 长度() {
		return size();
	}

	default boolean 为空() {

		return isEmpty();
	}

	default Object[] 到数组() {

		return toArray();
	}

	default 类型[] 到数组(Class<类型> _类) {

		return 到数组(数组.创建(_类, 长度()));
	}

	default 类型[] 到数组(类型[] _空数组) {

		return toArray(_空数组);
	}

	default boolean 添加(类型 _内容) {

		return add(_内容);
	}

	default boolean 添加所有(类型[] _内容) {

		for (类型 _单个 : _内容) {
			if (!添加(_单个)) {
				return false;
			}
		}
		return true;
	}

	default boolean 添加所有(Collection<类型> _内容) {

		return addAll(_内容);
	}

	default void 清空() {

		clear();
	}

	default boolean 检查(Object _内容) {

		return contains(_内容);
	}

	default boolean 检查所有(Collection<?> _内容) {

		return containsAll(_内容);
	}

	default boolean 删除对象(Object _对象) {

		return remove(_对象);
	}

	default boolean 删非交集(Collection<?> _交集) {

		return retainAll(_交集);
	}

	default boolean 删交集(Collection<?> _交集) {
		return removeAll(_交集);
	}


}
