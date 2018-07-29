package 间.后端

import 间.工具.断言
import 间.接口.返回值

abstract class 后端 {

    abstract fun 同步创建对象(_表: String, _参数: Map<*, *>, _是否刷新: Boolean): 后端返回值<Map<*, *>>

    abstract fun 同步获取对象(_表: String, _对象ID: String): 后端返回值<Map<*, *>>

    abstract fun 同步获取对象(_表: String, _对象ID: String, _包含列: String): 后端返回值<Map<*, *>>

    abstract fun 同步更新对象(_表: String, _对象ID: String, _参数: Map<*, *>, _是否刷新: Boolean): 后端返回值<Map<*, *>>

    abstract fun 同步查询对象(_表: String, _条件: Map<*, *>): 后端返回值<List<Map<*, *>>>

    abstract fun 同步数据库查询(_条件: String): 后端返回值<List<Map<*, *>>>

    abstract fun 同步删除对象(_表: String, _对象ID: String): 后端返回值<Map<*, *>>

    abstract class 后端返回值<内容> : 返回值<内容>() {

        private var 错误码: Int = 0

        fun 置错误码(_状态码: Int): 后端返回值<*> {

            错误码 = _状态码
            return this
        }

        fun 取错误码(): Int {

            return 错误码
        }

        abstract override fun 取错误信息(): String

    }

    companion object {

        private var 当前后端: 后端? = null

        fun 置当前后端(_后端: 后端) {
            当前后端 = _后端
        }

        fun 取当前后端(): 后端 {
            断言.不为空(当前后端, "还没有设置后端 ~")
            return 当前后端!!
        }
    }

}
