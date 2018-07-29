package 间.酷Q;

import 间.接口.返回值;

public class 机器人返回值<内容> extends 间.接口.返回值<内容> {

	private Integer 状态码;

	public 机器人返回值(Integer _状态码) {
		状态码 = _状态码;
		置状态(机器人.成功(_状态码));
	}


	public static 返回值 创建(Long _状态码) {
		return new 机器人返回值(_状态码 == null ? -1 : _状态码.intValue());
	}

	public static <内容> 返回值<内容> 创建(Long _状态码, 内容 _内容) {
		return 创建(_状态码).置内容(_内容);
	}

	@Override
	public String 取错误信息() {

		switch (状态码) {
			case -1:
				return "连接失败";
			case 0:
				return "同时 status 为 ok，表示操作成功";
			case 1:
				return "异步执行";
			case 100:
				return "参数缺失或参数无效，通常是因为没有传入必要参数";
			case 102:
				return "酷 Q 函数返回的数据无效，一般是因为传入参数有效但没有权限";
			case 103:
				return "操作失败，一般是因为用户权限不足，或文件系统异常、不符合预期";
			case 104:
				return "由于酷 Q 提供的凭证（Cookie 和 CSRF Token）失效导致请求 QQ 相关接口失败，可尝试清除酷 Q 缓存来解决";
			case 201:
				return "工作线程池未正确初始化（无法执行异步任务）";
		}

		return "未知错误";

	}
}