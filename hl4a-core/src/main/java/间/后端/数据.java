package 间.后端;

import 间.后端.后端.后端返回值;
import 间.后端.类型.指针数据;
import 间.后端.类型.数据转换;
import 间.后端.类型.日期数据;
import 间.工具.字符;
import 间.工具.时间;
import 间.收集.I哈希表;
import 间.收集.哈希表;
import 间.数据.JSON;

import java.util.Date;
import java.util.Map;

public class 数据 {

	private static final String 键值_对象ID = "objectId";
	private static final String 键值_创建时间 = "createdAt";
	private static final String 键值_更新时间 = "updatedAt";
	private static final String 键值_权限组 = "ACL";
	private boolean 是新数据;
	private I哈希表 本地表;
	private I哈希表 数据表;
	private String 表名;
	private boolean 保存时刷新 = true;
	private String 对象ID;

	public 数据(String _表名) {

		是新数据 = true;
		本地表 = new 哈希表();
		数据表 = new 哈希表();
		表名 = _表名;
	}

	public 数据(String _表名, String _对象ID) {

		this(_表名);
		置对象ID(_对象ID);
		是新数据 = false;
	}

	public 数据(指针数据 _数据) {

		this((String) _数据.读取("className"), (String) _数据.读取(键值_对象ID));
		for (Map.Entry<String, Object> _内容 : _数据.entrySet()) {
			switch (_内容.getKey()) {
				case "__type":
					break;
				case "className":
					break;
				case 键值_对象ID:
					break;
				default:
					数据表.设置(_内容.getKey(), _内容.getValue());
			}

		}
	}

	public boolean 是新数据() {

		return 是新数据;
	}

	public String 取表名() {

		return 表名;
	}

	public void 置表名(String _表名) {

		表名 = _表名;
	}

	public void 置对象ID(String _ID) {

		对象ID = _ID;
	}

	public String 取对象ID() {

		if (数据表.检查(键值_对象ID)) {
			return (String) 数据表.读取(键值_对象ID);
		} else {
			return 对象ID;
		}
	}

	public Date 取创建日期() {

		return 取日期(键值_创建时间);
	}

	public Date 取更新时间() {

		return 取日期(键值_更新时间);
	}

	public 数据 取数据(String _键值) {

		Object _数据 = 读取(_键值);
		if (_数据 instanceof 数据) {
			return (数据) _数据;
		} else if (_数据 instanceof 指针数据) {
			return ((指针数据) _数据).读取();
		} else if (_数据 instanceof Map) {
			return 设置(_键值, new 指针数据((Map) _数据)).读取();
		}

		return null;

	}

	public Date 取日期(String _键值) {

		Object _日期 = 读取(_键值);
		if (_日期 instanceof Date) {
			return (Date) _日期;
		} else if (_日期 instanceof 日期数据) {
			return ((日期数据) _日期).取日期();
		} else if (_日期 instanceof Map) {
			return 设置(_键值, new 日期数据((Map) _日期)).取日期();
		} else if (_日期 instanceof String) {
			return 时间.解析((String) _日期, 时间.ISO);
		}
		return null;
	}

	public <内容> 内容 读取(String _键值) {

		if (数据表.检查(_键值)) {
			return (内容) 数据转换.解析(数据表.读取(_键值));
		} else {
			return (内容) 数据转换.解析(本地表.读取(_键值));
		}
	}

	public <内容> 内容 设置(String _键值, 内容 _内容) {

		Object _数量 = 数据转换.解析(_内容);
		本地表.设置(_键值, _数量);
		return _内容;
	}

	public void 保存时刷新(boolean _刷新) {

		保存时刷新 = _刷新;
	}

	public 后端返回值 同步删除() {

		后端返回值<Map<?, ?>> _返回值 = 后端.Companion.取当前后端().同步删除对象(表名, 取对象ID());
		if (_返回值.成功()) {
			是新数据 = true;
		}
		return _返回值;
	}

	public 后端返回值 同步更新() {

		后端返回值<Map<?, ?>> _返回值 = 后端.Companion.取当前后端().同步获取对象(表名, 取对象ID());
		if (_返回值.成功()) {
			数据表.putAll(_返回值.取内容());
		}
		return _返回值;
	}

	public 后端返回值 同步更新(String... _引用) {

		后端返回值<Map<?, ?>> _返回值 = 后端.Companion.取当前后端().同步获取对象(表名, 取对象ID(), 字符.分解(_引用, ","));
		if (_返回值.成功()) {
			数据表.putAll(_返回值.取内容());
		}
		return _返回值;
	}

	public 后端返回值 同步保存() {

		if (是新数据) {
			后端返回值<Map<?, ?>> _返回值 = 后端.Companion.取当前后端().同步创建对象(表名, 本地表, 保存时刷新);
			if (_返回值.成功()) {
				是新数据 = false;
				数据表.putAll(_返回值.取内容());
			}
			return _返回值;
		} else {
			后端返回值<Map<?, ?>> _返回值 = 后端.Companion.取当前后端().同步更新对象(表名, 取对象ID(), 本地表, 保存时刷新);
			if (_返回值.成功()) {
				数据表.putAll(_返回值.取内容());
			}
			return _返回值;
		}
	}

	@Override
	public String toString() {

		return "[ 数据表 : " + 取表名() + " ] : " + JSON.转换(数据表);
	}

}
