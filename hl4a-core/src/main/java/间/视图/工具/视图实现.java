package 间.视图.工具;

import 间.视图.垂直布局;
import 间.视图.基本.基本布局;
import 间.视图.基本.基本视图;
import 间.视图.文本视图;
import 间.视图.水平布局;
import 间.视图.线性布局;

public abstract class 视图实现 {

	public abstract 基本视图.实现 新基本视图实现(基本视图 _视图);

	public abstract 基本布局.实现 新基本布局实现(基本布局 _视图);

	public abstract 线性布局.实现 新线性布局实现(垂直布局 _视图);

	public abstract 线性布局.实现 新线性布局实现(水平布局 _视图);

	public abstract 文本视图.实现 新文本视图实现(文本视图 _视图);

}