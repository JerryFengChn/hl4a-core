package 间.工具;

import java.math.BigInteger;

public enum 进制 {
	;

	public static String 编码(int _进制, byte[] _字节) {

		return new BigInteger(1, _字节).toString(_进制);
	}

	public static byte[] 解码(int _进制, String _字符) {

		return new BigInteger(_字符, _进制).toByteArray();
	}

}
