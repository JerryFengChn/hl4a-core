package 间.原子

import java.util.concurrent.atomic.AtomicBoolean

class 原子布尔 {

    private val 布尔 = AtomicBoolean()

    constructor()

    constructor(_初始: Boolean) {
        设置(_初始)
    }

    fun 设置(_布尔: Boolean): Boolean {
        return 布尔.getAndSet(_布尔)
    }

    fun 读取(): Boolean {
        return 布尔.get()
    }

}
