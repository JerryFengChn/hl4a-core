package 间.内容

import 间.视图.基本.基本视图

import java.io.Serializable

open class 界面 : 上下文, Serializable {

    private var 当前实现: 实现? = null

    fun 初始化实现(_实现: 实现) {
        if (当前实现 != null) throw IllegalStateException("已经初始化过了")
        当前实现 = _实现
    }

    fun 取实现(): 实现 {
        if (当前实现 == null) throw IllegalStateException("请不要手动实例化界面")
        return 当前实现!!
    }

    fun 显示() {
        取实现().显示()
    }

    fun 关闭() {
        取实现().关闭()
    }

    fun 置标题(_标题: String) {
        取实现().置标题(_标题)
    }

    fun 取标题(): String {
        return 取实现().取标题()
    }

    fun 置内容(_视图: 基本视图) {
        取实现().置内容(_视图)
    }

    open fun 界面创建事件() {

    }

    // =========================

    interface 实现 {

        fun 取界面(): 界面? {
            return null
        }

        fun 显示()

        fun 关闭()

        fun 置标题(_标题: String)

        fun 取标题(): String

        fun 置内容(_视图: 基本视图)

    }


}