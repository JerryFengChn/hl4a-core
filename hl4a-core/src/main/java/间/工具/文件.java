package 间.工具;

import 间.收集.哈希表;
import 间.收集.有序列表;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.Map;

public class 文件 extends File {

	private static 哈希表<String, String> 前缀替换 = new 哈希表<>();

	public 文件(String _地址) {

		super(检查地址(_地址));
	}

	public 文件(URI _地址) {

		super(_地址);
	}

	public 文件(File _文件, String _名称) {

		super(_文件, _名称);
	}

	public static String[] 检查地址(String[] _目录) {

		for (int _键值 = 0; _键值 != _目录.length; _键值 += 1)
			_目录[_键值] = 检查地址(_目录[_键值]);
		return _目录;
	}

	public static void 替换地址(String _前缀, String _目标) {

		前缀替换.设置(_前缀, _目标);
	}

	public static String 检查地址(String _目录) {

		if (_目录 == null) return null;

		for (Map.Entry<String, String> _替换 : 前缀替换.entrySet()) {
			String _前缀 = _替换.getKey();
			if (字符.以开始(_目录, _前缀)) {
				String _结果 = _替换.getValue();
				_目录 = _结果 + _目录.substring(_前缀.length());
			}
		}

		return new File(_目录).getPath();

	}

	public static String 格式大小(long _大小) {

		if (_大小 == -1) {
			return "文件夹";
		} else if (_大小 < 1024) {
			return _大小 + "B";
		} else if (_大小 < 1048576) {
			return _大小 / 1024 + "KB";
		} else if (_大小 < 1073741824) {
			return _大小 / 1048576 + "MB";
		} else {
			return _大小 / 1073741824 + "GB";
		}
	}

	public 文件 创建目录() {

		mkdirs();
		return this;
	}

	public 文件 创建文件() {

		try {
			取上级().创建目录();
			if (!取是文件()) {
				createNewFile();
			}
		} catch (IOException ignored) {
		}
		return this;
	}

	public boolean 取是文件() {

		return isFile();
	}

	public boolean 取是目录() {

		return isDirectory();
	}

	public boolean 取存在() {

		return exists();
	}

	public 文件 取文件(String _名称) {

		return new 文件(this, _名称);
	}

	public 文件[] 取文件列表() {

		if (取是目录()) {
			return 转换文件(listFiles());
		}
		return new 文件[0];
	}

	private 文件[] 转换文件(File[] _文件) {

		Arrays.sort(_文件, (f1, f2) -> {
			if (f1.isFile() && f2.isFile() || f1.isDirectory() && f2.isDirectory()) {
				return f1.getName().toLowerCase().compareTo(f2.getName().toLowerCase());
			} else {
				return f1.isFile() ? 1 : -1;
			}
		});
		Arrays.sort(_文件, (f1, f2) -> {
			long diff = f1.lastModified() - f2.lastModified();
			if (diff > 0) return 1;
			else if (diff == 0) return 0;
			else return -1;
		});
		文件[] _返回 = new 文件[_文件.length];
		for (int _键值 = 0; _键值 < _文件.length; _键值++) {
			_返回[_键值] = _文件[_键值] == null ? null : new 文件(_文件[_键值].getPath());
		}
		return _返回;
	}

	public 文件[] 找关键字(String _关键字) {

		有序列表<文件> _返回 = new 有序列表<>();

		文件[] _列表 = 取文件列表();

		for (文件 _单个 : _列表) {
			if (_单个.取是文件() && 字符.是否出现(_单个.getName(), _关键字)) {
				_返回.添加(_单个);
			} else {
				_返回.添加所有(_单个.找关键字(_关键字));
			}

		}

		return _返回.到数组(文件.class);

	}

	public 文件[] 找关键字(String _前缀, String _后缀) {

		return 找关键字(_前缀, _后缀, false);
	}

	public 文件[] 找关键字(String _前缀, String _后缀, boolean _不包含) {

		有序列表<文件> _返回 = new 有序列表<>();

		文件[] _列表 = 取文件列表();

		for (文件 _单个 : _列表) {
			if (_单个.取是文件() && _单个.getName().startsWith(_前缀) && _单个.getName().endsWith(_后缀)) {
				if (_不包含 && _单个.getName().equals(_前缀 + _后缀)) continue;
				_返回.添加(_单个);
			} else if (_单个.isDirectory()) {
				_返回.添加所有(_单个.找关键字(_前缀, _后缀, _不包含));
			}

		}

		return _返回.到数组(文件.class);

	}

	public 文件[] 遍历文件() {

		有序列表<文件> _返回 = new 有序列表<>();

		文件[] _列表 = 取文件列表();

		for (文件 _单个 : _列表) {

			if (_单个.isFile()) {
				_返回.添加(_单个);
			} else if (_单个.isDirectory()) {
				_返回.添加所有(_单个.遍历文件());
			}

		}

		return _返回.到数组(文件.class);

	}


    /*

     public static boolean 以开始(String _地址, String _路径) {
     return 取地址对象(_地址).startsWith(取地址对象(_路径));
     }

     public static boolean 以结束(String _地址, String _路径) {
     return 取地址对象(_地址).endsWith(取地址对象(_路径));
     }

     */

	public URI 取链接() {

		return toURI();
	}

	public String 取地址() {

		return getPath();
	}

	public String 取后缀() {

		if (!字符.是否出现(取名称(), ".")) {
			return "";
		}
		return 字符.截取结束(取名称(), ".", null);
	}

	public String 取前缀() {

		if (!字符.是否出现(取名称(), ".")) return 取名称();
		return 字符.小写(字符.截取结束(取名称(), null, "."));
	}

	public String 取名称() {

		return getName();
	}

	public 文件 取上级() {

		return getParent() == null ? null : new 文件(getParent());
	}

	public String 取格式大小() {

		return 格式大小(取大小());
	}

	public long 取大小() {

		if (取是文件()) {
			return length();
		}
		return -1;
	}

	public boolean 取可读() {

		return canRead();
	}

	public boolean 取可写() {

		return canWrite();
	}

	public boolean 置可读(boolean _可读) {

		return setReadable(_可读);
	}

	public boolean 置可写(boolean _可写) {

		return setWritable(_可写);
	}

	public boolean 取可执行() {

		return canExecute();
	}

	public boolean 置可执行(boolean _可执行) {

		return setExecutable(_可执行);
	}

	public boolean 取是空文件() {

		return 取大小() == 0;
	}

	public void 复制(文件 _地址) {

		if (!兼容.取是安卓()) {
			try {
				Files.copy(toPath(), _地址.toPath());
			} catch (IOException ignored) {
			}
		} else {
			if (取是文件()) {
				if (取是空文件()) {
					_地址.创建文件();
				} else {
					字节.保存(_地址, 字节.读取(this));
				}
			} else if (取是目录()) {
				文件[] _所有 = 取文件列表();
				for (文件 _单个 : _所有) {
					_单个.复制(_地址.取文件(_单个.getName()));
				}
			}
		}
	}

	public String 取上级地址() {

		return getParent();
	}

	public long 取修改日期() {

		return lastModified();
	}

	public boolean 比较(String _地址) {

		return equals(new 文件(_地址));
	}

	public void 剪切(文件 _地址) {

		if (!兼容.取是安卓()) {
			try {
				Files.move(toPath(), _地址.toPath());
			} catch (IOException ignored) {
			}
		} else {
			if (!renameTo(_地址)) {
				复制(_地址);
				删除();
			}
		}
	}

	public void 删除() {

		if (!兼容.取是安卓()) {
			try {
				Files.delete(toPath());
			} catch (IOException ignored) {
			}
		} else {
			if (取是目录()) {
				文件[] _列表 = 取文件列表();
				for (文件 _单个 : _列表) {
					_单个.删除();
				}
			}
			delete();
		}
	}

	@Override
	public String toString() {

		return 取地址();
	}

}
