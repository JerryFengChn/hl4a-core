package 间.安卓

import 间.兼容.系统
import 间.内容.上下文
import 间.内容.界面
import 间.工具.颜色
import 间.方法.函数
import 间.视图.工具.视图实现

class 安卓系统 : 系统() {

    override fun 取系统(): String {
        return "Android"
    }

    override fun 启动界面(_上下文: 上下文, _界面: Class<out 界面>) {

    }

    override fun 取视图实现(): 视图实现 {

    }

    override fun 置剪切板(_内容: String) {

    }

    override fun 取剪切板(): String {

    }

    override fun 网络可用(): Boolean {

    }

    override fun 更换颜色(_颜色: 颜色) {

    }

    override fun 主线程执行(_函数: 函数<*>) {

    }


}