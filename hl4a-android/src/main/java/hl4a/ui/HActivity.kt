package hl4a.ui

import 间.内容.上下文
import 间.内容.界面

class HActivity : Activity(), 上下文 {

    companion object {

        fun 启动界面(_上下文: 上下文, _界面: Class<out 界面>) {

            var _安卓上下文: Context

            if (_上下文 is 应用)

                if (_上下文 is 界面) {

                    val _实现 = _上下文.取实现()

                    if (_实现 is Activity) {

                        _安卓上下文 = _实现

                    }

                }

            val _意图 = Intent(_安卓上下文, HActivity.javaClass)

        }

    }

}
