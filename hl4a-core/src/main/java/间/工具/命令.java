package 间.工具;

import 间.收集.列表;
import 间.方法.函数;

import java.io.*;
import java.util.Map;

public class 命令 {

	private Process 进程 = null;
	private OutputStream 输出;

	/**
	 * 创建一个Shell命令
	 *
	 * @param _命令 要执行的Shell命令
	 */
	public 命令(String... _命令) {

		this(null, _命令);
	}

	/**
	 * 创建一个Shell命令
	 *
	 * @param _环境 Shell环境变量表
	 * @param _命令 要执行的Shell命令
	 **/
	public 命令(Map<String, String> _环境, String... _命令) {

		断言.不为空(_命令, "命令为空");
		断言.不为空(_命令.length != 0, "命令数组长度为0");
		try {
			if (_环境 != null) {
				列表<String> _内容 = new 列表<>();
				for (Map.Entry<String, String> _单个 : _环境.entrySet()) {
					_内容.添加(_单个.getKey() + "=" + _单个.getValue());
				}
				进程 = Runtime.getRuntime().exec(_命令, _内容.到数组(String.class));
			} else {
				进程 = Runtime.getRuntime().exec(_命令);
			}
			输出 = 进程.getOutputStream();
		} catch (Exception _错误) {
			错误.抛出(_错误);
		}
	}

	/**
	 * 等待Shell命令执行完成
	 * 会阻塞线程等待结束 ~
	 **/
	public void 等待() {

		try {
			进程.waitFor();
		} catch (InterruptedException _错误) {
			错误.抛出(_错误);
		}
	}

	/**
	 * @param _命令 要追加执行的命令
	 *            不会等待执行完成哦 ~
	 **/
	public void 执行(String _命令) {

		断言.不为空(_命令, "要追加执行的命令不能为空 ~");
		字符.保存(输出, _命令);
	}

	/**
	 * 读取命令行所有输出
	 * 必须已经执行完成 ~
	 *
	 * @return String 命令行所有输出
	 **/
	public String 读取() {

		InputStream _输入流 = 进程.getInputStream();

		if (_输入流 == null) return null;

		return 字符.读取(_输入流);

	}

	/*** 接收命令行每一行输出并返回所有
	 * 注意会阻塞线程直到执行结束哦 ~
	 * @param _进度 接收每行String参数的函数
	 * @return String 命令行所有输出
	 **/
	public String 读取(final 函数 _进度) {

		StringBuilder _结果 = new StringBuilder();
		while (!已结束()) {
			String _一行;
			BufferedReader _读取 = new BufferedReader(new InputStreamReader(进程.getErrorStream()));
			try {
				while ((_一行 = _读取.readLine()) != null) {
					_结果.append("\n").append(_一行);
					处理.同步(_进度, _一行);
				}
			} catch (IOException _错误) {
				错误.抛出(_错误);
			}
		}
		return _结果.toString();
	}

	/**
	 * 返回Shell命令执行是否成功退出
	 * 会阻塞线程等待结束哦 ~
	 * return boolean 是否成功退出
	 **/
	public boolean 成功() {

		return 间.工具.进程.成功(进程);
	}

	/**
	 * 返回Shell命令是否已执行完 ~
	 *
	 * @return boolean 是否已执行完
	 **/
	public boolean 已结束() {

		return 间.工具.进程.已结束(进程);
	}

	/**
	 * 直接结束命令行
	 * 不管有没有执行完 ~
	 **/
	public void 结束() {

		间.工具.进程.结束(进程);
	}

}
