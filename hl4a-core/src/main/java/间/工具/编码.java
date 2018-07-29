package 间.工具;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.Charset;

public enum 编码 {
	;

	public static Charset UTF8 = Charset.forName("UTF8");
	public static Charset GBK = Charset.forName("GBK");
	public static Charset UNICODE = Charset.forName("Unicode");

	public static Charset 取编码(String _编码) {

		return Charset.forName(_编码);
	}

	public enum 链接 {
		;

		public static String 编码(String _内容) {

			return 编码(_内容, UTF8); // java默认unicode
		}

		public static String 编码(String _内容, Charset _编码) {

			try {
				return URLEncoder.encode(_内容, _编码.name());
			} catch (UnsupportedEncodingException e) {
				throw new RuntimeException(e);
			}
		}

		public static String 解码(String _字符) {

			return 编码(_字符, UTF8);
		}

		public static String 解码(String _字符, Charset _编码) {

			try {
				return URLDecoder.decode(_字符, _编码.name());
			} catch (UnsupportedEncodingException e) {
				throw new RuntimeException(e);
			}
		}


	}

	public enum 二进制 {
		;

		public static String 编码(byte[] _字节) {

			return 进制.编码(2, _字节);
		}

		public static byte[] 解码(String _字符) {

			return 进制.解码(2, _字符);
		}

	}

	public enum 十六进制 {
		;

		public static String 编码(byte[] _字节) {

			return 进制.编码(16, _字节);
		}

		public static byte[] 解码(String _字符) {

			return 进制.解码(16, _字符);
		}

	}

	public enum Unicode {
		;

		public static String 编码(String _字符) {

			StringBuilder str = new StringBuilder();
			if ((_字符 == null) || (_字符.trim().equals(""))) return str.toString();
			for (int i = 0; i < _字符.length(); i++) {
				byte[] bytes = String.valueOf(_字符.charAt(i)).getBytes();
				String s4;
				if (bytes.length == 1) {
					s4 = String.valueOf(_字符.charAt(i));
				} else {
					int ch = _字符.charAt(i);
					s4 = "\\u" + Integer.toHexString(ch);
				}
				str.append(s4);
			}
			return str.toString();
		}

		public static String 解码(String _内容) {

			char aChar;
			int len = _内容.length();
			StringBuilder outBuffer = new StringBuilder(len);
			for (int x = 0; x < len; ) {
				aChar = _内容.charAt(x++);
				if (aChar == '\\') {
					aChar = _内容.charAt(x++);
					if (aChar == 'u') {
						int value = 0;
						for (int i = 0; i < 4; i++) {
							aChar = _内容.charAt(x++);
							switch (aChar) {
								case '0':
								case '1':
								case '2':
								case '3':
								case '4':
								case '5':
								case '6':
								case '7':
								case '8':
								case '9':
									value = (value << 4) + aChar - '0';
									break;
								case 'a':
								case 'b':
								case 'c':
								case 'd':
								case 'e':
								case 'f':
									value = (value << 4) + 10 + aChar - 'a';
									break;
								case 'A':
								case 'B':
								case 'C':
								case 'D':
								case 'E':
								case 'F':
									value = (value << 4) + 10 + aChar - 'A';
									break;
								default:
									return null;
							}

						}
						outBuffer.append((char) value);
					} else {
						switch (aChar) {
							case 't':
								aChar = '\t';
								break;
							case 'r':
								aChar = '\r';
								break;
							case 'n':
								aChar = '\n';
								break;
							case 'f':
								aChar = '\f';
								break;
						}
						outBuffer.append(aChar);
					}
				} else {
					outBuffer.append(aChar);
				}

			}
			return outBuffer.toString();

		}

	}

	public enum Base64 {
		;

		public static String 编码(byte[] _字节) {

			if (_字节 == null) return null;
			return new String(编码字节(_字节));
		}

		public static byte[] 解码(String _字符) {

			if (_字符 == null) return null;
			return 解码字节(_字符.getBytes());
		}

		public static byte[] 编码字节(byte[] byteData) {

			if (byteData == null) return null;
			int iSrcIdx;
			int iDestIdx;
			byte[] byteDest = new byte[((byteData.length + 2) / 3) * 4];
			for (iSrcIdx = 0, iDestIdx = 0; iSrcIdx < byteData.length - 2; iSrcIdx += 3) {
				byteDest[iDestIdx++] = (byte) ((byteData[iSrcIdx] >>> 2) & 63);
				byteDest[iDestIdx++] = (byte) ((byteData[iSrcIdx + 1] >>> 4) & 15 | (byteData[iSrcIdx] << 4) & 63);
				byteDest[iDestIdx++] = (byte) ((byteData[iSrcIdx + 2] >>> 6) & 3 | (byteData[iSrcIdx + 1] << 2) & 63);
				byteDest[iDestIdx++] = (byte) (byteData[iSrcIdx + 2] & 63);
			}
			if (iSrcIdx < byteData.length) {
				byteDest[iDestIdx++] = (byte) ((byteData[iSrcIdx] >>> 2) & 63);
				if (iSrcIdx < byteData.length - 1) {
					byteDest[iDestIdx++] = (byte) ((byteData[iSrcIdx + 1] >>> 4) & 15 | (byteData[iSrcIdx] << 4) & 63);
					byteDest[iDestIdx++] = (byte) ((byteData[iSrcIdx + 1] << 2) & 63);
				} else byteDest[iDestIdx++] = (byte) ((byteData[iSrcIdx] << 4) & 63);
			}
			for (iSrcIdx = 0; iSrcIdx < iDestIdx; iSrcIdx++) {
				if (byteDest[iSrcIdx] < 26) byteDest[iSrcIdx] = (byte) (byteDest[iSrcIdx] + 'A');
				else if (byteDest[iSrcIdx] < 52) byteDest[iSrcIdx] = (byte) (byteDest[iSrcIdx] + 'a' - 26);
				else if (byteDest[iSrcIdx] < 62) byteDest[iSrcIdx] = (byte) (byteDest[iSrcIdx] + '0' - 52);
				else if (byteDest[iSrcIdx] < 63) byteDest[iSrcIdx] = '+';
				else byteDest[iSrcIdx] = '/';
			}

			for (; iSrcIdx < byteDest.length; iSrcIdx++)
				byteDest[iSrcIdx] = '=';

			return byteDest;
		}

		public static byte[] 解码字节(byte[] byteData) throws IllegalArgumentException {

			if (byteData == null) return null;

			int iSrcIdx;
			int reviSrcIdx;
			int iDestIdx;
			byte[] byteTemp = new byte[byteData.length];
			reviSrcIdx = byteData.length;
			while (reviSrcIdx - 1 > 0 && byteData[reviSrcIdx - 1] == '=') {
				reviSrcIdx--;
			}

			if (reviSrcIdx - 1 == 0) {
				return null;
			}

			byte byteDest[] = new byte[((reviSrcIdx * 3) / 4)];
			for (iSrcIdx = 0; iSrcIdx < reviSrcIdx; iSrcIdx++) {
				if (byteData[iSrcIdx] == '+') byteTemp[iSrcIdx] = 62;
				else if (byteData[iSrcIdx] == '/') byteTemp[iSrcIdx] = 63;
				else if (byteData[iSrcIdx] < '0' + 10) byteTemp[iSrcIdx] = (byte) (byteData[iSrcIdx] + 52 - '0');
				else if (byteData[iSrcIdx] < ('A' + 26)) byteTemp[iSrcIdx] = (byte) (byteData[iSrcIdx] - 'A');
				else if (byteData[iSrcIdx] < 'a' + 26) byteTemp[iSrcIdx] = (byte) (byteData[iSrcIdx] + 26 - 'a');
			}
			for (iSrcIdx = 0, iDestIdx = 0; iSrcIdx < reviSrcIdx && iDestIdx < ((byteDest.length / 3) * 3); iSrcIdx += 4) {
				byteDest[iDestIdx++] = (byte) ((byteTemp[iSrcIdx] << 2) & 0xFC | (byteTemp[iSrcIdx + 1] >>> 4) & 0x03);
				byteDest[iDestIdx++] =
						(byte) ((byteTemp[iSrcIdx + 1] << 4) & 0xF0 | (byteTemp[iSrcIdx + 2] >>> 2) & 0x0F);
				byteDest[iDestIdx++] = (byte) ((byteTemp[iSrcIdx + 2] << 6) & 0xC0 | byteTemp[iSrcIdx + 3] & 0x3F);
			}
			if (iSrcIdx < reviSrcIdx) {
				if (iSrcIdx < reviSrcIdx - 2) {

					byteDest[iDestIdx++] =
							(byte) ((byteTemp[iSrcIdx] << 2) & 0xFC | (byteTemp[iSrcIdx + 1] >>> 4) & 0x03);
					byteDest[iDestIdx++] =
							(byte) ((byteTemp[iSrcIdx + 1] << 4) & 0xF0 | (byteTemp[iSrcIdx + 2] >>> 2) & 0x0F);
				} else if (iSrcIdx < reviSrcIdx - 1) {

					byteDest[iDestIdx++] =
							(byte) ((byteTemp[iSrcIdx] << 2) & 0xFC | (byteTemp[iSrcIdx + 1] >>> 4) & 0x03);
				} else {
					return null;
					// throw new IllegalArgumentException("Warning: 1 input bytes left to process.
					// This was not Base64 input");
				}
			}
			return byteDest;
		}

	}

}
