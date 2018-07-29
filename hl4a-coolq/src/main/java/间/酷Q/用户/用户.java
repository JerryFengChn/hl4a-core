package 间.酷Q.用户;

import cc.moecraft.icq.sender.returndata.ReturnData;
import cc.moecraft.icq.sender.returndata.returnpojo.get.RStrangerInfo;
import cc.moecraft.icq.sender.returndata.returnpojo.send.RMessageReturnData;
import lombok.EqualsAndHashCode;
import 间.接口.返回值;
import 间.酷Q.内容.信息;
import 间.酷Q.机器人;
import 间.酷Q.机器人返回值;

import static 间.接口.返回值.成功;

@EqualsAndHashCode
public class 用户 {

	private transient 机器人 上下文;
	private Long 账号;


	private transient String 名称;
	private transient String 性别;
	private transient Integer 年龄;

	public 用户(机器人 _上下文, Long _账号) {
		上下文 = _上下文;
		账号 = _账号;
	}

	public 机器人 取上下文() {
		return 上下文;
	}

	public <内容> 内容 检查信息(内容 _检查) {
		if (_检查 == null) {
			刷新信息(true);
		}
		return _检查;
	}


	public void 刷新信息(String _名称, String _性别, Integer _年龄) {
		名称 = _名称;
		性别 = 处理性别(_性别);
		年龄 = _年龄;
	}

	private String 处理性别(String _性别) {

		if (_性别 == null || "unknown".equals(_性别)) {
			return "未知";
		} else if ("male".equals(_性别)) {
			return "男";
		} else if ("female".equals(_性别)) {
			return "女";
		}

		return _性别;

	}

	public 返回值 刷新信息(boolean _缓存) {
		ReturnData<RStrangerInfo> _信息 = 取上下文().取实现().getHttpApi().getStrangerInfo(账号, !_缓存);
		if (机器人.成功(_信息.returnCode)) {
			刷新信息(_信息.data.nickname, _信息.data.sex, _信息.data.age.intValue());
			return 成功;
		} else {
			return 机器人返回值.创建(_信息.returnCode);
		}
	}

	public String 取名称() {
		检查信息(名称);
		return 名称;
	}

	public String 取性别() {
		检查信息(性别);
		return 性别;
	}

	public int 取年龄() {
		检查信息(年龄);
		return 年龄;
	}

	public Long 取账号() {
		return 账号;
	}

	public 返回值<信息> 发送信息(String _信息) {
		ReturnData<RMessageReturnData> _返回 = 上下文.取实现().getHttpApi().sendPrivateMsg(账号, _信息);
		return 机器人.成功(_返回.returnCode) ? 机器人返回值.创建(_返回.returnCode, new 信息(上下文, _返回.data.messageId, _信息, 上下文.取当前用户())) :
				机器人返回值.创建(_返回.returnCode);
	}

	public 返回值 点赞() {
		return 点赞(10);
	}

	public 返回值 点赞(int _次数) {
		return 机器人返回值.创建(上下文.取实现().getHttpApi().sendLike(账号, _次数).returnCode);
	}

}
