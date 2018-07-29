package 间.工具;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.nio.charset.Charset;
import java.util.Enumeration;
import java.util.zip.*;

/**
 * 用来操作ZIP文件的工具类
 */

public class ZIP implements AutoCloseable {

	/* redefine those constant here because of bug 13721174 preventing to compile using the
	 * constants defined in ZipFile */
	private static final int ENDHDR = 22;
	private static final int ENDSIG = 0x6054b50;
	/**
	 * Size of reading buffers.
	 */
	private static final int BUFFER_SIZE = 0x4000;
	private 文件 当前文件;
	private ZipFile 压缩文件;


	// -----------------------------------------------

	/**
	 * 创建一个ZIP文件对象 使用UTF8编码
	 *
	 * @param _文件 文件对象
	 * @throws IOException 读取错误
	 */
	public ZIP(文件 _文件) throws IOException {

		this(_文件, 编码.UTF8);
	}

	/**
	 * 创建一个ZIP文件对象 使用UTF8编码
	 *
	 * @param _文件 文件对象
	 * @param _编码 使用的编码
	 * @throws IOException 读取错误
	 */
	public ZIP(文件 _文件, Charset _编码) throws IOException {

		断言.不为空(_文件, "文件对象为空");
		断言.为真(_文件.取是文件(), "找不到文件");
		断言.不为空(_编码, "编码对象为空");
		当前文件 = _文件;
		压缩文件 = new ZipFile(_文件);
	}

	private static CentralDirectory findCentralDirectory(RandomAccessFile raf) throws IOException {

		long scanOffset = raf.length() - ENDHDR;
		if (scanOffset < 0) {
			throw new ZipException("File too short to be a zip file: " + raf.length());
		}

		long stopOffset = scanOffset - 0x10000 /* ".ZIP file comment"'s max length */;
		if (stopOffset < 0) {
			stopOffset = 0;
		}

		int endSig = Integer.reverseBytes(ENDSIG);
		while (true) {
			raf.seek(scanOffset);
			if (raf.readInt() == endSig) {
				break;
			}

			scanOffset--;
			if (scanOffset < stopOffset) {
				throw new ZipException("End Of Central Directory signature not found");
			}
		}
		// Read the End Of Central Directory. ENDHDR includes the signature
		// bytes,
		// which we've already read.

		// Pull out the information we need.
		raf.skipBytes(2); // diskNumber
		raf.skipBytes(2); // diskWithCentralDir
		raf.skipBytes(2); // numEntries
		raf.skipBytes(2); // totalNumEntries
		CentralDirectory dir = new CentralDirectory();
		dir.size = Integer.reverseBytes(raf.readInt()) & 0xFFFFFFFFL;
		dir.offset = Integer.reverseBytes(raf.readInt()) & 0xFFFFFFFFL;
		return dir;
	}

	/* Package visible for testing */
	private static long computeCrcOfCentralDir(RandomAccessFile raf, CentralDirectory dir) throws IOException {

		CRC32 crc = new CRC32();
		long stillToRead = dir.size;
		raf.seek(dir.offset);
		int length = (int) Math.min(BUFFER_SIZE, stillToRead);
		byte[] buffer = new byte[BUFFER_SIZE];
		length = raf.read(buffer, 0, length);
		while (length != -1) {
			crc.update(buffer, 0, length);
			stillToRead -= length;
			if (stillToRead == 0) {
				break;
			}
			length = (int) Math.min(BUFFER_SIZE, stillToRead);
			length = raf.read(buffer, 0, length);
		}
		return crc.getValue();
	}

	private static void 遍历压缩(ZipOutputStream _输出流, String _地址, String _附加) throws IOException {

		文件[] _所有 = new 文件(_地址).取文件列表();
		for (文件 _单个 : _所有) {
			if (_单个.取是目录()) {
				String _目录 = _附加 + _单个.getName() + "/";
				_输出流.putNextEntry(new ZipEntry(_目录));
				遍历压缩(_输出流, _单个.getPath(), _目录);
			} else {
				InputStream _输入 = 流.输入.文件(_单个);
				_输出流.putNextEntry(new ZipEntry(_附加 + _单个.getName()));
				int _长度 = 0;
				byte[] _缓冲 = new byte[流.中];
				while ((_长度 = _输入.read(_缓冲)) > 0) {
					_输出流.write(_缓冲, 0, _长度);
				}
				流.关闭(_输入);
			}
		}
	}

	@Override
	public void close() throws Exception {

		if (压缩文件 != null) {
			压缩文件.close();
		}
	}

	public void 关闭() {

		try {
			close();
		} catch (Exception ignored) {
		}
	}

	/**
	 * 来自 MulitDex 的识别ZipCrc代码
	 *
	 * @return Zip的Crc哈希值
	 */

	public long 取CRC() {

		try {
			RandomAccessFile raf = new RandomAccessFile(当前文件, "r");
			CentralDirectory dir = findCentralDirectory(raf);
			return computeCrcOfCentralDir(raf, dir);
		} catch (FileNotFoundException ignored) {
		} catch (IOException _错误) {
			错误.抛出(_错误);
		}
		return 0;
	}

	// -----------------------------------------------

	/**
	 * 从Zip文件中读取 指定的文件 的字节
	 *
	 * @param _路径 要读取的文件的相对路径
	 * @return 指定文件的byte[] 字节组
	 */

	public byte[] 读取(String _路径) throws IOException {

		断言.不为空(_路径, "路径为空");
		ZipEntry _进入 = 压缩文件.getEntry(_路径);
		if (_进入 == null) throw new IOException("Zip内找不到 : " + _路径);
		if (_进入.isDirectory()) {
			return null;
		} else {
			return 字节.读取(压缩文件.getInputStream(_进入));
		}
	}

	/**
	 * 从Zip中解压 指定文件/目录 到指定位置
	 *
	 * @param _地址 要解压的相对目录
	 * @param _输出 解压到的地址
	 */

	public ZIP 解压(String _地址, 文件 _输出) throws IOException {

		断言.不为空(_地址, "要解压的相对地址为空");
		断言.不为空(_输出, "要输出的文件为空");
		ZipEntry _进入 = 压缩文件.getEntry(_地址);
		if (_进入 == null) throw new IOException("Zip内没有这个文件 : " + _地址);
		if (_进入.isDirectory()) {
			Enumeration<? extends ZipEntry> _所有 = 压缩文件.entries();
			while (_所有.hasMoreElements()) {
				ZipEntry _单个 = _所有.nextElement();
				if (!_单个.getName().startsWith(_输出 + "/")) continue;
				if (_单个.isDirectory()) {
					new 文件(_输出, _单个.getName()).创建文件();
				} else {
					流.保存(_输出.取文件(_单个.getName()), 压缩文件.getInputStream(_单个));
				}
			}
		} else {
			流.保存(_输出, 压缩文件.getInputStream(_进入));
		}
		return this;
	}

	/**
	 * 解压所有文件到指定目录
	 *
	 * @param _输出 要解压到的地址
	 **/

	public ZIP 解压(文件 _输出) throws IOException {

		断言.不为空(_输出, "要输出的地址不能为空 ~");
		if (!_输出.取是目录()) {
			_输出.创建目录();
		}
		Enumeration<? extends ZipEntry> _所有 = 压缩文件.entries();
		while (_所有.hasMoreElements()) {
			ZipEntry _单个 = _所有.nextElement();
			if (_单个.isDirectory()) {
				_输出.取文件(_单个.getName());
			} else {
				流.保存(_输出.取文件(_单个.getName()), 压缩文件.getInputStream(_单个));
			}
		}
		return this;
	}

	/**
	 * 压缩文件/目录覆盖压缩包
	 *
	 * @param _地址 要压缩的文件/目录
	 */

	public ZIP 压缩(文件 _地址) throws IOException {

		断言.不为空(_地址, "要压缩的文件/目录为空");
		断言.为真(_地址.取存在(), "找不到文件/目录 _1", _地址);
		当前文件.删除();
		ZipOutputStream _输出流 = new ZipOutputStream(流.输出.文件(当前文件));
		if (_地址.取是文件()) {
			InputStream _输入 = 流.输入.文件(_地址);
			_输出流.putNextEntry(new ZipEntry(_地址.取名称()));
			int _长度 = 0;
			byte[] _缓冲 = new byte[流.中];
			while ((_长度 = _输入.read(_缓冲)) > 0) {
				_输出流.write(_缓冲, 0, _长度);
			}
			流.关闭(_输入);
			流.关闭(_输出流);
		}
		文件[] _所有 = _地址.取文件列表();
		for (文件 _单个 : _所有) {
			if (_单个.isDirectory()) {
				String _目录 = _单个.getName() + "/";
				_输出流.putNextEntry(new ZipEntry(_目录));
				遍历压缩(_输出流, _单个.getPath(), _目录);
			} else {
				InputStream _输入 = 流.输入.文件(_单个);
				_输出流.putNextEntry(new ZipEntry(_单个.getName()));
				int _长度 = 0;
				byte[] _缓冲 = new byte[流.中];
				while ((_长度 = _输入.read(_缓冲)) > 0) {
					_输出流.write(_缓冲, 0, _长度);
				}
				流.关闭(_输入);
			}
		}
		流.关闭(_输出流);
		压缩文件 = new ZipFile(当前文件);
		return this;
	}

	static class CentralDirectory {

		long offset;
		long size;
	}

}
