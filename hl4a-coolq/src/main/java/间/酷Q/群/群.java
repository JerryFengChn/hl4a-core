package 间.酷Q.群;

import cc.moecraft.icq.sender.returndata.ReturnData;
import cc.moecraft.icq.sender.returndata.ReturnListData;
import cc.moecraft.icq.sender.returndata.returnpojo.get.RGroupDetail;
import cc.moecraft.icq.sender.returndata.returnpojo.get.RGroupMemberInfo;
import cc.moecraft.icq.sender.returndata.returnpojo.send.RMessageReturnData;
import lombok.EqualsAndHashCode;
import 间.接口.返回值;
import 间.酷Q.内容.信息;
import 间.酷Q.机器人;
import 间.酷Q.机器人返回值;

import java.util.List;

@EqualsAndHashCode
public class 群 {

	private transient 机器人 上下文;

	private Long 群号;
	private transient String 群名;
	private transient Integer 管理员数;
	private transient Integer 最大管理员数;
	private transient 群员 群主;

	private transient 群员列表<群员> 所有管理员;
	private transient Long 创建时间;
	private transient String 介绍;
	private transient 群员列表<群员> 所有群员;
	private transient Integer 人数;
	private transient Integer 最大人数;

	public 群(机器人 _上下文, Long _群号, String _群名) {
		this(_上下文, _群号, _群名, true);
	}

	public 群(机器人 _上下文, Long _群号, String _群名, boolean _自动刷新) {
		群号 = _群号;
		群名 = _群名;
		上下文 = _上下文;
		if (_自动刷新) 刷新信息();
	}

	public 机器人 取上下文() {
		return 上下文;
	}

	public void 刷新信息(RGroupDetail _信息) {

		群名 = _信息.getGroupName();
		管理员数 = _信息.getAdminCount().intValue();
		最大管理员数 = _信息.getMaxAdminCount().intValue();

		if (群主 == null || !群主.取账号().equals(_信息.getOwnerId())) {
			群主 = new 群员(取上下文(), _信息.getOwnerId(), this);
		}

		if (所有管理员 == null || 所有管理员.长度() != _信息.getAdmins().size()) {

			所有管理员 = new 群员列表(取上下文());

			for (RGroupDetail.Admin _单个管理 : _信息.getAdmins()) {
				群员 _单个 = new 群员(取上下文(), _单个管理.getUserId(), this);
				_单个.刷新信息(_单个管理.getNickname(), null, null);
				所有管理员.添加(_单个);
			}

		} else if (所有管理员 != null) {
			List<RGroupDetail.Admin> _所有 = _信息.getAdmins();
			for (int _键值 = 0; _键值 < _所有.size(); _键值++) {
				if (!_所有.get(_键值).getUserId().equals(所有管理员.读取(_键值).取账号())) {
					所有管理员.设置(_键值, new 群员(取上下文(), _所有.get(_键值).getUserId(), this));
				}
			}

		}

	}

	public 返回值 刷新群员() {

		ReturnListData<RGroupMemberInfo> _信息 = 取上下文().取实现().getHttpApi().getGroupMemberList(群号);

		if (机器人.成功(_信息.returnCode)) {
			刷新群员(_信息.data);
			return 返回值.成功;
		}

		return 机器人返回值.创建(_信息.returnCode);


	}

	public void 刷新群员(List<RGroupMemberInfo> _群员) {

		所有群员 = new 群员列表(取上下文());
		for (RGroupMemberInfo _单个信息 : _群员) {
			群员 _单个 = new 群员(取上下文(), this, _单个信息);
			所有群员.添加(_单个);
		}

	}

	public 返回值 刷新信息() {

		ReturnData<RGroupDetail> _信息 = 取上下文().取实现().getHttpApi().getGroupInfo(群号);

		if (机器人.成功(_信息.returnCode)) {
			刷新信息(_信息.data);
			return 返回值.成功;
		}

		return 机器人返回值.创建(_信息.returnCode);

	}

	public synchronized <内容> 内容 检查信息(内容 _检查) {

		if (_检查 == null) {
			刷新信息();
		}

		return _检查;

	}

	public synchronized <内容> 内容 检查群员(内容 _检查) {

		if (_检查 == null) {
			刷新群员();
		}

		return _检查;

	}

	public String 取介绍() {
		检查信息(介绍);
		return 介绍;
	}

	public int 取人数() {
		检查信息(人数);
		return 人数;
	}

	public int 取最大人数() {
		检查信息(最大人数);
		return 最大人数;
	}

	public int 取最大管理员数() {
		检查信息(最大管理员数);
		return 最大管理员数;
	}

	public int 取管理员数() {
		检查信息(管理员数);
		return 管理员数;
	}


	public 群员 取群主() {
		检查信息(群主);
		return 群主;
	}

	public 群员列表 取所有管理员() {
		检查信息(所有管理员);
		return 所有管理员;
	}

	public Long 取创建时间() {
		检查信息(创建时间);
		return 创建时间;
	}

	public 群员列表 取所有群员() {
		检查群员(所有群员);
		return 所有群员;
	}

	public 返回值<信息> 发送信息(String _信息) {
		ReturnData<RMessageReturnData> _结果 = 取上下文().取实现().getHttpApi().sendGroupMsg(群号, _信息);
		return 机器人返回值.创建(_结果.returnCode, 机器人.成功(_结果.returnCode) ? new 信息(取上下文(), _结果.data.messageId, _信息,
				取上下文().取当前用户()) : null);
	}

	public 返回值 全员禁言() {
		return 全员禁言(true);
	}

	public 返回值 解除全员禁言() {
		return 全员禁言(false);
	}

	public 返回值 全员禁言(boolean _禁言) {
		return 机器人返回值.创建(取上下文().取实现().getHttpApi().setGroupWholeBan(群号, _禁言).returnCode);
	}

	public 返回值 踢出(Long _账号) {
		return 踢出(_账号, false);
	}

	public 返回值 踢出(Long _账号, boolean _禁止加入) {
		//	return 机器人返回值.创建(取上下文().取实现().getHttpApi().sendReturnRaw("set_group_kick", "user_id", _账号, "group_id", 取群号
		// (), "reject_add_request", _禁止加入));
		return 机器人返回值.创建(取上下文().取实现().getHttpApi().setGroupKick(群号, _账号, _禁止加入));
	}

	public 返回值 退出() {
		return 退出(false);
	}

	public 返回值 退出(boolean _解散) {
		return 机器人返回值.创建(取上下文().取实现().getHttpApi().setGroupLeave(取群号(), _解散).returnCode);
	}

	public Long 取群号() {
		return 群号;
	}

	public String 取群名() {
		return 群名;
	}

}
