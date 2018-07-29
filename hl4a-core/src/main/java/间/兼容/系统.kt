package 间.兼容

import 间.内容.上下文
import 间.内容.界面
import 间.工具.颜色
import 间.方法.函数
import 间.视图.工具.视图实现

abstract class 系统 {

    abstract fun 取系统(): String

    abstract fun 启动界面(_上下文: 上下文, _界面: Class<out 界面>)

    abstract fun 取视图实现(): 视图实现

    abstract fun 置剪切板(_内容: String)

    abstract fun 取剪切板(): String

    abstract fun 网络可用(): Boolean

    abstract fun 更换颜色(_颜色: 颜色)

    abstract fun 主线程执行(_函数: 函数<*>)

    override fun hashCode(): Int {

        return -1 * 取系统().hashCode() - 100
    }

    override fun equals(obj: Any?): Boolean {

        return super.equals(obj) || obj is 系统 && 取系统() == obj.取系统()
    }


}
