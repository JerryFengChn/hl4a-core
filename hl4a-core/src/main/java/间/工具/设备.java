package 间.工具;

import 间.兼容.系统;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Properties;

public class 设备 {

	private static final boolean AIX = 匹配系统名("AIX");
	private static final boolean HP_UX = 匹配系统名("HP-UX");
	private static final boolean IRIX = 匹配系统名("Irix");
	private static final boolean LINUX = 匹配系统名("Linux") || 匹配系统名("LINUX");
	private static final boolean MAC = 匹配系统名("Mac");
	private static final boolean MAC_OSX = 匹配系统名("Mac OS X");
	private static final boolean OS2 = 匹配系统名("OS/2");
	private static final boolean SOLARIS = 匹配系统名("Solaris");
	private static final boolean SUN_OS = 匹配系统名("SunOS");
	private static final boolean WINDOWS = 匹配系统名("Windows");
	private static final boolean WINDOWS_NT = 匹配系统名("Windows NT");
	private static final boolean WINDOWS_2000 = 匹配系统名("Windows", "5.0");
	private static final boolean WINDOWS_95 = 匹配系统名("Windows 9", "4.0");
	private static final boolean WINDOWS_98 = 匹配系统名("Windows 9", "4.1");
	private static final boolean WINDOWS_ME = 匹配系统名("Windows", "4.9");
	private static final boolean WINDOWS_XP = 匹配系统名("Windows", "5.1");
	private static Properties 环境;
	private static final String 系统版本 = 取环境变量("os.version");
	private static final String 系统架构 = 取环境变量("os.arch");
	private static final String 系统名称 = 取环境变量("os.name");
	private static final String 地址分隔符 = 取环境变量("file.separator");
	private static final String 行分隔符 = 取环境变量("line.separator");
	private static final String 路径分隔符 = 取环境变量("path.separator");
	private static 系统 当前系统;

	static {
		try {
			环境 = System.getProperties();
		} catch (SecurityException ignored) {
		}
		if (环境 == null) {
			环境 = 反射.取变量(System.class, "props");
		}
	}

	public static void 置当前系统(系统 _系统) {
		当前系统 = _系统;
	}

	public static 系统 取当前系统() {

		if (当前系统 == null) throw new IllegalStateException("还没有初始化平台");

		return 当前系统;

	}

	public static String 取主机名() {

		try {
			return InetAddress.getLocalHost().getHostName();
		} catch (UnknownHostException e) {
			return "unknown";
		}

	}

	public static String 取环境变量(String _名称) {

		if (环境 != null) {
			return 环境.getProperty(_名称);
		} else {
			try {
				return System.getProperty(_名称);
			} catch (SecurityException ignored) {
			}
			return "";
		}
	}

	public static void 置环境变量(String _键值, String _内容) {

		if (环境 != null) {
			环境.setProperty(_键值, _内容);
		} else {
			System.setProperty(_键值, _内容);
		}
	}

	public static boolean 匹配系统名(String... _名称) {
		for (String _单个 : _名称) if (!取系统名称().matches(_单个)) return false;
		return true;
	}

	public static String 取剪切板() {

		return 取当前系统().取剪切板();

	}

	public static void 置剪切板(String _内容) {

		取当前系统().置剪切板(_内容);

	}

	public static String 取系统版本() {
		return 系统版本;
	}

	public static String 取系统架构() {
		return 系统架构;
	}

	public static String 取系统名称() {
		return 系统名称;
	}

	public static boolean 是AIX() {
		return AIX;
	}

	public static boolean 是HpUx() {
		return HP_UX;
	}

	public static boolean 是Irix() {
		return IRIX;
	}

	public static boolean 是Linux() {
		return LINUX;
	}

	public static boolean 是Mac() {
		return MAC;
	}

	public static boolean 是MacOsx() {
		return MAC_OSX;
	}

	public static boolean 是OS2() {
		return OS2;
	}

	public static boolean Solaris() {
		return SOLARIS;
	}

	public static boolean 是SunOs() {
		return SUN_OS;
	}

	public static boolean 是Windows() {
		return WINDOWS;
	}

	public static boolean 是Windows2000() {
		return WINDOWS_2000;
	}

	public static boolean 是Windows95() {
		return WINDOWS_95;
	}

	public static boolean 是Windows98() {
		return WINDOWS_98;
	}

	public static boolean 是WindowsMe() {
		return WINDOWS_ME;
	}

	public static boolean 是WindowsNt() {
		return WINDOWS_NT;
	}

	public static boolean 是WindowsXp() {
		return WINDOWS_XP;
	}

	public static String 取地址分隔符() {
		return 地址分隔符;
	}

	public static String 取行分隔符() {
		return 行分隔符;
	}

	public static String 取路径分隔符() {
		return 路径分隔符;
	}

}