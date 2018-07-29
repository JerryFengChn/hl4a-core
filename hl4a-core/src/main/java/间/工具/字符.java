package 间.工具;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.nio.charset.Charset;
import java.util.Objects;

public enum 字符 {
	;

	/**
	 * 字符串转换编码
	 *
	 * @param _字符 要转换编码的字符
	 * @param _编码 要转换到的编码
	 * @return 转换好的字符
	 */
	public static String 转码(String _字符, Charset _编码) {

		断言.不为空(_字符, "_字符为空");
		return new String(_字符.getBytes(_编码));
	}

	/**
	 * 格式化文本 $1 代表模板的第一个参数toString
	 *
	 * @param _对象 要格式化的对象/文本
	 * @param _模板 模板数组
	 * @return 返回的格式化的字符串
	 */

	public static String 格式化(Object _对象, Object... _模板) {

		String _内容 = _对象 == null ? "null" : _对象.toString();
		if (_模板 == null || _模板.length == 0) return _内容;
		for (int _键值 = 0; _键值 < _模板.length; _键值++) {
			Object _单个对象 = _模板[_键值];
			String _单个 = _单个对象 == null ? "null" : _单个对象.toString();
			String _替换 = "_" + (_键值 + 1);
			_内容 = 替换(_内容, _替换, _单个);
		}
		return _内容;
	}

	/**
	 * 序列化对象
	 *
	 * @param _对象 实现了Serializable接口的对象
	 * @return Base64之后的序列化以后的对象
	 */

	public static String 序列化(Serializable _对象) {

		断言.不为空(_对象, "要序列化的对象为空");
		return 编码.Base64.编码(字节.序列化(_对象));
	}

	/**
	 * 反序列化对象
	 *
	 * @param _字符 Base64之后的序列化以后的对象
	 * @return 反序列化的对象
	 */

	public static <类型> 类型 反序列化(String _字符) {

		断言.不为空(_字符, "要反序列化的字符为空");
		return 字节.反序列化(编码.Base64.解码(_字符));
	}

	/**
	 * 比较两个字符串是否相等 用于一个字符串可能为空的情况
	 *
	 * @param _字符 第一个字符
	 * @param _比较 第二个字符
	 * @return 是否相等
	 */

	public static boolean 比较(String _字符, String _比较) {

		return Objects.equals(_字符, _比较);
	}

	/**
	 * 从指定文件读取字符串
	 *
	 * @param _地址 文件地址
	 * @return 读取的字符串
	 */

	public static String 读取(String _地址) {
		return 转换(字节.读取(_地址));
	}

	public static String 读取(文件 _文件) {
		return 转换(字节.读取(_文件));
	}

	/**
	 * 从输入流读取字符串
	 *
	 * @param _流 输入流
	 * @return 读取的字符串
	 */

	public static String 读取(InputStream _流) {

		断言.不为空(_流, "输出流为空");
		return 转换(字节.读取(_流));
	}

	/**
	 * 将字符串保存到指定文件
	 *
	 * @param _内容 要保存到的文件
	 * @param _地址 字符串内容
	 */

	public static void 保存(String _地址, String _内容) {

		断言.不为空(_地址, "要保存到的地址为空");
		字节.保存(_地址, _内容.getBytes());
	}

	/**
	 * 将字符串保存到输出流
	 *
	 * @param _输出 要被保存的输出流
	 * @param _内容 要保存的内容
	 */

	public static void 保存(OutputStream _输出, String _内容) {

		断言.不为空(_输出, "要被保存的输出流为空");
		断言.不为空(_内容, "要保存的内容为空");
		字节.保存(_输出, _内容.getBytes());
	}

	/**
	 * 在指定文件追加文本
	 *
	 * @param _地址 要追加到的地址
	 * @param _内容 要追加的文本
	 */

	public static void 追加(String _地址, String _内容) {

		断言.不为空(_地址, "要追加到的地址为空");
		断言.不为空(_内容, "要追加的文本为空");
		字节.追加(_地址, _内容.getBytes());
	}

	/**
	 * 字符串转换成字符串
	 *
	 * @param _字符 字符
	 * @return 转换好的字符
	 */

	public static String 转换(String _字符) {

		断言.不为空(_字符, "字符为空");
		return _字符;

	}

	/**
	 * 字节转换成字符串
	 *
	 * @param _字节 字节组
	 * @return 转换好的字符
	 */

	public static String 转换(byte[] _字节) {

		断言.不为空(_字节, "字节为空");

		return new String(_字节);

	}

	/**
	 * 字节转换成字符串
	 *
	 * @param _字节 字节组
	 * @param _编码 字节的编码
	 * @return 转换好的字符
	 */

	public static String 转换(byte[] _字节, Charset _编码) {

		断言.不为空(_字节, "字节为空");

		return new String(_字节, _编码);

	}

	/**
	 * 从字符串的指定位置开始向前截取所有文本
	 *
	 * @param _文本 要截取的文本
	 * @param _开始 (从前向后记);
	 * @return String 截取好的文本
	 */

	public static String 取开始前(String _文本, Integer _开始) {

		断言.不为空(_文本, "要截取的文本为空");
		断言.不为空(_开始, "开始向前截取的位置为空");
		return _文本.substring(0, _开始);
	}

	/**
	 * 从字符串的指定位置开始向后截取所有文本
	 *
	 * @param _文本 要截取的文本
	 * @param _开始 开始向后截取的位置 (从前向后记);
	 * @return String 截取好的文本
	 */

	public static String 取开始后(String _文本, Integer _开始) {

		断言.不为空(_文本, "要截取的文本为空");
		断言.不为空(_开始, "开始向后截取的位置为空");
		return _文本.substring(_开始);
	}

	/**
	 * 从字符串的指定位置开始向前截取所有文本
	 *
	 * @param _文本 要截取的文本
	 * @param _结束 开始向前截取的位置 (从后向前记);
	 * @return String 截取好的文本
	 */

	public static String 取结束前(String _文本, Integer _结束) {

		断言.不为空(_文本, "要截取的文本为空");
		断言.不为空(_结束, "开始向前截取的位置为空");
		return _文本.substring(0, _文本.length() - _结束);
	}

	/**
	 * 从字符串的指定位置开始向后截取所有文本
	 *
	 * @param _文本 要截取的文本
	 * @param _结束 开始向后截取的位置 (从后向前记);
	 * @return String 截取好的文本
	 */

	public static String 取结束后(String _文本, Integer _结束) {

		断言.不为空(_文本, "要截取的文本为空");
		断言.不为空(_结束, "开始向后截取的位置为空");
		return _文本.substring(_文本.length() - _结束);
	}

	/**
	 * 取指定开始结束位置之间的字符串
	 *
	 * @param _文本 要截取的文本
	 * @param _开始 开始位置
	 * @param _结束 结束位置
	 * @return String 截取好的文本
	 */

	public static String 取中间(String _文本, Integer _开始, Integer _结束) {

		断言.不为空(_文本, "要截取的文本为空");
		断言.不为空(_开始, "开始位置为空");
		断言.不为空(_结束, "结束位置为空");
		return _文本.substring(_开始, _结束);
	}

	/**
	 * 检查字符串中是否出现指定文本
	 *
	 * @param _文本 要检查的文本
	 * @param _内容 是否出现的内容
	 * @return boolean 是否出现
	 */

	public static Boolean 是否出现(String _文本, String _内容) {

		断言.不为空(_文本, "要检查的文本为空");
		断言.不为空(_内容, "检查是否出现的文本为空");
		return _文本.contains(_内容);

	}

	/**
	 * 检查是否以指定文本开头
	 *
	 * @param _文本 被检查的文本
	 * @param _开始 要检查的内容
	 * @return boolean 结果
	 **/

	public static Boolean 以开始(String _文本, String _开始) {

		断言.不为空(_文本, "被检查的文本为空");
		断言.不为空(_开始, "要检查的内容");
		return _文本.startsWith(_开始);
	}

	/**
	 * 检查是否以指定文本结束
	 *
	 * @param _文本 被检查的文本
	 * @param _结束 要检查的内容
	 * @return boolean 结果
	 **/

	public static Boolean 以结束(String _文本, String _结束) {

		断言.不为空(_文本, "被检查的文本为空");
		断言.不为空(_结束, "要检查的内容");
		return _文本.endsWith(_结束);
	}

	/**
	 * 使用指定关键字分割文本到数组
	 *
	 * @param _文本 要分割的文本
	 * @param _分割 关键字
	 * @return String[] 分割的结果
	 **/

	public static String[] 分割(String _文本, String _分割) {

		断言.不为空(_文本, "要分割的文本为空");
		断言.不为空(_分割, "分割的内容为空");
		return _文本.split(_分割);
	}

	/**
	 * 使用指定关键字分割文本到数组
	 *
	 * @param _文本   要分割的文本
	 * @param _分割   关键字
	 * @param _最大位数 分割的数组最大长度
	 * @return String[] 分割的结果
	 **/

	public static String[] 分割(String _文本, String _分割, int _最大位数) {

		断言.不为空(_文本, "要分割的文本为空");
		断言.不为空(_分割, "分割的内容为空");
		return _文本.split(_分割, _最大位数);
	}

	/**
	 * 分解字符串数组
	 *
	 * @param _数组 要分解的数组
	 * @return String 直接拼接的字符串
	 **/

	public static String 分解(Object[] _数组) {

		断言.不为空(_数组, "要分解的数组为空");
		return 分解(_数组, "");
	}

	/**
	 * 用指定文本分解字符串数组
	 *
	 * @param _数组 要分解的数组
	 * @param _分隔 分隔文本
	 * @return 拼接的字符串
	 **/

	public static String 分解(Object[] _数组, String _分隔) {

		断言.不为空(_数组, "要分解的数组为空");
		断言.不为空(_分隔, "分隔文本为空");

		int _数组长度 = _数组.length;

		if (_数组长度 == 0) {
			return "";
		} else if (_数组长度 == 1) {
			if (_数组[0] != null) {
				return _数组[0].toString();
			} else {
				return "";
			}
		}

		StringBuilder _分解 = new StringBuilder(_数组[0].toString());

		for (int _键值 = 1; _键值 != _数组长度; _键值++) {

			_分解.append(_分隔).append(_数组[_键值]);

		}

		return _分解.toString();

	}

	/**
	 * 替换文本
	 *
	 * @param _文本  要处理的文本
	 * @param _旧文本 要替换的文本
	 * @param _新文本 替换到的文本
	 * @return String 结果
	 **/

	public static String 替换(String _文本, String _旧文本, String _新文本) {

		断言.不为空(_文本, "要处理的文本为空");
		断言.不为空(_旧文本, "要替换的文本为空");
		断言.不为空(_新文本, "替换到的文本为空");
		return _文本.replace(_旧文本, _新文本);
	}

	/**
	 * 批量替换文本
	 *
	 * @param _文本  要处理的字符串数组
	 * @param _旧文本 要替换的文本
	 * @param _新文本 替换到的文本
	 * @return String 结果
	 **/

	public static String[] 替换(String[] _文本, String _旧文本, String _新文本) {

		断言.不为空(_文本, "要处理的字符串数组为空");
		断言.不为空(_旧文本, "要替换的文本为空");
		断言.不为空(_新文本, "替换到的文本为空");
		String[] _结果 = new String[_文本.length];
		for (int _键值 = 0; _键值 < _结果.length; _键值++) {
			if (_文本[_键值] == null) continue;
			_结果[_键值] = 替换(_文本[_键值], _旧文本, _新文本);
		}
		return _结果;
	}

	/**
	 * 用正则表达式替换文本
	 *
	 * @param _文本  要处理的字符串数组
	 * @param _表达式 表达式
	 * @param _新文本 替换到的文本
	 * @return String 结果
	 **/

	public static String 正则替换(String _文本, String _表达式, String _新文本) {

		断言.不为空(_文本, "要处理的字符串数组为空");
		断言.不为空(_表达式, "表达式为空");
		断言.不为空(_新文本, "替换到的文本为空");
		return _文本.replaceAll(_表达式, _新文本);
	}

	/**
	 * 逐行替换文本
	 *
	 * @param _文本  要处理的字符
	 * @param _旧文本 要替换的文本
	 * @param _新文本 替换到的文本
	 **/

	public static String 逐行替换(String _文本, String _旧文本, String _新文本) {

		断言.不为空(_文本, "要处理的字符不能为空 ~");
		断言.不为空(_旧文本, "要替换的文本不能为空 ~");
		断言.不为空(_新文本, "替换到的文本不能为空 ~");
		String[] _所有 = 分割(_文本, "\n");
		for (int _键值 = 0; _键值 != _所有.length; _键值++) {
			_所有[_键值] = 替换(_所有[_键值], _旧文本, _新文本);
		}
		return 分解(_所有, "\n");
	}

	/**
	 * 用正则表达式逐行替换文本
	 *
	 * @param _文本  要处理的字符
	 * @param _表达式 表达式
	 * @param _新文本 替换到的文本
	 **/

	public static String 逐行正则(String _文本, String _表达式, String _新文本) {

		断言.不为空(_文本, "要处理的字符不能为空 ~");
		断言.不为空(_表达式, "表达式不能为空 ~");
		断言.不为空(_新文本, "替换到的文本不能为空 ~");
		String[] _所有 = 分割(_文本, "\n");
		for (int _键值 = 0; _键值 != _所有.length; _键值 += 1)
			_所有[_键值] = 正则替换(_所有[_键值], _表达式, _新文本);
		return 分解(_所有, "\n");
	}

	public static String 截取开始(String _文本, String _开始, String _结束) {

		Integer start = null;
		Integer end = null;

		if (_开始 == null && _结束 == null) return _文本;

		if (_开始 == null) start = 0;
		else start = _文本.indexOf(_开始) + _开始.length();

		if (start == -1) return null;

		if (_结束 == null) return _文本.substring(start);
		else {
			_文本 = _文本.substring(start);
			end = _文本.indexOf(_结束);
		}
		if (end == -1) return null;

		return _文本.substring(0, end);

	}

	public static String 截取中间(String _文本, String _开始, String _结束) {

		Integer start = null;
		Integer end = null;

		if (_开始 == null && _结束 == null) return _文本;

		if (_开始 == null) start = 0;
		else start = _文本.indexOf(_开始) + _开始.length();

		if (start == -1) return null;

		if (_结束 == null) return _文本.substring(start);
		else {
			_文本 = _文本.substring(start);
			end = _文本.lastIndexOf(_结束);
		}

		if (end == -1) return null;

		return _文本.substring(0, end);

	}

	public static String 截取结束(String _文本, String _开始, String _结束) {

		Integer start = null;
		Integer end = null;

		if (_开始 == null && _结束 == null) return _文本;

		if (_开始 == null) start = 0;
		else start = _文本.lastIndexOf(_开始) + _开始.length();

		if (start == -1) return null;

		if (_结束 == null) return _文本.substring(start);
		else {
			_文本 = _文本.substring(start);
			end = _文本.lastIndexOf(_结束);
		}
		if (end == -1) return null;

		return _文本.substring(0, end);
	}

	public static String 截取(String _文本, String _开始, String _结束, Integer _开始数量, Integer _结束数量) {

		if (_开始数量 == null) _开始数量 = 1;
		if (_结束数量 == null) _结束数量 = 1;
		Integer _出现数量 = 取出现次数(_文本, _开始);
		if (_开始数量 < 0) {
			_开始数量 = _出现数量 + 1 + _开始数量;
			if (_开始数量 < 0) {
				_开始数量 = _出现数量;
			}
		}
		if (_开始数量 > _出现数量) {
			_开始数量 = _出现数量;
		}
		int _当前位置 = 0;
		if (_开始 != null && _开始.length() > 0 && _出现数量 != 0) {
			while (_开始数量 > 0) {
				_当前位置 = 取出现位置前下标(_文本, _开始, _当前位置);
				_开始数量--;
			}
			_文本 = 取开始后(_文本, _当前位置);
		}
		if (_结束 != null && _结束.length() > 0) {
			_出现数量 = 取出现次数(_文本, _结束);
			if (_结束数量 < 0) {
				_结束数量 = _出现数量 + 1 + _结束数量;
				if (_结束数量 < 0) {
					_结束数量 = _出现数量;
				}
			}
			if (_结束数量 > _出现数量) {
				_结束数量 = _出现数量;
			}
			_当前位置 = 取出现位置后上标(_文本, _结束);
			while (_结束数量 > 0) {
				_当前位置 = 取出现位置前上标(_文本, _结束, _当前位置);
				_结束数量--;
			}
			return 取开始前(_文本, _当前位置);
		} else {
			return _文本;
		}
	}

	public static Integer 取出现位置(String _文本, String _内容) {

		if (_文本 == null) return null;
		if (_内容 == null) return null;
		int _位置 = _文本.indexOf(_内容);
		if (_位置 == -1) return null;
		return _位置;
	}

	public static Integer 取出现位置(String _文本, String _内容, Integer _开始位置) {

		if (_文本 == null) return null;
		if (_内容 == null) return null;
		if (_开始位置 != null) {
			int _位置 = _文本.indexOf(_内容, _开始位置);
			if (_位置 == -1) return null;
			return _位置;
		} else return 取出现位置(_文本, _内容);
	}

	public static Integer 取出现位置前下标(String _文本, String _内容) {

		return 取出现位置后上标(_文本, _内容) + _内容.length();
	}

	public static Integer 取出现位置后下标(String _文本, String _内容) {

		return 取出现位置前上标(_文本, _内容) + _文本.length();
	}

	public static Integer 取出现位置前下标(String _文本, String _内容, Integer _开始位置) {

		return 取出现位置前上标(_文本, _内容) + _内容.length();
	}

	public static Integer 取出现位置后下标(String _文本, String _内容, Integer _开始位置) {

		return 取出现位置前上标(_文本, _内容, _开始位置) + _内容.length();
	}

	public static Integer 取出现位置前上标(String _文本, String _内容) {

		int _位置 = _文本.indexOf(_内容);
		if (_位置 == -1) return -1;
		return _位置;
	}

	public static Integer 取出现位置后上标(String _文本, String _内容) {

		int _位置 = _文本.lastIndexOf(_内容);
		if (_位置 == -1) return -1;
		return _位置;
	}

	public static Integer 取出现位置前上标(String _文本, String _内容, Integer _开始位置) {

		int _位置 = _文本.indexOf(_内容, _开始位置);
		if (_位置 == -1) return -1;
		return _位置;
	}

	public static Integer 取出现位置后上标(String _文本, String _内容, Integer _开始位置) {

		int _位置 = _文本.lastIndexOf(_内容, _开始位置);
		if (_位置 == -1) return -1;
		return _位置;
	}

	public static boolean 是空白(Object... _内容) {

		if (_内容 == null) return true;
		for (Object _单个 : _内容) {
			if (_单个 == null || "".equals(_单个.toString().trim())) return true;
		}
		return false;
	}

	public static Integer 取出现次数(String _文本, String _内容) {

		Integer _次数 = 0;
		Integer _位置 = -1;

		while ((_位置 = _文本.indexOf(_内容)) != -1) {
			_文本 = _文本.substring(_位置 + _内容.length());
			_次数++;
		}
		return _次数;
	}

	public static String 大写(String _文本) {

		return _文本.toUpperCase();
	}

	public static String 小写(String _文本) {

		return _文本.toLowerCase();
	}

}

