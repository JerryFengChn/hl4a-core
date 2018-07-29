package 间.工具;

import 间.收集.哈希集;
import 间.收集.有序列表;
import 间.方法.函数;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collection;

@SuppressWarnings({"NonAsciiCharacters", "unchecked"})
public enum 数组 {
	;

	public static <E, T> E[] 转换(T[] _数组, Class<E> _类, 转换器<E, T> _转换器) {

		E[] _返回 = 创建(_类, _数组.length);

		for (int _键值 = 0; _键值 < _数组.length; _键值++) {

			_返回[_键值] = _转换器.转换(_数组[_键值]);

		}

		return _返回;

	}

	public static void 遍历(Object[] _数组, 函数 _回调) {

		for (Object _单个 : _数组) {
			处理.同步(_回调, _单个);
		}
	}

	public static void 遍历(Collection _数组, 函数 _回调) {

		for (Object _单个 : _数组) {
			处理.同步(_回调, _单个);
		}
	}

	public static <类型> 类型[] 去重(类型[] _数组) {

		哈希集<类型> _集合 = new 哈希集<>();
		for (类型 _单个 : _数组) {
			_集合.添加(_单个);
		}
		return _集合.到数组(取泛型数组类(_数组));
	}

	public static <类型> 类型[] 反转(类型[] _数组) {

		类型[] _返回 = 创建(取泛型数组类(_数组), _数组.length);
		// 这里是反转 就不能用 arraycopy
		//noinspection ManualArrayCopy
		for (int _键值 = _数组.length - 1; _键值 >= 0; _键值--) {
			_返回[_键值] = _数组[_键值];
		}
		return _返回;
	}

	public static <类型> 类型[] 创建(Class<类型> _类, int _长度) {

		return (类型[]) Array.newInstance(_类, _长度);
	}

	public static <类型> 类型[] 删除(类型[] _数组, 类型 _对象) {

		有序列表<类型> _返回 = new 有序列表<>(_数组);
		_返回.remove(_对象);
		return _返回.到数组(取泛型数组类(_数组));
	}

	public static <类型> 类型[] 删除(类型[] _数组, int _键值) {

		if (_数组.length == 0) return _数组;
		else if (_键值 > _数组.length - 1) return _数组;
		类型[] _返回 = 创建(取泛型数组类(_数组), _数组.length - 1);
		if (_键值 > 0) System.arraycopy(_数组, 0, _返回, 0, _键值 - 1);
		if (_数组.length - _键值 - 1 > 0) System.arraycopy(_数组, _键值, _返回, _键值, _数组.length - _键值);
		return _返回;
	}

	public static <类型> 类型[] 截取(类型[] _数组, Integer _开始, Integer _结束) {

		int _开始位置 = _开始 == null ? 0 : _开始;
		int _结束位置 = _结束 == null ? _数组.length : _结束 + 1;
		if (_开始位置 == 0) return _数组;
		int _长度 = _结束位置 - _开始位置;
		类型[] _返回 = 创建(取泛型数组类(_数组), _长度);
		System.arraycopy(_数组, _开始位置, _返回, 0, _结束位置 - _开始位置);
		return _返回;
	}

	public static <类型> 类型[] 转换(Class<类型> _类, Object[] _数组) {

		if (_数组.getClass().getComponentType().equals(_类)) {
			return (类型[]) _数组;
		} else {
			类型[] _返回 = 创建(_类, _数组.length);
			System.arraycopy(_数组, 0, _返回, 0, _数组.length);
			return _返回;
		}
	}

	public static <类型> 类型[] 合并(类型 _前, 类型[] _后) {

		类型[] _返回 = 创建(分析数组类((Class<类型>) _前.getClass(), 取泛型数组类(_后)), _后.length + 1);
		_返回[0] = _前;
		if (_后.length > 0) {
			System.arraycopy(_后, 0, _返回, 1, _后.length);
		}
		return _返回;
	}

	public static <类型> 类型[] 合并(类型[] _前, 类型 _后) {

		类型[] _返回 = 创建(分析数组类(取泛型数组类(_前), (Class<类型>) _后.getClass()), _前.length + 1);
		_返回[_返回.length - 1] = _后;
		if (_前.length > 0) {
			System.arraycopy(_前, 0, _返回, 0, _前.length);
		}
		return _返回;
	}

	public static <类型> 类型[] 合并(类型[] _前, 类型[] _后) {

		类型[] _返回 = 创建(分析数组类(取泛型数组类(_前), 取泛型数组类(_后)), _前.length + _后.length);
		if (_前.length > 0) System.arraycopy(_前, 0, _返回, 0, _前.length);
		if (_后.length > 0) System.arraycopy(_后, 0, _返回, _前.length, _后.length);
		return _返回;
	}

	public static <类型> boolean 比较(类型[] _数组, 类型[] _比较) {

		return Arrays.equals(_数组, _比较);
	}

	public static <类型> boolean 以开始(类型[] _数组, 类型[] _比较) {

		return _比较.length == 0 || 比较(截取(_数组, 0, _比较.length - 1), _比较);
	}

	public static <类型> boolean 以结束(类型[] _数组, 类型[] _比较) {

		return _比较.length == 0 || 比较(截取(_数组, _数组.length - _比较.length, _数组.length - 1), _比较);
	}

	public static <类型> Class<类型> 分析数组类(Class<类型> _1, Class<类型> _2) {

		if (_1.isAssignableFrom(_2)) {
			return _1;
		} else if (_2.isAssignableFrom(_1)) {
			return _2;
		} else {
			return (Class<类型>) Object.class;
		}

	}

	public static <类型> Class<类型> 取泛型数组类(类型[] _数组) {

		return (Class<类型>) _数组.getClass().getComponentType();
	}

	public interface 转换器<E, T> {

		E 转换(T _对象);

	}

}
