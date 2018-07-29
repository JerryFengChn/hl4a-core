package 间.收集;

@SuppressWarnings({"NonAsciiCharacters", "unchecked"})
public class 二级表<对象, 键值, 内容> {

	private 哈希表<对象, I哈希表<键值, 内容>> 表;

	public 二级表() {

		表 = new 哈希表<>();
	}

	protected I哈希表<键值, 内容> 读取二级表(对象 _键值) {

		I哈希表<键值, 内容> _表 = 表.读取(_键值);
		if (_表 == null) {
			_表 = new 哈希表<>();
			表.设置(_键值, _表);
		}
		return _表;
	}

	public 内容 读取(对象 _对象, Object _键值) {

		if (表.检查(_对象)) {
			return 读取二级表(_对象).读取(_键值);
		}
		return null;
	}

	public 内容 设置(对象 _对象, 键值 _键值, 内容 _内容) {

		return 读取二级表(_对象).设置(_键值, _内容);
	}

	public 内容 删除(对象 _对象, 键值 _键值) {

		return 读取二级表(_对象).删除(_键值);
	}

	public I哈希表<键值, 内容> 删除(对象 _对象) {

		return 表.删除(_对象);
	}

}
