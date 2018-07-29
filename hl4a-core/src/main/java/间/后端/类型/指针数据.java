package 间.后端.类型;

import 间.后端.数据;
import 间.接口.返回值;
import 间.收集.哈希表;

import java.util.Map;

public class 指针数据 extends 哈希表<String, Object> {

	private 数据 数据;
	private boolean 需要保存 = false;

	public 指针数据(Map<String, Object> _数据) {

		设置("__type", "Pointer");
		putAll(_数据);
	}

	public 指针数据(数据 _数据) {

		数据 = _数据;
		设置("__type", "Pointer");
		设置("className", _数据.取表名());
		if (_数据.是新数据()) {
			需要保存 = true;
		} else {
			设置("objectId", _数据.取对象ID());
		}
	}

	public 返回值 同步准备() {

		if (需要保存) {
			返回值 _状态 = 数据.同步保存();
			if (!_状态.成功()) {
				return _状态;
			}
			设置("objectId", 数据.取对象ID());
			需要保存 = false;
		}
		return 返回值.成功;
	}

	public 数据 读取() {

		if (数据 == null) {
			数据 = new 数据(this);
		}
		return 数据;
	}

}
