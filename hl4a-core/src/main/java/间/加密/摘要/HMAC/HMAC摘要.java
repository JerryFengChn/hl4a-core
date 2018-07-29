package 间.加密.摘要.HMAC;

import 间.工具.文件;
import 间.工具.断言;
import 间.工具.流;

import javax.crypto.Mac;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public abstract class HMAC摘要 {

	private Mac 加密;
	private byte[] 密码;
	private byte[] 向量;
	private boolean 已初始化 = false;

	private HMAC摘要() {

		try {
			加密 = Mac.getInstance(取算法().算法);
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException(e);
		}
	}

	public HMAC摘要(byte[] _密码) {
		this();
		置密码(_密码);
	}

	public HMAC摘要(byte[] _密码, byte[] _向量) {

		this();
		置向量(_向量);
	}

	public void 置密码(byte[] _密码) {

		密码 = _密码;
		已初始化 = false;
	}

	public void 置向量(byte[] _向量) {

		向量 = _向量;
		已初始化 = false;
	}

	public abstract 算法 取算法();

	public byte[] 加密(byte[] _密码, 文件 _文件) {

		return 加密(_密码, 流.输入.文件(_文件));
	}

	public byte[] 加密(byte[] _密码, byte[] _字节) {

		return 加密(_密码, 流.输入.字节(_字节));
	}

	public byte[] 加密(byte[] _密码, InputStream _输入流) {

		断言.不为空(_输入流, "输入流为空");
		try {
			if (!已初始化 && 密码 != null) {
				try {
					if (向量 == null) {
						加密.init(new SecretKeySpec(_密码, 取算法().算法));
					} else {
						加密.init(new SecretKeySpec(_密码, 取算法().算法), new IvParameterSpec(向量));
					}
					已初始化 = true;
				} catch (InvalidKeyException | InvalidAlgorithmParameterException ignored) {
				}
			}
			byte[] _缓存 = new byte[流.中];
			int _剩余 = 0;
			while ((_剩余 = _输入流.read(_缓存)) != -1) {
				加密.update(_缓存, 0, _剩余);
			}
			return 加密.doFinal();
		} catch (IOException _错误) {
			throw new RuntimeException(_错误);
		} finally {
			加密.reset();
		}
	}

	public enum 算法 {

		HMacMD5("HmacMD5"), HMacSHA1("HmacSHA1"), HMacSHA256("HmacSHA256"), HMacSHA384("HmacSHA384"), HMacSHA512(
				"HmacSHA512");

		private String 算法;

		算法(String _算法) {

			算法 = _算法;
		}

	}


}
