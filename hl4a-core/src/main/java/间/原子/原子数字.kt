package 间.原子

import java.util.concurrent.atomic.AtomicInteger

class 原子数字 {

    private var 数字: AtomicInteger

    constructor() {
        数字 = AtomicInteger()
    }

    constructor(_初始: Int) {
        数字 = AtomicInteger(_初始)
    }

    fun 设置(_数字: Int) {
        数字.set(_数字)
    }

    fun 读取(): Int {
        return 数字.get()
    }

    fun 加一(): Int {
        return 数字.addAndGet(1)
    }

}
