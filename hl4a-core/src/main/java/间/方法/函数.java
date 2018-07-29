package 间.方法;

import java.io.Serializable;

@FunctionalInterface
public interface 函数<返回值> extends Runnable, Serializable {

	返回值 调用(Object... _参数);

	@Override
	default void run() {

		调用();
	}

	@FunctionalInterface
	interface 零<返回值> extends 函数<返回值> {

		返回值 调用();

		@Override
		default 返回值 调用(Object[] _参数) {

			return 调用();
		}

	}

	@FunctionalInterface
	interface 一<返回值, 参数一> extends 函数<返回值> {

		返回值 调用(参数一 _参数);

		@Override
		default 返回值 调用(Object[] _参数) {

			return 调用((参数一) _参数[0]);
		}

	}

	@FunctionalInterface
	interface 二<返回值, 参数一, 参数二> extends 函数<返回值> {

		返回值 调用(参数一 _一, 参数二 _二);

		@Override
		default 返回值 调用(Object[] _参数) {

			return 调用((参数一) _参数[0], (参数二) _参数[1]);
		}

	}

	@FunctionalInterface
	interface 三<返回值, 参数一, 参数二, 参数三> extends 函数<返回值> {

		返回值 调用(参数一 _一, 参数二 _二, 参数三 _三);

		@Override
		default 返回值 调用(Object[] _参数) {

			return 调用((参数一) _参数[0], (参数二) _参数[1], (参数三) _参数[2]);
		}

	}

}
