package 间.内容

class 安卓界面 : Activity() {

    // ---- 事件开始

    fun 界面创建事件(_恢复: Bundle) {}
    fun 界面启动事件() {}
    fun 界面刷新事件() {}
    fun 界面遮挡事件() {}
    fun 界面回调事件(_请求码: Int, _返回码: Int, _意图: Intent) {}
    fun 离开界面事件() {}
    fun 界面销毁事件() {}
    fun 取得焦点事件() {}
    fun 失去焦点事件() {}
    fun 按键按下事件(_按键码: Int, _事件: KeyEvent): Boolean {
        return false
    }

    fun 返回按下事件(): Boolean {
        return false
    }

    fun 收到意图事件(_意图: Intent) {}
    fun 保存状态事件(_输出: Bundle) {}
    fun 权限回调事件() {}

    fun 跳转界面(_界面: Class<out Activity>, _意图: Intent) {

        跳转界面(_界面, -1, _意图)

    }

    @JvmOverloads
    fun 跳转界面(_界面: Class<out Activity>, _请求码: Int = -1, _意图: Intent = Intent()) {

        _意图.setClass(this, _界面)

        startActivityForResult(_意图, _请求码)

    }

    fun 置布局(_布局: Int) {
        setContentView(_布局)
    }

    fun 置布局(_布局: View) {
        setContentView(_布局)
    }

    fun 取布局(): View {
        return window.decorView
    }

    fun <视图 : View> 找视图(_ID: Int): 视图 {
        return findViewById(_ID)
    }

    fun 取标题(): CharSequence {
        return title
    }

    fun 置标题(_标题: Int) {
        setTitle(_标题)
    }

    fun 置标题(_标题: CharSequence) {
        title = _标题
    }

    fun 结束界面() {
        finish()
    }

    // ---- 原生事件

    override fun onCreate(savedInstanceState: Bundle) {
        super.onCreate(savedInstanceState)

        界面创建事件(savedInstanceState)
    }

    override fun onStart() {
        super.onStart()
        界面启动事件()
    }

    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
        if (hasFocus) {
            取得焦点事件()
        } else {
            失去焦点事件()
        }
    }

    override fun onNewIntent(_意图: Intent) {
        super.onNewIntent(_意图)
        收到意图事件(_意图)
    }

    override fun onSaveInstanceState(_输出: Bundle) {
        super.onSaveInstanceState(_输出)
        保存状态事件(_输出)
    }

    override fun onResume() {
        super.onResume()
        界面刷新事件()
    }

    override fun onPause() {
        super.onPause()
        界面遮挡事件()
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent): Boolean {
        if (按键按下事件(keyCode, event)) {
            return true
        }
        return if (keyCode == KeyEvent.KEYCODE_BACK && 返回按下事件()) {
            true
        } else super.onKeyDown(keyCode, event)
    }


    override fun onActivityResult(_请求码: Int, _返回码: Int, _意图: Intent) {
        super.onActivityResult(_请求码, _请求码, _意图)
        界面回调事件(_请求码, _返回码, _意图)
    }

    override fun onStop() {
        super.onStop()
        离开界面事件()
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        权限回调事件()
    }

    override fun onDestroy() {
        super.onDestroy()
        界面销毁事件()
    }

    fun 置返回值(_请求码: Int) {
        setResult(_请求码)
    }

    fun 置返回值(_结果码: Int, _意图: Intent) {
        setResult(_结果码, _意图)
    }

}