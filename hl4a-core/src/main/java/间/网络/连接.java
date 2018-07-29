package 间.网络;

import org.json.JSONObject;
import 间.工具.*;
import 间.收集.哈希表;
import 间.收集.有序列表;
import 间.收集.有序哈希表;

import javax.net.ssl.*;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigInteger;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.Socket;
import java.net.URL;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import java.util.Iterator;
import java.util.Map;
import java.util.UUID;

@SuppressWarnings({"ALL", "unchecked"})
public class 连接 {

	private static 有序列表<String> 所有模式 = new 有序列表<>("OPTIONS", "GET", "HEAD", "POST", "PUT", "DELETE", "TRACE", "PATCH");

	static {
		try {
			HttpsURLConnection.setDefaultSSLSocketFactory(new 连接工厂());
			HttpsURLConnection.setDefaultHostnameVerifier(new 信任域名());
		} catch (Exception ignored) {
		}
	}

	private 哈希表<String, String> 请求头 = new 哈希表<>();
	private 哈希表<String, String> Cookie表 = new 哈希表<>();
	private 哈希表<String, String> 参数表 = new 哈希表<>();
	private 哈希表<String, 文件> 文件表 = new 哈希表<>();
	private boolean 是表单 = false;
	private 有序哈希表<String, String> 链接参数 = new 有序哈希表<>();
	private String 模式;
	private String 标识;
	private String 地址;
	private boolean JSON输出 = false;
	private String JSON;

	public 连接() {

		this(null);
	}

	public 连接(String _地址) {

		this(_地址, "GET");
	}

	public 连接(String _地址, String _模式) {

		模式(_模式);
		地址(_地址);
		标识 = UUID.randomUUID().toString();
	}

	private static String 编码(String _内容) {

		return 编码.链接.编码(_内容);
	}

	public static 资源 GET(String _地址) {

		return new 连接(_地址, "GET").同步();
	}

	public static 资源 GET(String _地址, Map<String, String> _链接参数) {

		return new 连接(_地址, "GET").链接参数(_链接参数).同步();
	}

	public static 资源 POST(String _地址, Map<String, String> _参数) {

		return new 连接(_地址, "POST").参数(_参数).同步();
	}

	public 连接 地址(String _地址) {

		地址 = _地址;
		return this;
	}

	public 连接 模式(String _模式) {

		_模式 = _模式.toUpperCase();
		if (所有模式.检查(_模式)) {
			模式 = _模式;
		}
		return this;
	}

	public 连接 是表单(boolean _状态) {

		是表单 = _状态;
		return this;
	}

	public 连接 标识(String _UA) {

		请求头("User-Agent", _UA);
		return this;
	}

	public 连接 断点(long _开始) {

		请求头("RANGE", BigInteger.valueOf(_开始).toString() + "-");
		return this;
	}

	public 连接 请求头(String _名称, String _内容) {

		请求头.设置(_名称, _内容);
		return this;
	}

	public 连接 链接参数(String _名称, String _内容) {

		链接参数.设置(_名称, _内容);
		return this;
	}

	public 连接 链接参数(Map<String, String> _内容) {

		链接参数.putAll(_内容);
		return this;
	}

	public 连接 参数(String _名称, String _内容) {

		参数表.设置(_名称, _内容);
		return this;
	}

	public 连接 参数(Map<String, String> _内容) {

		参数表.putAll(_内容);
		return this;
	}

	public 连接 Cookie(String _名称, String _内容) {

		Cookie表.设置(_名称, _内容);
		return this;
	}

	public 连接 文件(String _名称, 文件 _文件) {

		if (!_文件.取是文件()) 错误.参数("文件不存在:" + _文件);
		文件表.设置(_名称, _文件);
		return this;
	}

	public void JSON(Map _表) {

		JSON(new JSONObject(_表).toString());
	}

	public void JSON(String _内容) {

		JSON = _内容;
	}

	public void JSON输出(boolean _状态) {

		JSON输出 = _状态;
	}

	public 资源 同步() {

		try {

			if (!Cookie表.isEmpty()) {
				请求头("Cookie", 转换Cookie(Cookie表));
			}


			StringBuilder _合并 = new StringBuilder();
			有序哈希表<String, String> _参数 = new 有序哈希表<>();
			if (字符.是否出现(地址, "?")) {
				String _编码 = 字符.截取开始(地址, "?", null);
				String[] _所有 = 字符.分割(_编码, "&");
				for (String _单个 : _所有) {
					if (!字符.是否出现(_单个, "=")) {
						_参数.设置(_单个, null);
						continue;
					}
					String _键值 = 字符.截取开始(_单个, null, "=");
					String _内容 = 字符.截取开始(_单个, "=", null);
					_参数.设置(_键值, _内容);
				}
				_合并.append(字符.截取开始(地址, null, "?"));
			} else {
				_合并.append(地址);
			}
			_参数.putAll(链接参数);
			if (!_参数.isEmpty()) {
				_合并.append("?");
				boolean _不是第一个 = false;
				for (Map.Entry<String, String> _单个 : _参数.entrySet()) {
					if (_不是第一个) {
						_合并.append("&");
					} else {
						_不是第一个 = true;
					}
					_合并.append(编码(_单个.getKey()));
					_合并.append("=");
					_合并.append(编码(_单个.getValue()));
				}
			}

			HttpURLConnection 连接 = (HttpURLConnection) new URL(_合并.toString()).openConnection();


			连接.setRequestMethod(模式);

			if (JSON输出) {

				请求头("Content-Type", "application/json;boundary=" + 标识);

			} else if (是表单) {

				请求头("Content-Type", "multipart/form-datap;boundary=" + 标识);

			}

			for (Map.Entry<String, String> s : 请求头.entrySet()) {

				连接.setRequestProperty(s.getKey(), s.getValue());

			}

			if (!"GET".equals(模式) || "HEAD".equals(模式)) {

				连接.setDoOutput(true);

				OutputStream _输出 = 连接.getOutputStream();

				if (JSON输出) {
					if (JSON == null && 参数表.长度() > 0) {
						JSON(参数表);
					}
					if (JSON != null) {
						System.out.println(JSON);
						_输出.write(JSON.getBytes());
					}
				} else {
					byte[] _分隔 = ("--" + 标识).getBytes();
					byte[] _换行 = "\r\n".getBytes();
					if (!参数表.isEmpty()) {

						for (Map.Entry<String, String> _单个 : 参数表.entrySet()) {
							_输出.write(_换行);
							_输出.write(_分隔);
							_输出.write(_换行);
							_输出.write("Content-Disposition: form-data;".getBytes());
							_输出.write(("name=\"" + 编码(_单个.getKey()) + "\"").getBytes());
							_输出.write(_换行);
							_输出.write(编码(_单个.getValue()).getBytes());
						}
					}
					if (!文件表.isEmpty()) {
						for (Map.Entry<String, 文件> _单个 : 文件表.entrySet()) {
							文件 _文件 = _单个.getValue();
							_输出.write(_换行);
							_输出.write(_分隔);
							_输出.write(_换行);
							_输出.write("Content-Disposition: form-data;".getBytes());
							_输出.write(("name=\"" + 编码(_单个.getKey()) + "\";").getBytes());
							_输出.write(("filename=\"" + 编码(_文件.getName()) + "\";").getBytes());
							_输出.write(_换行);
							流.保存(_输出, 流.输入.文件(_文件));
						}
					}
					_输出.write(_换行);
					_输出.write(_分隔);
					_输出.write("--".getBytes());
				}
				_输出.flush();
				_输出.close();
			}
			return new 资源(连接);
		} catch (Exception _错误) {
			错误.抛出(_错误);
		}

		return new 资源(null);

	}

	private String 转换Cookie(哈希表 _内容) {

		switch (_内容.长度()) {
			case 0:
				return "";
			case 1:
				Map.Entry<String, String> _一个 = (Map.Entry<String, String>) _内容.entrySet().iterator().next();
				return _一个.getKey() + "=" + _内容;
			default:
				StringBuilder _返回 = new StringBuilder();
				@SuppressWarnings("unchecked") Iterator<Map.Entry<String, String>> _所有 = _内容.entrySet().iterator();
				Map.Entry<String, String> _第一个 = _所有.next();
				_返回.append("&").append(编码(_第一个.getKey())).append("=").append(编码(_第一个.getValue()));
				while (_所有.hasNext()) {
					Map.Entry<String, String> _单个 = _所有.next();
					_返回.append("&").append(编码(_单个.getKey())).append("=").append(编码(_单个.getValue()));
				}
				return _返回.toString();
		}
	}

	private static class 信任证书 implements X509TrustManager {

		@Override
		public void checkClientTrusted(X509Certificate[] chain, String authType) {

		}

		@Override
		public void checkServerTrusted(X509Certificate[] chain, String authType) {

		}

		@Override
		public X509Certificate[] getAcceptedIssuers() {

			return new X509Certificate[]{};
		}
	}

	private static class 信任域名 implements HostnameVerifier {

		@Override
		public boolean verify(String hostname, SSLSession session) {

			return true;// 直接返回true
		}
	}

	private static class 连接工厂 extends SSLSocketFactory {

		public static String SSL = "SSL";
		public static String SSLv2 = "SSLv2";
		public static String SSLv23 = "SSLv2.3";
		public static String SSLv3 = "SSLv3";

		public static String TLS = "TLS";
		public static String TLSv1 = "TLSv1";
		public static String TLSv11 = "TLSv1.1";
		public static String TLSv12 = "TLSv1.2";

		private static String[] 开启策略 = {SSLv3, TLSv1, TLSv11, TLSv12};
		// Android低版本不重置的话某些SSL访问就会失败

		private SSLSocketFactory 工厂;

		public 连接工厂() {

			super();
			try {
				SSLContext _上下文 = SSLContext.getInstance("SSL");
				_上下文.init(null, new X509TrustManager[]{new 信任证书()}, new SecureRandom());
				工厂 = _上下文.getSocketFactory();
			} catch (Exception ignored) {
			}
		}

		@Override
		public String[] getDefaultCipherSuites() {

			return 工厂.getDefaultCipherSuites();
		}

		@Override
		public String[] getSupportedCipherSuites() {

			return 工厂.getSupportedCipherSuites();
		}

		@Override
		public SSLSocket createSocket(Socket s, String host, int port, boolean autoClose) throws IOException {

			SSLSocket _连接 = (SSLSocket) 工厂.createSocket(s, host, port, autoClose);
			重置策略(_连接);
			return _连接;
		}

		@Override
		public Socket createSocket(String host, int port) throws IOException {

			SSLSocket _连接 = (SSLSocket) 工厂.createSocket(host, port);
			重置策略(_连接);
			return _连接;
		}

		@Override
		public Socket createSocket(String host, int port, InetAddress localHost, int localPort) throws IOException {

			SSLSocket _连接 = (SSLSocket) 工厂.createSocket(host, port, localHost, localPort);
			重置策略(_连接);
			return _连接;
		}

		@Override
		public Socket createSocket(InetAddress host, int port) throws IOException {

			SSLSocket _连接 = (SSLSocket) 工厂.createSocket(host, port);
			重置策略(_连接);
			return _连接;
		}

		@Override
		public Socket createSocket(InetAddress address, int port, InetAddress localAddress, int localPort) throws IOException {

			SSLSocket _连接 = (SSLSocket) 工厂.createSocket(address, port, localAddress, localPort);
			重置策略(_连接);
			return _连接;
		}

		private void 重置策略(SSLSocket _连接) {

			_连接.setEnabledProtocols(开启策略);
		}

	}

}
