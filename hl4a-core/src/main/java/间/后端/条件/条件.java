package 间.后端.条件;

import 间.后端.查询;
import 间.收集.I哈希表;
import 间.收集.哈希表;
import 间.数据.JSON;

import java.util.List;

public class 条件 extends 哈希表<String, Object> implements I条件 {

	@Override
	public int 类型() {

		return 0;
	}

	public 条件 小于(String _键值, int _内容) {

		置键值对(_键值, new 小于(_内容));
		return this;
	}

	public 条件 小于等于(String _键值, int _内容) {

		置键值对(_键值, new 小于等于(_内容));
		return this;
	}

	public 条件 大于(String _键值, int _内容) {

		置键值对(_键值, new 大于(_内容));
		return this;
	}

	public 条件 大于等于(String _键值, int _内容) {

		置键值对(_键值, new 大于等于(_内容));
		return this;
	}

	public 条件 不等于(String _键值, Object _内容) {

		置键值对(_键值, new 不等于(_内容));
		return this;
	}

	public 条件 等于(String _键值, Object _内容) {

		设置(_键值, _内容);
		return this;
	}

	public 条件 包含(String _键值, List _内容) {

		置键值对(_键值, new 包含(_内容));
		return this;
	}

	public 条件 包含所有(String _键值, List _内容) {

		置键值对(_键值, new 包含所有(_内容));
		return this;
	}

	public 条件 不包含(String _键值, List _内容) {

		置键值对(_键值, new 不包含(_内容));
		return this;
	}

	public 条件 为空(String _键值, boolean _为空) {

		置键值对(_键值, new 为空(_为空));
		return this;
	}

	public 条件 关系查询(String _键值, 查询 _内容) {

		置键值对(_键值, new 关系查询(_内容));
		return this;
	}

	public 条件 关系内容查询(String _键值, 查询 _内容, String _查询键值) {

		置键值对(_键值, new 关系内容查询(_内容, _查询键值));
		return this;
	}

	public 条件 正则匹配(String _键值, String _表达式) {

		置键值对(_键值, new 正则匹配(_表达式));
		return this;
	}

	public 条件 正则特性(String _键值, String _特性) {

		置键值对(_键值, new 正则特性(_特性));
		return this;
	}

	protected 哈希表 取键值条件(String _键值) {

		if (!检查(_键值) || !(读取(_键值) instanceof 哈希表)) {
			设置(_键值, new 哈希表());
		}
		return (哈希表) 读取(_键值);
	}

	protected void 置键值对(String _键值, 键值对条件 _键值对) {

		取键值条件(_键值).设置(_键值对.取键值(), _键值对.取内容());
	}

	@Override
	public String toString() {

		return JSON.转换(this);
	}

	protected static class 正则特性 extends 键值对条件<String> {

		public 正则特性(String _特性) {

			super("_options", _特性);
		}

	}

	protected static class 正则匹配 extends 键值对条件<String> {

		public 正则匹配(String _表达式) {

			super("_regex", _表达式);
		}

	}

	protected static class 关系查询 extends 键值对条件<查询> {

		public 关系查询(查询 _内容) {

			super("_inQuery", _内容);
		}

	}

	protected static class 关系内容查询 extends 键值对条件<I哈希表> {

		public 关系内容查询(查询 _内容, String _查询键值) {

			super("_select", 制作表(_内容, _查询键值));
		}

		protected static I哈希表<String, Object> 制作表(查询 _内容, String _查询键值) {

			哈希表<String, Object> _表 = new 哈希表<>();
			_表.设置("query", _内容);
			_表.设置("key", _查询键值);
			return _表;
		}
	}

	protected static class 键值对条件<内容> {

		protected String 当前键值;
		protected 内容 当前内容;

		public 键值对条件(String _键值, 内容 _内容) {

			当前键值 = _键值;
			当前内容 = _内容;
		}

		public String 取键值() {

			return 当前键值;
		}

		public 内容 取内容() {

			return 当前内容;
		}

	}

	protected class 小于 extends 键值对条件<Integer> {

		public 小于(Integer _内容) {

			super("_lt", _内容);
		}

	}

	protected class 小于等于 extends 键值对条件<Integer> {

		public 小于等于(Integer _内容) {

			super("_lte", _内容);
		}

	}

	protected class 大于 extends 键值对条件<Integer> {

		public 大于(Integer _内容) {

			super("_gt", _内容);
		}

	}

	protected class 大于等于 extends 键值对条件<Integer> {

		public 大于等于(Integer _内容) {

			super("_gte", _内容);
		}

	}

	protected class 不等于 extends 键值对条件<Object> {

		public 不等于(Object _内容) {

			super("_ne", _内容);
		}

	}

	protected class 包含 extends 键值对条件<List> {

		public 包含(List _列表) {

			super("_in", _列表);
		}

	}

	protected class 为空 extends 键值对条件<Boolean> {

		public 为空(Boolean _列表) {

			super("_exists", _列表);
		}

	}

	protected class 包含所有 extends 键值对条件<List> {

		public 包含所有(List _列表) {

			super("_all", _列表);
		}

	}

	protected class 不包含 extends 键值对条件<List> {

		public 不包含(List _列表) {

			super("_nin", _列表);
		}

	}

}
