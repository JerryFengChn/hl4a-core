package 间.酷Q.用户;

import 间.收集.列表选择器;
import 间.收集.有序列表;
import 间.酷Q.机器人;

public class 用户列表<用户 extends 间.酷Q.用户.用户> extends 列表选择器<用户> {

	private transient 有序列表<String> 账号包含;
	private transient 有序列表<String> 账号正则;

	private transient 机器人 上下文;
	private transient String 账号等于;
	private transient 有序列表<String> 名称包含;
	private transient 有序列表<String> 名称正则;
	private transient String 名称等于;
	private transient String 性别为;
	private transient Integer 年龄等于;
	private transient Integer 年龄大于;
	private transient Integer 年龄小于;
	private transient Integer 年龄大于等于;
	private transient Integer 年龄小于等于;

	public 用户列表(机器人 _上下文) {
		上下文 = _上下文;
	}

	@Override
	public 用户列表<用户> 匹配() {
		用户列表<用户> _匹配 = new 用户列表<>(取上下文());
		_匹配.添加所有(匹配列表());
		return _匹配;
	}

	protected 有序列表<用户> 匹配列表() {
		return super.匹配();
	}

	public 机器人 取上下文() {
		return 上下文;
	}

	public 用户列表<用户> 账号包含(String... _匹配) {

		if (账号包含 == null) 账号包含 = new 有序列表<>();
		账号包含.添加所有(_匹配);

		return this;

	}

	public 用户列表<用户> 账号正则(String... _匹配) {

		if (账号包含 == null) 账号正则 = new 有序列表<>();
		账号正则.添加所有(_匹配);

		return this;

	}

	public 用户列表<用户> 账号等于(String _账号) {

		账号等于 = _账号;

		return this;

	}

	public 用户列表<用户> 名称包含(String... _匹配) {

		if (名称包含 == null) 名称包含 = new 有序列表<>();
		名称包含.添加所有(_匹配);

		return this;

	}

	public 用户列表<用户> 名称正则(String... _匹配) {

		if (名称包含 == null) 名称正则 = new 有序列表<>();
		名称正则.添加所有(_匹配);

		return this;

	}

	public 用户列表<用户> 名称等于(String _名称) {

		名称等于 = _名称;

		return this;

	}

	/**
	 * @param _性别 男或女或未知
	 */
	public 用户列表<用户> 性别为(String _性别) {
		性别为 = _性别;
		return this;
	}

	public 用户列表<用户> 年龄等于(int _年龄) {

		年龄等于 = _年龄;

		return this;

	}

	public 用户列表<用户> 年龄大于(int _年龄) {

		年龄大于 = _年龄;

		return this;

	}

	public 用户列表<用户> 年龄小于(int _年龄) {

		年龄小于 = _年龄;

		return this;

	}

	public 用户列表<用户> 年龄大于等于(int _年龄) {

		年龄大于等于 = _年龄;

		return this;

	}

	public 用户列表<用户> 年龄小于等于(int _年龄) {

		年龄小于等于 = _年龄;

		return this;

	}


	@Override
	public boolean 处理(用户 _用户) {

		String _账号 = _用户.取账号().toString();

		if (账号等于 == null) {

			if (账号包含 != null) {

				for (String _单个账号 : 账号包含) {

					if (!_账号.contains(_单个账号)) return false;

				}

			}

			if (账号正则 != null) {

				for (String _单个正则 : 账号正则) {

					if (!_账号.matches(_单个正则)) return false;

				}

			}

		} else if (!账号等于.equals(_账号)) {

			return false;

		}


		if (性别为 != null && !性别为.equals(_用户.取性别())) {

			return false;

		}


		if (名称等于 == null) {

			if (名称包含 != null) {

				for (String _单个名称 : 名称包含) {

					if (!_用户.取名称().contains(_单个名称)) return false;

				}

			}

			if (名称正则 != null) {

				for (String _单个正则 : 名称正则) {

					if (!_用户.取名称().matches(_单个正则)) return false;

				}

			}

		} else if (!名称等于.equals(_用户.取名称())) {

			return false;

		}


		if (年龄等于 == null) {

			if (年龄大于等于 != null && _用户.取年龄() < 年龄大于等于) return false;
			if (年龄小于等于 != null && _用户.取年龄() > 年龄小于等于) return false;
			if (年龄大于 != null && _用户.取年龄() <= 年龄大于) return false;
			return 年龄小于 == null || _用户.取年龄() < 年龄小于;

		} else return 年龄等于 == _用户.取年龄();

	}

	@Override
	protected void 重置() {

		账号等于 = null;
		账号包含 = null;
		账号正则 = null;

		年龄等于 = null;
		年龄大于 = null;
		年龄小于 = null;
		年龄大于等于 = null;
		年龄小于等于 = null;

		名称等于 = null;
		名称包含 = null;
		名称正则 = null;

		性别为 = null;

	}
}
