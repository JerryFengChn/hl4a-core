package 间.酷Q.内容;

import lombok.EqualsAndHashCode;
import 间.接口.返回值;
import 间.酷Q.机器人;
import 间.酷Q.机器人返回值;
import 间.酷Q.用户.用户;

@EqualsAndHashCode
public class 信息 {

	private Long ID;

	private transient String 内容;
	private transient 间.酷Q.用户.用户 用户;
	private transient 机器人 上下文;

	private 信息(机器人 _上下文, Long _ID) {
		ID = _ID;
		上下文 = _上下文;
	}

	public 信息(机器人 _上下文, Long _ID, String _内容, 用户 _用户) {
		this(_上下文, _ID);
		内容 = _内容;
		用户 = _用户;
	}

	public String 取内容() {
		return 内容;
	}

	public 用户 取用户() {
		return 用户;
	}

	public 机器人 取上下文() {
		return 上下文;
	}

	public 返回值 撤回() {
		return 机器人返回值.创建(上下文.取实现().getHttpApi().deleteMsg(ID).returnCode);
	}

}
