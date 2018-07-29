package 间.酷Q.群;

import cc.moecraft.icq.sender.returndata.returnpojo.get.RGroup;
import 间.收集.有序列表;
import 间.酷Q.机器人;

import java.util.List;

public class 群列表 extends 有序列表<群> {

	private transient 机器人 上下文;

	public 群列表(机器人 _上下文) {
		super();
		上下文 = _上下文;
	}

	public 群列表(机器人 _上下文, List<RGroup> _群) {
		this(_上下文);
		for (RGroup _单个 : _群) {
			添加(new 群(_上下文, _单个.groupId, _单个.groupName, false));
		}
	}

	public 机器人 取上下文() {
		return 上下文;
	}

	public 群 读取(Long _群号) {
		for (群 _单个 : this) {
			if (_单个.取群号().equals(_群号)) return _单个;
		}
		return null;
	}

	public 群 读取(String _群名) {
		for (群 _单个 : this) {
			if (_单个.取群名().equals(_群名)) {
				return _单个;
			}
		}
		return null;
	}

	public 群 匹配(String _群名) {
		return 匹配所有(_群名).取第一个();
	}

	public 群列表 匹配所有(String _群名) {
		群列表 _返回 = new 群列表(上下文);
		for (群 _单个 : this) {
			if (_单个.取群名().contains(_群名)) {
				_返回.添加(_单个);
			}
		}
		return _返回;
	}

	public String[] 取所有群名() {
		String[] _所有 = new String[长度()];
		for (int _键值 = 0; _键值 < 长度(); _键值++) {
			_所有[_键值] = 读取(_键值).取群名();
		}
		return _所有;
	}

	public Long[] 取所有群号() {
		Long[] _所有 = new Long[长度()];
		for (int _键值 = 0; _键值 < 长度(); _键值++) {
			_所有[_键值] = 读取(_键值).取群号();
		}
		return _所有;
	}

}
