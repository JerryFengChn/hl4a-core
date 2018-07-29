package 间.酷Q;

import cc.moecraft.icq.PicqBotX;
import cc.moecraft.icq.sender.returndata.ReturnData;
import cc.moecraft.icq.sender.returndata.ReturnListData;
import cc.moecraft.icq.sender.returndata.returnpojo.get.RLoginInfo;
import 间.工具.线程;
import 间.接口.返回值;
import 间.方法.简单函数;
import 间.酷Q.用户.用户;
import 间.酷Q.群.群列表;

public class 机器人 {

	private PicqBotX 实现;
	private 线程 后台;

	private 用户 当前用户;

	public 机器人(String _IP, int _接收端口, int _发送端口, boolean _调试) {
		实现 = new PicqBotX(_IP, _接收端口, _发送端口, _调试);
		后台 = new 线程((简单函数) () -> {

			try {
				实现.startBot();
			} catch (Exception _e) {
				_e.printStackTrace();
			}

		});
		刷新用户();
	}

	public 机器人(int _接收端口, int _发送端口, boolean _调试) {
		this("127.0.0.1", _接收端口, _发送端口, _调试);
	}

	public static boolean 成功(Long _状态码) {
		return _状态码 != null && 成功(_状态码.intValue());
	}

	public static boolean 成功(Integer _状态码) {
		return _状态码 != null && (_状态码 == 0 || _状态码 == 1);
	}

	public 返回值 刷新用户() {
		ReturnData<RLoginInfo> _返回 = 取实现().getHttpApi().getLoginInfo();
		if (!成功(_返回.returnCode)) return 机器人返回值.创建(_返回.returnCode);
		当前用户 = new 用户(this, _返回.data.userId);
		return 返回值.成功;
	}

	public PicqBotX 取实现() {
		return 实现;
	}

	public 用户 取当前用户() {
		return 当前用户;
	}

	public void 启动() {

		后台.启动();

	}

	public void 暂停() {
		实现.httpPause();
	}

	public void 继续() {
		实现.httpResume();
	}

	public 返回值<群列表> 取群列表() {
		ReturnListData _结果 = 实现.getHttpApi().getGroupList();
		if (!成功(_结果.returnCode)) {
			return 机器人返回值.创建(_结果.returnCode);
		} else {
			return 机器人返回值.创建(_结果.returnCode, new 群列表(this, _结果.data));
		}
	}


}
