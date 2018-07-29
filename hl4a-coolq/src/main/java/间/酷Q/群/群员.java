package 间.酷Q.群;

import cc.moecraft.icq.sender.returndata.ReturnData;
import cc.moecraft.icq.sender.returndata.returnpojo.get.RGroupMemberInfo;
import lombok.EqualsAndHashCode;
import 间.接口.返回值;
import 间.酷Q.机器人;
import 间.酷Q.机器人返回值;
import 间.酷Q.用户.用户;

@EqualsAndHashCode(callSuper = true)
public class 群员 extends 用户 {

	private 群 群;
	private transient String 名片;

	private transient String 地区;
	private transient Boolean 是管理员;
	private transient Boolean 是群主;
	private transient Boolean 有不良记录;
	private transient String 专属头衔;
	private transient Long 专属头衔过期时间;
	private transient Long 上次发言时间;

	public 群员(机器人 _上下文, Long _账号, 群 _群) {
		super(_上下文, _账号);
		群 = _群;
	}

	public 群员(机器人 _上下文, 群 _群, RGroupMemberInfo _信息) {
		this(_上下文, _信息.getUserId(), _群);
		刷新信息(_信息);
	}

	public 群 取群() {
		return 群;
	}

	public void 刷新信息(RGroupMemberInfo _信息) {
		刷新信息(_信息.nickname, _信息.sex, _信息.age.intValue());
		名片 = _信息.card;
		地区 = _信息.area;
		是管理员 = !"member".equals(_信息.role);
		是群主 = "owner".equals(_信息.role);
		有不良记录 = _信息.unfriendly;
		专属头衔 = _信息.title;
		专属头衔过期时间 = _信息.titleExpireTime;
		上次发言时间 = _信息.lastSentTime * 1000; // mmp返回的时间戳竟然是秒
	}

	@Override
	public 返回值 刷新信息(boolean _缓存) {
		ReturnData<RGroupMemberInfo> _返回 = 取上下文().取实现().getHttpApi().getGroupMemberInfo(群.取群号(), 取账号());
		if (机器人.成功(_返回.returnCode)) {
			刷新信息(_返回.data);
			return 返回值.成功;
		}
		return 机器人返回值.创建(_返回.returnCode);
	}

	public String 取名片() {
		检查信息(名片);
		return 名片;
	}

	public 返回值 置名片(String _群名片) {
		return 机器人返回值.创建(取上下文().取实现().getHttpApi().setGroupCard(取群().取群号(), 取账号(), _群名片).returnCode);
	}

	public Boolean 有不良记录() {
		检查信息(有不良记录);
		return 有不良记录;
	}

	public String 取地区() {
		检查信息(地区);
		return 地区;
	}

	public Boolean 是管理员() {
		检查信息(是管理员);
		return 是管理员;
	}

	public Boolean 是群主() {
		检查信息(是群主);
		return 是群主;
	}

	public String 取专属头衔() {
		检查信息(专属头衔);
		return 专属头衔;
	}

	public 返回值 置专属头衔(String _头衔) {
		return 机器人返回值.创建(取上下文().取实现().getHttpApi().setGroupSpecialTitle(取群().取群号(), 取账号(), _头衔).returnCode);
	}

	public Long 取专属头衔过期时间() {
		检查信息(专属头衔过期时间);
		return 专属头衔过期时间;
	}

	public Long 取上次发言时间() {
		检查信息(上次发言时间);
		return 上次发言时间;
	}

}