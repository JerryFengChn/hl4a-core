package 间.工具;

import 间.收集.列表;
import 间.收集.哈希表;
import 间.收集.有序列表;
import 间.方法.函数;

import java.lang.reflect.*;

public class 反射 {

	private static 哈希表<Class, 列表<String>> 转换缓存 = new 哈希表<>();
	private static ClassLoader 当前加载器 = 反射.class.getClassLoader();
	private static 哈希表<Class, Class> 对应表 = new 哈希表<>();

	static {
		对应表.设置(int.class, Integer.class);
		对应表.设置(long.class, Long.class);
		对应表.设置(boolean.class, Boolean.class);
		对应表.设置(float.class, Float.class);
		对应表.设置(char.class, Character.class);
		对应表.设置(double.class, Double.class);
		对应表.设置(short.class, Short.class);
		对应表.设置(byte.class, Byte.class);
	}

	public static <类型> 列表<String> 取可缓存参数(Class $类) {
		if ($类 == null) return null;
		if (!转换缓存.检查($类)) {
			列表<String> $返回 = new 列表<>();
			Field[] $变量 = $类.getDeclaredFields();
			for (Field $单个 : $变量) {
				if (是公开($单个) && !是静态($单个) && !是不建议缓存($单个)) {
					$返回.添加($单个.getName());
				}
			}
			转换缓存.设置($类, $返回);
			return $返回;
		}
		return 转换缓存.读取($类);
	}

	public static boolean 是公开(Member $对象) {
		return Modifier.isPublic($对象.getModifiers());
	}

	public static boolean 是静态(Member $对象) {
		return Modifier.isStatic($对象.getModifiers());
	}

	public static boolean 是最终(Member $对象) {
		return Modifier.isFinal($对象.getModifiers());
	}

	public static boolean 是抽象(Member $对象) {
		return Modifier.isAbstract($对象.getModifiers());
	}

	public static boolean 是接口(Member $对象) {
		return Modifier.isInterface($对象.getModifiers());
	}

	public static boolean 是原生(Member $对象) {
		return Modifier.isNative($对象.getModifiers());
	}

	public static boolean 是私有(Member $对象) {
		return Modifier.isPrivate($对象.getModifiers());
	}

	public static boolean 是保护(Member $对象) {
		return Modifier.isProtected($对象.getModifiers());
	}

	public static boolean 是同步(Member $对象) {
		return Modifier.isSynchronized($对象.getModifiers());
	}

	public static boolean 是不建议缓存(Member $对象) {
		return Modifier.isTransient($对象.getModifiers());
	}

	public static boolean 是线程安全更新(Member $对象) {
		return Modifier.isVolatile($对象.getModifiers());
	}

	public static <类型 extends Enum> 类型 取枚举(Class<类型> $类, String $名称) {
		return (类型) Enum.valueOf($类, $名称);
	}

	public static <类型 extends Enum> 类型[] 取所有枚举(Class<类型> $类) {
		return $类.getEnumConstants();
	}

	public static void 置加载器(ClassLoader $加载器) {
		当前加载器 = $加载器;
	}

	public static <类型> Class<类型> 取类(String $类名) {
		return 取类($类名, 当前加载器);
	}

	public static <类型> Class<类型> 取系统类(String $类名) {
		return 取类($类名, ClassLoader.getSystemClassLoader());
	}

	public static <类型> Class<类型> 取类(String $类名, ClassLoader $类加载器) {
		if ($类名 == null) return null;
		try {
			Class<类型> $类 = (Class<类型>) Class.forName($类名, false, $类加载器);
			return $类;
		} catch (Exception $错误) {
		}
		return null;
	}

	public static Class<?> 取子类(Class<?> $类, String $类名) {
		Class<?>[] $所有 = null;
		while ($类 != null) {
			$所有 = $类.getDeclaredClasses();
			for (Class<?> $单个 : $所有) {
				if ($单个.getName().equals($类.getName() + "$" + $类名)) {
					return $单个;
				}
			}
			$类 = $类.getSuperclass();
		}
		return null;
	}

	public static Field 取变量对象(Class<?> $类, String $变量名) {
		while ($类 != null) {
			try {
				Field $变量 = $类.getDeclaredField($变量名);
				$变量.setAccessible(true);
				return $变量;
			} catch (NoSuchFieldException $错误) {
			}
			$类 = $类.getSuperclass();
		}
		return null;
	}

	public static boolean 有变量(Class<?> $类, String $变量名) {
		while ($类 != null) {
			try {
				$类.getDeclaredField($变量名);
				return true;
			} catch (NoSuchFieldException $错误) {
			}
			$类 = $类.getSuperclass();
		}
		return false;
	}

	public static Field[] 取所有变量(Class<?> $类) {

		return $类.getDeclaredFields();

	}

	public static <类型> 类型 取变量(Object $对象, String $名称) {
		Class<?> $作用类;
		if ($对象 instanceof Class) {
			$作用类 = (Class<?>) $对象;
		} else if ($对象 instanceof String) {
			$作用类 = 取类((String) $对象);
		} else {
			$作用类 = $对象.getClass();
		}
		return 取变量($对象, $作用类, $名称);
	}

	public static void 置变量(Object $对象, String $名称, Object $内容) {
		Class<?> $作用类;
		if ($对象 instanceof Class) {
			$作用类 = (Class<?>) $对象;
		} else if ($对象 instanceof String) {
			$作用类 = 取类((String) $对象);
		} else {
			$作用类 = $对象.getClass();
		}
		置变量($对象, $作用类, $名称, $内容);
	}

	public static <类型> 类型 取变量(Object $对象, Class<?> $作用类, String $名称) {
		Field $变量 = 取变量对象($作用类, $名称);
		try {
			if ($变量 != null) return (类型) $变量.get($对象);
		} catch (Exception $错误) {
			错误.抛出($错误);
		}
		return null;
	}

	public static void 置变量(Object $对象, Class<?> $作用类, String $名称, Object $内容) {
		Field $变量 = 取变量对象($作用类, $名称);
		try {
			if ($变量 != null) $变量.set($对象, $内容);
		} catch (IllegalAccessException $错误) {
		} catch (IllegalArgumentException $错误) {
		}
	}

	public static Constructor[] 取所有初始化方法(Class<?> $类) {
		if ($类 == null) return new Constructor[0];
		有序列表<Constructor> $所有 = new 有序列表<>();
		$所有.添加所有($类.getDeclaredConstructors());
		return $所有.到数组(Constructor.class);
	}

	public static <类型> 类型 实例化(Class<类型> $类) {
		return 实例化($类, new Object[0]);
	}

	public static <类型> 类型 实例化(Class<类型> $类, Object... $参数) {
		if ($类 == null) return null;
		if ($类.isEnum()) {
			throw new RuntimeException("不能实例化Enum！(没有初始化方法)");
		}
		Constructor[] $所有 = 取所有初始化方法($类);
		Constructor $方法 = null;
		Object[] $参数组 = null;
		for (int $键值 = 0; $键值 < $所有.length; $键值++) {
			Constructor $单个 = $所有[$键值];
			Object[] $适配参数 = 适配参数($单个, $参数);
			if ($适配参数 != null) {
				$方法 = $单个;
				$参数组 = $适配参数;
			}
		}
		if ($方法 == null) {
			String $同名 = "\n同名的初始化方法:" + $所有.length;
			if ($所有.length == 0) {
				$同名 += "\n没有";
			} else {
				for (Constructor $单个 : $所有) {
					$同名 += "\n" + $单个.toGenericString();
				}
			}
			throw new RuntimeException("没有那样的初始化方法:在类" + $类.getName() + "\n参数:" + 字符.分解(取参数类组($参数), ",") + "\n" + $同名);
		}
		try {
			$方法.setAccessible(true);
			return (类型) $方法.newInstance($参数组);
		} catch (Exception $错误) {
			错误.抛出($错误);
		}
		return null;
	}

	public static 函数 代理(final Object $对象, final String $方法名) {
		return (函数) $参数 -> 调用($对象, $方法名, $参数);
	}

	public static 函数 配置(Object $对象, String $方法名, Object... $配置参数) {
		return 反射.取方法方法($对象, $方法名, $配置参数);
	}

	public static Method[] 取所有方法(Class<?> $实例) {
		Method[] $所有 = null;
		if ($实例 == null) return new Method[0];
		else $所有 = $实例.getDeclaredMethods();
		有序列表<Method> $方法 = new 有序列表<Method>();
		for (Method $单个 : $所有) {
			$单个.setAccessible(true);
			$方法.添加($单个);
		}
		$方法.添加所有(取所有方法($实例.getSuperclass()));
		return $方法.到数组(Method.class);
	}

	public static Method[] 取所有方法(Class<?> $实例, String $名称) {
		Method[] $所有 = null;
		if ($实例 == null) return new Method[0];
		else $所有 = $实例.getDeclaredMethods();
		有序列表<Method> $方法 = new 有序列表<Method>();
		for (Method $单个 : $所有) {
			if ($名称.equals($单个.getName())) {
				$单个.setAccessible(true);
				$方法.添加($单个);
			}
		}
		$方法.添加所有(取所有方法($实例.getSuperclass(), $名称));
		return $方法.到数组(Method.class);
	}

	public static boolean 有方法(Object $对象, String $名称) {
		Class<?> $作用类;
		if ($对象 instanceof Class) {
			$作用类 = (Class<?>) $对象;
		} else {
			$作用类 = $对象.getClass();
		}
		return 有方法($作用类, $名称);
	}

	private static boolean 有方法(Class<?> $实例, String $名称) {
		Method[] $所有 = null;
		if ($实例 == null) return false;
		else $所有 = $实例.getDeclaredMethods();
		for (Method $单个 : $所有) {
			if ($名称.equals($单个.getName())) {
				return true;
			}
		}
		return 有方法($实例.getSuperclass(), $名称);
	}

	public static Object[] 变参(Object... $参数) {
		return $参数;
	}

	public static Object[] 取方法对象(Object $对象, String $名称, Object... $参数) {
		if ($参数 == null) $参数 = new Object[0];
		Method[] $所有 = 取所有方法($对象 instanceof Class ? (Class) $对象 : $对象.getClass(), $名称);
		Method $方法 = null;
		Object[] $参数组 = null;
		for (int $键值 = 0; $键值 < $所有.length; $键值++) {
			Method $单个 = $所有[$键值];
			Object[] $适配参数 = 适配参数($单个, $参数);
			if ($适配参数 != null) {
				$方法 = $单个;
				$参数组 = $适配参数;
			}
		}
		if ($方法 == null) {
			String $同名 = "\n同名的方法:" + $所有.length;
			if ($所有.length == 0) {
				$同名 += "\n没有";
			} else {
				for (Method $单个 : $所有) {
					$同名 += "\n" + $单个.toGenericString();
				}
			}
			throw new RuntimeException("没有那样的方法:在类" + $对象.getClass().getName() + "\n方法:" + $名称 + "\n参数:" + 字符.分解(取参数类组($参数), ",") + "\n" + $同名);
		}
		if ($对象 instanceof Class) {
			$对象 = null;
		}
		return new Object[]{$方法, $参数组};
	}

	public static 函数 取方法方法(Object $对象, String $名称, Object... $参数) {
		Object[] $返回 = 取方法对象($对象, $名称, $参数);
		final Method $方法对象 = (Method) $返回[0];
		final Object $实例对象 = $对象;
		final Object[] $传入参数 = (Object[]) $返回[1];
		return (函数) $参数1 -> {
			try {
				return $方法对象.invoke($实例对象, $传入参数);
			} catch (Exception $错误) {
				错误.抛出($错误);
			}
			return null;
		};
	}

	public static Object 调用(Object $对象, String $名称, Object... $参数) {
		Object[] $返回 = 取方法对象($对象, $名称, $参数);
		Method $方法对象 = (Method) $返回[0];
		Object[] $传入参数 = (Object[]) $返回[1];
		try {
			// System.out.print("调用方法 : " + $对象.getClass() + "\n方法名 : " + $名称 + "\n参数 :" + 字符.分解($参数, ","));
			return $方法对象.invoke($对象, $传入参数);
		} catch (Exception $错误) {
			错误.抛出($错误);
		}
		return null;
	}

	private static Object[] 适配参数(Method $单个, Object[] $参数) {
		Class[] $类组 = $单个.getParameterTypes();
		if ($类组.length == 0) {
			return $参数.length == 0 ? $参数 : null;
		}
		if ($类组.length > 1 && $参数.length == 0) {
			return null;
		}
		if ($类组.length != $参数.length && !是数组($类组[$类组.length - 1])) {
			return null;
		}
		有序列表 $返回集合 = new 有序列表();
		for (int $键值 = 0; $键值 < $类组.length; $键值++) {
			Class $类 = $类组[$键值];
			if ($键值 + 1 == $类组.length && 是数组($类)) {
				Object[] $剩余参数 = 数组.截取($参数, $键值, null);
				Class<?> $数组类型 = $类.getComponentType();
				for (Object $单个参数 : $剩余参数) {
					if ($单个参数 != null && !是子类($数组类型, $单个参数.getClass())) {
						return null;
					}
				}
				$返回集合.添加(数组.转换($数组类型, $剩余参数));
			} else if ($参数[$键值] != null && !是子类($类, $参数[$键值].getClass())) {
				return null;
			} else {
				$返回集合.添加($参数[$键值]);
			}
		}
		return $返回集合.到数组();
	}

	private static Object[] 适配参数(Constructor $单个, Object[] $参数) {
		Class[] $类组 = $单个.getParameterTypes();
		if ($类组.length == 0) {
			return $参数.length == 0 ? $参数 : null;
		}
		if ($类组.length > 1 && $参数.length == 0) {
			return null;
		}
		if ($类组.length != $参数.length && !是数组($类组[$类组.length - 1])) {
			return null;
		}
		有序列表 $返回集合 = new 有序列表();
		for (int $键值 = 0; $键值 < $类组.length; $键值++) {
			Class $类 = $类组[$键值];
			if ($键值 + 1 == $类组.length && 是数组($类)) {
				int $参数长度 = $参数.length - $类组.length;
				Object[] $剩余参数 = 数组.截取($参数, $键值, null);
				Class<?> $数组类型 = $类.getComponentType();
				for (Object $单个参数 : $剩余参数) {
					if ($单个参数 != null && !是子类($数组类型, $单个参数.getClass())) {
						return null;
					}
				}
				$返回集合.添加(数组.转换($数组类型, $剩余参数));
			} else if ($参数[$键值] != null && !是子类($类, $参数[$键值].getClass())) {
				return null;
			} else {
				$返回集合.添加($参数[$键值]);
			}
		}
		return $返回集合.到数组();
	}

	public static Class[] 取参数类组(Object... $参数) {
		Class[] $参数类组 = new Class[$参数.length];
		for (int i = 0, j = $参数.length; i < j; i++) {
			$参数类组[i] = $参数[i] != null ? $参数[i].getClass() : null;
		}
		return $参数类组;
	}

	public static boolean 是原生类型(Class<?> $类) {
		return 对应表.containsKey($类) || 对应表.containsValue($类);
	}

	public static boolean 是子类(Class<?> $类, Class<?> $子类) {
		if ($类.isAssignableFrom($子类)) {
			return true;
		} else if (对应表.检查($类) && 对应表.检查内容($子类)) {
			return true;
		} else return 对应表.检查($子类) && 对应表.检查内容($类);
	}

	public static boolean 是数组(Class<?> $类) {
		return $类.isArray();
	}

}
