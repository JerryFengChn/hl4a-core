package 间.加密.摘要.HMAC;

public class HMacMD5 extends HMAC摘要 {

	public HMacMD5(byte[] _密码) {

		super(_密码);
	}

	public HMacMD5(byte[] _密码, byte[] _向量) {

		super(_密码, _向量);
	}

	@Override
	public 算法 取算法() {

		return 算法.HMacMD5;
	}

}
