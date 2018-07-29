package 间.网络;

import 间.工具.*;
import 间.收集.I列表;
import 间.收集.I哈希表;
import 间.数据.JSON;
import 间.方法.函数;

import javax.net.ssl.HttpsURLConnection;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;

public class 资源 {

	private HttpURLConnection 连接;
	private byte[] 内容;

	public 资源(HttpURLConnection _连接) {

		连接 = _连接;
	}

	public String 地址() {

		return 连接.getURL().toString();
	}

	public String 模式() {

		return 连接.getRequestMethod();
	}

	public boolean 是HTTPS() {

		return 连接 instanceof HttpsURLConnection;
	}

	public String 请求头(String _名称) {

		return 连接.getRequestProperty(_名称);
	}

	public int 进度(long _当前) {

		return Float.valueOf(((float) (_当前 / 长度()))).intValue() * 100;
	}

	public long 长度() {

		if (!兼容.取是安卓()) {
			return 连接.getContentLengthLong();
		} else {
			String _长度 = 请求头("Content-Length");
			if (_长度 != null) {
				return Long.parseLong(_长度);
			}
			return -1;
		}
	}

	public boolean 成功() {

		return 状态码() >= 200 && 状态码() < 300;
	}

	public boolean 重定向() {

		return HttpURLConnection.getFollowRedirects();
	}

	public int 状态码() {

		try {
			return 连接.getResponseCode();
		} catch (IOException e) {
			return -1;
		}
	}

	public byte[] 字节() {

		if (内容 == null) {
			内容 = 流.读取(流());
		}
		return 内容;
	}

	public InputStream 流() {

		try {
			if (成功())
				return 连接.getInputStream();
			else
				return 连接.getErrorStream();
		} catch (IOException ignored) {
		}
		return null;
	}

	public I哈希表 JSON表() {

		return JSON.解析(文本());
	}

	public I列表 JSON列表() {

		return JSON.解析列表(文本());
	}

	public String 文本() {

		return 字符.转换(字节());
	}

	public void 保存(文件 _输出) {

		保存(_输出, null);
	}

	public void 保存(文件 _输出, 函数 _出错) {

		保存(_输出, null, null);
	}

	public void 保存(文件 _输出, 函数 _进度, 函数 _出错) {

		保存(_输出, _进度, null, null, null);
	}

	public void 保存(文件 _输出, 函数 _进度, 函数 _开始, 函数 _结束, 函数 _出错) {

		OutputStream _流;
		if (状态码() != 206) {
			_流 = 流.输出.文件(_输出);
		} else {
			_流 = 流.输出.文件(_输出, true);
		}
		if (_流 == null) {
			错误.IO("无法保存到: " + _输出);
		}
		try {
			流.非阻塞保存(_流, 内容 == null ? 流() : 流.输入.字节(内容), _进度, _开始, _结束);
			流.关闭(_流);
		} catch (Exception _错误) {
			if (_出错 == null) {
				错误.抛出(_错误);
			} else {
				处理.同步(_出错);
			}
		}
	}

}
