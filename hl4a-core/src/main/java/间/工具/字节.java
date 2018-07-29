package 间.工具;

import java.io.*;

public enum 字节 {
	;

	public static byte[] 序列化(Object _对象) {

		try {
			ByteArrayOutputStream _输出 = 流.输出.字节();
			ObjectOutputStream _流 = new ObjectOutputStream(_输出);
			_流.writeObject(_对象);
			return _输出.toByteArray();
		} catch (Exception _错误) {
			错误.抛出(_错误);
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public static <类型> 类型 反序列化(byte[] _字节) {

		ByteArrayInputStream _输入 = 流.输入.字节(_字节);
		ObjectInputStream _流 = null;
		try {
			_流 = new ObjectInputStream(_输入);
			return (类型) _流.readObject();
		} catch (IOException | ClassNotFoundException e) {
			throw new RuntimeException(e);
		}
	}

	public static byte[] 读取(String _文件) {

		return 读取(new 文件(_文件));
	}

	public static byte[] 读取(文件 _文件) {

		InputStream _输入流 = 流.输入.文件(_文件);
		byte[] _字节 = 读取(_输入流);
		流.关闭(_输入流);
		return _字节;

	}

	public static byte[] 读取(InputStream _输入流) {

		return 流.读取(_输入流);

	}

	public static void 保存(String _文件, byte[] _字节) {

		保存(new 文件(_文件), _字节);

	}

	public static void 保存(文件 _文件, byte[] _字节) {

		OutputStream _输出流 = 流.输出.文件(_文件);

		流.保存(_输出流, _字节);

		流.关闭(_输出流);

	}

	public static void 追加(String _文件, byte[] _字节) {

		追加(new 文件(_文件), _字节);

	}

	public static void 追加(文件 _文件, byte[] _字节) {

		OutputStream _输出流 = 流.输出.文件(_文件, true);

		流.保存(_输出流, _字节);

		流.关闭(_输出流);

	}

	public static void 保存(OutputStream _输出流, byte[] _字节) {

		if (_输出流 == null) return;

		流.保存(_输出流, _字节);

	}

	public static byte[] 转换(String _文本) {

		return _文本.getBytes();
	}

}

