package 间.内容

import 间.工具.设备

interface 上下文 {

    fun 启动界面(_界面: Class<out 界面>) {
        设备.取当前系统().启动界面(this, _界面)
    }

}