package 间.工具

import android.app.ActivityThread

object 安卓环境 {

    fun 取应用(): Application {

        return ActivityThread.currentApplication()

    }

}