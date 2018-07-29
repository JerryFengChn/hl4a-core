package 间.加密.摘要;

import 间.工具.文件;
import 间.工具.断言;
import 间.工具.流;

import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 使用MessageDigest进行摘要加密
 */

public abstract class 摘要加密 {

	private MessageDigest 加密;

	public 摘要加密() {

		try {
			加密 = MessageDigest.getInstance(取算法().算法);
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException(e);
		}
	}

	public abstract 算法 取算法();

	public byte[] 加密(文件 _文件) {

		return 加密(流.输入.文件(_文件));
	}

	public byte[] 加密(byte[] _字节) {

		return 加密(流.输入.字节(_字节));
	}

	public byte[] 加密(InputStream _输入流) {

		断言.不为空(_输入流, "输入流为空");
		try {
			加密.reset();
			byte[] _缓存 = new byte[流.中];
			int _剩余 = 0;
			while ((_剩余 = _输入流.read(_缓存)) != -1) {
				加密.update(_缓存, 0, _剩余);
			}
			return 加密.digest();
		} catch (IOException _错误) {
			throw new RuntimeException(_错误);
		}
	}

	public enum 算法 {

		MD2("MD2"), MD5("MD5"), SHA1("SHA-1"), SHA256("SHA-256"), SHA384("SHA-384"), SHA512("SHA-512");

		private String 算法;

		算法(String _算法) {

			算法 = _算法;
		}

	}

}