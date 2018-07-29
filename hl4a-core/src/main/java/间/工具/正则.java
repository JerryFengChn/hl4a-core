package 间.工具;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class 正则 {

	private Pattern 表达式;

	public 正则(String _内容) {

		表达式 = Pattern.compile(_内容);
	}

	public 结果[] 匹配(String _文本) {

		Matcher _结果 = 表达式.matcher(_文本);
		结果[] _返回 = new 结果[_结果.groupCount()];
		for (int _键值 = 0; _键值 < _结果.groupCount(); _键值++) {
			_返回[_键值] = new 结果(_结果.group(_键值), _结果.start(_键值), _结果.end(_键值));
		}
		return _返回;
	}

	public class 结果 {

		public final String 内容;
		public final int 开始;
		public final int 结束;

		public 结果(String _内容, int _开始, int _结束) {

			内容 = _内容;
			开始 = _开始;
			结束 = _结束;
		}

		@Override
		public String toString() {

			return "[正则结果 桌面: " + 内容 + "\n开始: " + 开始 + " 关闭: " + 结束 + "]";
		}

	}

}
