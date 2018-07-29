package 间.桌面.视图;

import javafx.geometry.Insets;
import javafx.scene.layout.Region;
import 间.视图.基本.基本视图;

public class 桌面基本视图 implements 基本视图.实现 {

	private Region 内容;

	public 桌面基本视图() {
		this(new Region());
	}

	public 桌面基本视图(Region _内容) {
		内容 = _内容;
		_内容.setUserData(取视图());
	}

	public Region 取内容() {
		return 内容;
	}

	@Override
	public int 取宽度() {
		return ((Double) 取内容().getWidth()).intValue();
	}

	@Override
	public void 置宽度(int _宽度) {
		取内容().setPrefWidth(_宽度);
	}

	@Override
	public int 取高度() {
		return ((Double) 取内容().getHeight()).intValue();
	}

	@Override
	public void 置高度(int _高度) {
		取内容().setPrefHeight(_高度);
	}

	@Override
	public void 置宽高(int _宽度, int _高度) {
		取内容().setPrefSize(_宽度, _高度);
	}

	@Override
	public void 置填充(int _填充) {
		取内容().setPadding(new Insets(_填充));
	}

	@Override
	public int 取上填充() {
		return ((Double) 取内容().getPadding().getTop()).intValue();
	}

	@Override
	public void 置上填充(int _填充) {
		置填充(_填充, 取下填充(), 取左填充(), 取右填充());
	}

	@Override
	public int 取下填充() {
		return ((Double) 取内容().getPadding().getBottom()).intValue();
	}

	@Override
	public void 置下填充(int _填充) {
		置填充(取上填充(), _填充, 取左填充(), 取右填充());
	}

	@Override
	public int 取左填充() {
		return ((Double) 取内容().getPadding().getLeft()).intValue();
	}

	@Override
	public void 置左填充(int _填充) {
		置填充(取上填充(), 取下填充(), _填充, 取右填充());
	}

	@Override
	public int 取右填充() {
		return ((Double) 取内容().getPadding().getRight()).intValue();
	}

	@Override
	public void 置右填充(int _填充) {
		置填充(取上填充(), 取下填充(), 取左填充(), _填充);
	}

	@Override
	public void 置填充(int _上, int _下, int _左, int _右) {
		取内容().setPadding(new Insets(_上, _右, _下, _左));
	}

}
