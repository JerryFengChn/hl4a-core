package 间.酷Q.群;

import 间.收集.有序列表;
import 间.酷Q.机器人;
import 间.酷Q.用户.用户列表;

public class 群员列表<群员 extends 间.酷Q.群.群员> extends 用户列表<群员> {

	private transient 有序列表<String> 名片包含;
	private transient 有序列表<String> 名片正则;
	private transient String 名片等于;
	private transient 有序列表<String> 地区包含;
	private transient 有序列表<String> 地区正则;
	private transient String 地区等于;
	private transient Boolean 是管理员;
	private transient Boolean 是群主;
	private transient Boolean 有不良记录;
	private transient 有序列表<String> 专属头衔包含;
	private transient 有序列表<String> 专属头衔正则;
	private transient String 专属头衔等于;
	private transient Long 专属头衔过期时间等于;
	private transient Long 专属头衔过期时间大于;
	private transient Long 专属头衔过期时间小于;
	private transient Long 专属头衔过期时间大于等于;
	private transient Long 专属头衔过期时间小于等于;
	private transient Long 上次发言时间等于;
	private transient Long 上次发言时间大于;
	private transient Long 上次发言时间小于;
	private transient Long 上次发言时间大于等于;
	private transient Long 上次发言时间小于等于;

	public 群员列表(机器人 _上下文) {
		super(_上下文);
	}

	public 群员列表<群员> 名片包含(String... _匹配) {

		if (名片包含 == null) 名片包含 = new 有序列表<>();
		名片包含.添加所有(_匹配);

		return this;

	}

	public 群员列表<群员> 名片正则(String... _匹配) {

		if (名片包含 == null) 名片正则 = new 有序列表<>();
		名片正则.添加所有(_匹配);

		return this;

	}

	public 群员列表<群员> 名片等于(String _名片) {

		名片等于 = _名片;

		return this;

	}

	public 群员列表<群员> 地区包含(String... _匹配) {

		if (地区包含 == null) 地区包含 = new 有序列表<>();
		地区包含.添加所有(_匹配);

		return this;

	}

	public 群员列表<群员> 地区正则(String... _匹配) {

		if (地区包含 == null) 地区正则 = new 有序列表<>();
		地区正则.添加所有(_匹配);

		return this;

	}

	public 群员列表<群员> 地区等于(String _地区) {

		地区等于 = _地区;

		return this;

	}


	public 群员列表<群员> 专属头衔过期时间等于(long _专属头衔过期时间) {

		专属头衔过期时间等于 = _专属头衔过期时间;

		return this;

	}

	public 群员列表<群员> 专属头衔过期时间大于(long _专属头衔过期时间) {

		专属头衔过期时间大于 = _专属头衔过期时间;

		return this;

	}

	public 群员列表<群员> 专属头衔过期时间小于(long _专属头衔过期时间) {

		专属头衔过期时间小于 = _专属头衔过期时间;

		return this;

	}

	public 群员列表<群员> 专属头衔过期时间大于等于(long _专属头衔过期时间) {

		专属头衔过期时间大于等于 = _专属头衔过期时间;

		return this;

	}

	public 群员列表<群员> 专属头衔过期时间小于等于(long _专属头衔过期时间) {

		专属头衔过期时间小于等于 = _专属头衔过期时间;

		return this;

	}

	public 群员列表<群员> 上次发言时间等于(long _上次发言时间) {

		上次发言时间等于 = _上次发言时间;

		return this;

	}

	public 群员列表<群员> 上次发言时间大于(long _上次发言时间) {

		上次发言时间大于 = _上次发言时间;

		return this;

	}

	public 群员列表<群员> 上次发言时间小于(long _上次发言时间) {

		上次发言时间小于 = _上次发言时间;

		return this;

	}

	public 群员列表<群员> 上次发言时间大于等于(long _上次发言时间) {

		上次发言时间大于等于 = _上次发言时间;

		return this;

	}

	public 群员列表<群员> 上次发言时间小于等于(long _上次发言时间) {

		上次发言时间小于等于 = _上次发言时间;

		return this;

	}

	@Override
	public boolean 处理(群员 _用户) {

		if (!super.处理(_用户)) return false;

		if (名片等于 == null) {

			if (名片包含 != null) {

				for (String _单个名片 : 名片包含) if (!_用户.取名片().contains(_单个名片)) return false;

			}

			if (名片正则 != null) {

				for (String _单个正则 : 名片正则) if (!_用户.取名片().matches(_单个正则)) return false;

			}

		} else if (!名片等于.equals(_用户.取名片())) {
			return false;
		}

		if (地区等于 == null) {

			if (地区包含 != null) {

				for (String _单个地区 : 地区包含) if (!_用户.取地区().contains(_单个地区)) return false;

			}

			if (地区正则 != null) {

				for (String _单个正则 : 地区正则) if (!_用户.取地区().matches(_单个正则)) return false;

			}

		} else if (!(地区等于.equals(_用户.取地区()))) {
			return false;
		}


		if (是管理员 != null && !是管理员.equals(_用户.是管理员())) return false;

		if (是群主 != null && !是群主.equals(_用户.是群主())) return false;

		if (有不良记录 != null && !有不良记录.equals(_用户.有不良记录())) return false;

		if (专属头衔过期时间等于 == null) {

			if (专属头衔过期时间大于等于 != null && _用户.取专属头衔过期时间() < 专属头衔过期时间大于等于) return false;
			if (专属头衔过期时间小于等于 != null && _用户.取专属头衔过期时间() > 专属头衔过期时间小于等于) return false;
			if (专属头衔过期时间大于 != null && _用户.取专属头衔过期时间() <= 专属头衔过期时间大于) return false;
			if (专属头衔过期时间小于 != null && _用户.取专属头衔过期时间() >= 专属头衔过期时间小于) return false;
		} else if (!(专属头衔过期时间等于 == _用户.取专属头衔过期时间())) {
			return false;
		}

		if (上次发言时间等于 == null) {

			if (上次发言时间大于等于 != null && _用户.取上次发言时间() < 上次发言时间大于等于) return false;
			if (上次发言时间小于等于 != null && _用户.取上次发言时间() > 上次发言时间小于等于) return false;
			if (上次发言时间大于 != null && _用户.取上次发言时间() <= 上次发言时间大于) return false;
			return 上次发言时间小于 == null || _用户.取上次发言时间() < 上次发言时间小于;

		} else return 上次发言时间等于 == _用户.取上次发言时间();

	}

	public 群员列表<群员> 是管理员(Boolean _管理员) {

		是管理员 = _管理员;

		return this;

	}

	public 群员列表<群员> 是群主(Boolean _群主) {

		是群主 = _群主;

		return this;

	}

	public 群员列表<群员> 有不良纪录(Boolean _不良纪录) {

		有不良记录 = _不良纪录;

		return this;

	}

	@Override
	public 群员列表<群员> 匹配() {
		群员列表<群员> _匹配 = new 群员列表<>(取上下文());
		_匹配.添加所有(匹配列表());
		return _匹配;
	}

	@Override
	protected void 重置() {
		super.重置();

		名片等于 = null;
		名片正则 = null;
		名片包含 = null;
	}

	@Override
	public 群员列表<群员> 账号包含(String... _匹配) {
		super.账号包含(_匹配);
		return this;
	}

	@Override
	public 群员列表<群员> 账号正则(String... _匹配) {
		super.账号正则(_匹配);
		return this;
	}

	@Override
	public 群员列表<群员> 账号等于(String _账号) {
		super.账号等于(_账号);
		return this;
	}

	@Override
	public 群员列表<群员> 名称包含(String... _匹配) {
		super.名称包含(_匹配);
		return this;
	}

	@Override
	public 群员列表<群员> 名称正则(String... _匹配) {
		super.名称正则(_匹配);
		return this;
	}

	@Override
	public 群员列表<群员> 名称等于(String _名称) {
		super.名称等于(_名称);
		return this;
	}

	/**
	 * @param _性别 男或女或未知
	 */
	@Override
	public 群员列表<群员> 性别为(String _性别) {
		super.性别为(_性别);
		return this;
	}

	@Override
	public 群员列表<群员> 年龄等于(int _年龄) {
		super.年龄等于(_年龄);
		return this;
	}

	@Override
	public 群员列表<群员> 年龄大于(int _年龄) {
		super.年龄大于(_年龄);
		return this;
	}

	@Override
	public 群员列表<群员> 年龄小于(int _年龄) {
		super.年龄小于(_年龄);
		return this;
	}

	@Override
	public 群员列表<群员> 年龄大于等于(int _年龄) {
		super.年龄大于等于(_年龄);
		return this;
	}

	@Override
	public 群员列表<群员> 年龄小于等于(int _年龄) {
		super.年龄小于等于(_年龄);
		return this;
	}

}