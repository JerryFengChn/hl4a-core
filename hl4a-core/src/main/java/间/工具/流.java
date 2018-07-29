package 间.工具;

import cn.hutool.core.io.IoUtil;
import cn.hutool.core.io.StreamProgress;
import 间.方法.函数;

import java.io.*;
import java.nio.channels.FileLock;

public enum 流 {
	;

	public static final int 小 = 1024 * 512; // 1k
	public static final int 中 = 512 * 1024; // 512k
	public static final int 大 = 1024 * 1024; // 1m

	public static void 关闭(AutoCloseable _流) {

		if (_流 == null) return;
		try {
			_流.close();
		} catch (Exception ignored) {
		}
	}

	public static FileLock 锁定(FileInputStream _流) {

		if (_流 == null) return null;
		try {
			return _流.getChannel().tryLock();
		} catch (IOException ignored) {
		}
		return null;
	}

	public static FileLock 锁定(FileOutputStream _流) {

		if (_流 == null) return null;
		try {
			return _流.getChannel().tryLock();
		} catch (IOException ignored) {
		}
		return null;
	}

	public static void 解锁(FileLock _锁) {

		if (_锁 == null) return;
		try {
			_锁.release();
		} catch (IOException ignored) {
		}
	}

	public static byte[] 读取(InputStream _流) {

		if (_流 == null) return null;
		return IoUtil.readBytes(_流);
	}

	public static byte[] 读取(InputStream _流, int _长度) {

		if (_流 == null) return null;
		return IoUtil.readBytes(_流, _长度);
	}

	public static String 读取文本(InputStream _流) {

		return 读取文本(_流, "UTF-8");
	}

	public static String 读取文本(InputStream _流, String _格式) {

		if (_流 == null) return null;
		return IoUtil.read(_流, _格式);
	}

	public static void 保存(文件 _地址, byte[] _内容) {

		保存(输出.文件(_地址), _内容);
	}

	public static void 保存(OutputStream _流, byte[] _内容) {

		if (_流 == null) return;
		try {
			_流.write(_内容);
		} catch (IOException ignored) {
		}
	}

	public static void 非阻塞保存(OutputStream _流, InputStream _内容) {

		非阻塞保存(_流, _内容, null);
	}

	public static void 非阻塞保存(OutputStream _流, InputStream _内容, 函数 _进度) {

		非阻塞保存(_流, _内容, _进度, null, null);
	}

	public static void 非阻塞保存(OutputStream _流, InputStream _内容, 函数 _进度, 函数 _开始, 函数 _结束) {

		非阻塞保存(_流, _内容, 流.中, _进度, _开始, _结束);
	}

	public static void 非阻塞保存(OutputStream _流, InputStream _内容, int _缓存) {

		非阻塞保存(_流, _内容, _缓存, null);
	}

	public static void 非阻塞保存(OutputStream _流, InputStream _内容, int _缓存, 函数 _进度) {

		非阻塞保存(_流, _内容, _缓存, _进度, null, null);
	}

	public static void 非阻塞保存(OutputStream _流, InputStream _内容, int _缓存, 函数 _进度, 函数 _开始, 函数 _结束) {

		if (_流 == null || _内容 == null) return;
		IoUtil.copyByNIO(_内容, _流, _缓存, 创建进度(_进度, _开始, _结束));
	}

	public static void 非阻塞保存(文件 _流, InputStream _内容) {

		非阻塞保存(_流, _内容, null);
	}

	public static void 非阻塞保存(文件 _流, InputStream _内容, 函数 _进度) {

		非阻塞保存(_流, _内容, _进度, null, null);
	}

	public static void 非阻塞保存(文件 _流, InputStream _内容, 函数 _进度, 函数 _开始, 函数 _结束) {

		非阻塞保存(_流, _内容, 流.中, _进度, _开始, _结束);
	}

	public static void 非阻塞保存(文件 _流, InputStream _内容, int _缓存) {

		非阻塞保存(_流, _内容, _缓存, null);
	}

	public static void 非阻塞保存(文件 _流, InputStream _内容, int _缓存, 函数 _进度) {

		非阻塞保存(_流, _内容, _缓存, _进度, null, null);
	}

	public static void 非阻塞保存(文件 _流, InputStream _内容, int _缓存, 函数 _进度, 函数 _开始, 函数 _结束) {

		if (_流 == null || _内容 == null) return;
		IoUtil.copyByNIO(_内容, 输出.文件(_流), _缓存, 创建进度(_进度, _开始, _结束));
	}

	public static void 保存(文件 _地址, InputStream _内容) {

		保存(输出.文件(_地址), _内容);
	}

	public static void 保存(OutputStream _流, InputStream _内容) {

		if (_流 == null || _内容 == null) return;
		if (_流 instanceof FileOutputStream && _内容 instanceof FileInputStream) {
			IoUtil.copy((FileInputStream) _内容, (FileOutputStream) _流);
		} else {
			IoUtil.copy(_内容, _流);
		}
	}

	public static void 保存(文件 _地址, InputStream _内容, int _缓存) {

		保存(输出.文件(_地址), _内容, _缓存);
	}

	public static void 保存(OutputStream _流, InputStream _内容, int _缓存) {

		if (_流 == null || _内容 == null) return;
		if (_流 instanceof FileOutputStream && _内容 instanceof FileInputStream) {
			IoUtil.copy(_内容, _流, _缓存);
		} else {
			IoUtil.copy(_内容, _流, _缓存);
		}
	}

	protected static 流进度 创建进度(函数 _事件, 函数 _开始, 函数 _结束) {

		if (_事件 == null && _开始 == null && _结束 == null) {
			return null;
		}
		return new 流进度(_事件, _开始, _结束);
	}

	public enum 输入 {
		;

		public static InputStream 自身(String _路径) {

			return 输入.class.getClassLoader().getResourceAsStream(_路径);
		}

		public static ByteArrayInputStream 字节(byte[] _字节) {

			断言.不为空(_字节, "字节为空");
			return new ByteArrayInputStream(_字节);
		}

		public static FileInputStream 文件(文件 _文件) {

			断言.不为空(_文件, "文件对象为空");
			断言.为真(_文件.取是文件(), "找不到文件");
			try {
				return new FileInputStream(_文件);
			} catch (FileNotFoundException _错误) {
				throw new RuntimeException(_错误);
			}
		}

	}

	public enum 输出 {
		;

		public static ByteArrayOutputStream 字节() {

			return new ByteArrayOutputStream();
		}

		public static FileOutputStream 文件(String _地址) {

			return 文件(new 文件(_地址));
		}

		public static FileOutputStream 文件(文件 _地址) {

			return 文件(_地址, false);
		}

		public static FileOutputStream 文件(String _地址, boolean _追加) {

			return 文件(new 文件(_地址), _追加);

		}

		public static FileOutputStream 文件(文件 _地址, boolean _追加) {

			_地址.创建文件();
			try {
				return new FileOutputStream(_地址, _追加);
			} catch (FileNotFoundException ignored) {
			}
			return null;
		}

	}

	public static class 流进度 implements StreamProgress {

		private 函数 事件;
		private 函数 开始;
		private 函数 结束;

		public 流进度(函数 _事件, 函数 _开始, 函数 _结束) {

			事件 = _事件;
			开始 = _开始;
			结束 = _结束;
		}

		@Override
		public void start() {

			处理.同步(开始);
		}

		@Override
		public void progress(long _进度) {

			处理.同步(事件, _进度);
		}

		@Override
		public void finish() {

			处理.同步(结束);
		}

	}

}
