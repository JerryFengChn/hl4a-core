package 间.加密.对称;

public abstract class 对称加密 {

	public abstract 算法 取算法();

	public enum 算法 {

		/**
		 * 默认的AES加密方式：AES/CBC/PKCS5Padding
		 */
		AES("AES"), ARCFOUR("ARCFOUR"), Blowfish("Blowfish"),
		/**
		 * 默认的DES加密方式：DES/ECB/PKCS5Padding
		 */
		DES("DES"),
		/**
		 * 3DES算法，默认实现为：DESede/CBC/PKCS5Padding
		 */
		DESede("DESede"), RC2("RC2"),

		PBEWithMD5AndDES("PBEWithMD5AndDES"), PBEWithSHA1AndDESede("PBEWithSHA1AndDESede"), PBEWithSHA1AndRC2_40(
				"PBEWithSHA1AndRC2_40");

		private String 算法;

		算法(String _算法) {

			算法 = _算法;
		}

	}

}