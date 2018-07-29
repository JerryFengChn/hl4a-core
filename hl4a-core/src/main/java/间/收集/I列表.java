package 间.收集;

import java.util.List;

public interface I列表<类型> extends I集合<类型>, List<类型> {

	default 类型 读取(int _键值) {
		return 长度() > _键值 ? get(_键值) : null;
	}

	default 类型 删除(int _键值) {
		return remove(_键值);
	}

	default 类型 设置(int _键值, 类型 _类型) {
		return set(_键值, _类型);
	}


	default 类型 取第一个() {
		return 读取(0);
	}

	default 类型 取最后一个() {
		return 长度() > 0 ? 读取(长度() - 1) : null;
	}

}
