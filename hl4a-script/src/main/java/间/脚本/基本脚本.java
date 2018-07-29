package 间.脚本;

public interface 基本脚本 {

	String 取脚本类型();

	void 压入变量(String $名称, Object $对象);

	Object 读取对象(String $名称);

	Object 执行代码(String $代码);

	Object 运行文件(String $文件);

	Object 调用函数(String $方法, Object... $参数);

}
