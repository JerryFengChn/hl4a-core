package 间.工具;

import 间.收集.列表;
import 间.收集.有序列表;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

@SuppressWarnings({"NonAsciiCharacters", "unchecked"})
public enum 注解 {
	;

	public static <注解 extends Annotation> 注解 取类注解(Class<?> _类, Class<注解> _注解类, boolean _循环) {

		注解 _注解 = null;
		Annotation[] _所有注解 = _类.getDeclaredAnnotations();
		for (Annotation _单个 : _所有注解) {
			if (_注解类.isInstance(_单个)) {
				return (注解) _单个;
			}
		}
		循环:
		while (_循环 && _注解 == null && (_类 = _类.getSuperclass()) != null) {
			Annotation[] _所有 = _类.getDeclaredAnnotations();
			for (Annotation _单个 : _所有) {
				if (_注解类.isInstance(_单个)) {
					_注解 = (注解) _单个;
					break 循环;
				}
			}
		}
		return _注解;

	}

	public static <注解 extends Annotation> 变量注解<注解>[] 取变量注解(Class<?> _类, Class<?> _注解类, boolean _循环) {

		Field[] _所有 = _类.getDeclaredFields();
		有序列表<变量注解> _方法 = new 有序列表<>();
		for (Field _单个 : _所有) {
			for (Annotation _注解 : _单个.getDeclaredAnnotations()) {
				if (_注解类.isInstance(_注解)) {
					_单个.setAccessible(true);
					_方法.添加(new 变量注解<>((注解) _注解, _单个));
				}
			}
		}
		if (_循环) {
			_方法.添加所有(取变量注解(_类.getSuperclass(), _注解类, _循环));
		}
		return _方法.到数组(变量注解.class);

	}

	public static <注解 extends Annotation> 方法注解<注解>[] 取方法注解(Class<?> _类, Class<注解> _注解类, boolean _循环) {

		Method[] _所有 = _类.getDeclaredMethods();
		列表<方法注解> _方法 = new 列表<>();
		for (Method _单个 : _所有) {
			for (Annotation _注解 : _单个.getDeclaredAnnotations()) {
				if (_注解类.isInstance(_注解)) {
					_单个.setAccessible(true);
					_方法.添加(new 方法注解<>((注解) _注解, _单个));
				}
			}
		}
		if (_循环) {
			_方法.添加所有(取方法注解(_类.getSuperclass(), _注解类, _循环));
		}
		return _方法.到数组(方法注解.class);
	}

	public static class 变量注解<类型 extends Annotation> {

		public final 类型 注解;
		public final Field 变量;

		public 变量注解(类型 _注解, Field _变量) {

			注解 = _注解;
			变量 = _变量;
		}

	}

	public static class 方法注解<类型 extends Annotation> {

		public final 类型 注解;
		public final Method 方法;

		public 方法注解(类型 _注解, Method _方法) {

			注解 = _注解;
			方法 = _方法;
		}

	}

}
