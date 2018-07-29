package 间.桌面.视图;

import javafx.collections.ListChangeListener;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import 间.收集.哈希表;
import 间.视图.基本.基本布局;
import 间.视图.基本.基本视图;

public class 桌面基本布局 extends 桌面基本视图 implements 基本布局.实现 {

	private 哈希表<Integer, 基本视图> 子视图 = new 哈希表<>();

	public 桌面基本布局() {
		this(new Pane());
	}

	public 桌面基本布局(Pane _内容) {
		super(_内容);
		取内容().getChildren().addListener(this::子视图改变事件);
	}

	private void 子视图改变事件(ListChangeListener.Change<? extends Node> _改变) {

		if (_改变.next()) {

			for (int _键值 = _改变.getFrom(); _键值 < _改变.getTo(); _键值++) {
				if (_改变.wasRemoved()) {
					子视图.remove(_键值);
				} else {
					子视图.设置(_键值, (基本视图) _改变.getList().get(_键值).getUserData());
				}
			}

		}

	}

	@Override
	public Pane 取内容() {
		return (Pane) super.取内容();
	}

	@Override
	public void 加入子视图(基本视图... _视图) {

		for (基本视图 _单个 : _视图) {

			if (!(_单个.取实现() instanceof 桌面基本视图)) throw new IllegalStateException("不支持的视图实现");

			桌面基本视图 _基本视图 = (桌面基本视图) _单个.取实现();

			取内容().getChildren().add(_基本视图.取内容());

		}

	}

	@Override
	public 基本视图 取子视图(int _键值) {
		return (基本视图) 取内容().getChildren().get(_键值).getUserData();
	}

	@Override
	public 基本视图[] 取子视图() {
		return 子视图.values().toArray(new 基本视图[取内容().getChildren().size()]);
	}

	@Override
	public 基本视图 删子视图(int _键值) {
		return (基本视图) 取内容().getChildren().remove(_键值).getUserData();
	}

	@Override
	public 基本视图[] 删子视图() {
		基本视图[] _所有 = 子视图.values().toArray(new 基本视图[取内容().getChildren().size()]);
		取内容().getChildren().removeAll();
		return _所有;
	}

}
