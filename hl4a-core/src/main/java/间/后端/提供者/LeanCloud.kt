package 间.后端.提供者

import 间.toAny
import 间.原子.原子布尔
import 间.后端.后端
import 间.工具.*
import 间.方法.简单函数
import 间.网络.连接
import 间.网络.连接工厂

class LeanCloud @JvmOverloads constructor(_应用ID: String, private val 应用KEY: String, private val MasterKey: String? = null) : 后端() {
    private var 是MasterKey = false
    private val 服务器: LC服务器
    private val 工厂: 连接工厂

    init {

        断言.不为空(_应用ID, "应用ID不能为空 ~")
        断言.不为空(应用KEY, "应用Key不能为空 ~")
        是MasterKey = MasterKey == null

        服务器 = LC服务器(_应用ID)

        工厂 = 连接工厂()
        工厂.JSON输出(true)
        工厂.标识(默认请求UA)
        工厂.请求头("X-LC-Id", _应用ID)
        工厂.请求头("X-LC-Key", 应用KEY)
        // 工厂.请求头("X-LC-Sign", 计算LCSIGN());

    }

    private fun 计算LCSIGN(): String {

        val _时间戳 = 时间.时间戳()

//_LCSIGN = 散列.摘要("MD5",_LCSIGN) + "," + _时间戳 + (是MasterKey ? ",master" : "");

        return " ${_时间戳}${if (是MasterKey) MasterKey else 应用KEY}"

    }

    private fun 同步新建连接(_后缀: String, _模式: String): 连接? {

        if (服务器.同步准备()) {
            服务器.重新准备()
            if (服务器.同步准备()) return null
        }

        return 工厂.新建("https://" + 服务器.取信息()!!.接口服务器 + "/" + API版本 + "/" + _后缀, _模式)

    }

    private fun 同步列表查询(_连接: 连接): LC返回值<List<Map<*, *>>> {

        val _结果 = _连接.同步()

        if (_结果.状态码() == -1) {
            return 连接服务器失败.toAny()
        }

        val _表 = _结果.JSON表()

        val _返回值 = LC返回值<List<Map<*, *>>>(_结果.状态码(), _表)

        _返回值.置内容(_表["results"]?.toAny())

        return _返回值

    }

    private fun 同步查询(_连接: 连接): LC返回值<Map<*, *>> {

        val _结果 = _连接.同步()

        if (_结果.状态码() == -1) {
            return 连接服务器失败.toAny()
        }

        val _表 = _结果.JSON表()

        val _返回值 = LC返回值<Map<*, *>>(_结果.状态码(), _表)

        _返回值.置内容(_表)

        return _返回值

    }

    override fun 同步创建对象(_表: String, _参数: Map<*, *>, _是否刷新: Boolean): LC返回值<Map<*, *>> {

        val _连接 = 同步新建连接("classes/$_表", "POST") ?: return 连接服务器失败.toAny()
        _连接.JSON(_参数)
        if (_是否刷新) {
            _连接.链接参数("fetchWhenSave", "true")
        }
        return 同步查询(_连接)

    }

    override fun 同步获取对象(_表: String, _对象ID: String): 后端.后端返回值<Map<*, *>> {

        val _连接 = 同步新建连接("classes/$_表/$_对象ID", "GET") ?: return 连接服务器失败.toAny()
        return 同步查询(_连接)
    }

    override fun 同步获取对象(_表: String, _对象ID: String, _包含列: String): 后端.后端返回值<Map<*, *>> {

        val _连接 = 同步新建连接("classes/$_表/$_对象ID", "GET") ?: return 连接服务器失败.toAny()
        if (!_包含列.isEmpty()) {
            _连接.链接参数("include", _包含列)
        }
        return 同步查询(_连接)
    }

    override fun 同步更新对象(_表: String, _对象ID: String, _参数: Map<*, *>, _是否刷新: Boolean): LC返回值<Map<*, *>> {

        val _连接 = 同步新建连接("classes/$_表/$_对象ID", "PUT") ?: return 连接服务器失败.toAny()
        _连接.JSON(_参数)
        if (_是否刷新) {
            _连接.链接参数("fetchWhenSave", "true")
        }
        return 同步查询(_连接)
    }

    override fun 同步查询对象(_表: String, _条件: Map<*, *>): LC返回值<List<Map<*, *>>> {

        val _连接 = 同步新建连接("classes/$_表", "GET") ?: return 连接服务器失败.toAny()
        if (!_条件.isEmpty()) {
            _连接.链接参数(_条件.toAny())
        }
        return 同步列表查询(_连接)
    }

    override fun 同步数据库查询(_脚本: String): 后端.后端返回值<List<Map<*, *>>> {

        val _连接 = 同步新建连接("cloudQuery", "GET") ?: return 连接服务器失败.toAny()
        _连接.链接参数("cql", _脚本)
        return 同步列表查询(_连接)
    }

    override fun 同步删除对象(_表: String, _对象ID: String): 后端.后端返回值<Map<*, *>> {

        val _连接 = 同步新建连接("classes/$_表/$_对象ID", "DELETE") ?: return 连接服务器失败.toAny()
        return 同步查询(_连接)
    }

    class LC服务器(private val 应用ID: String) {

        private val 已准备好 = 原子布尔(false)
        private val 服务器可用 = 原子布尔(false)
        private var 信息: LC服务器信息? = null
        private val 缓存地址: String

        init {
            缓存地址 = "$服务器缓存/$应用ID.yml"

            处理.异步(简单函数 { this.初始化() })

        }

        fun 取信息(): LC服务器信息? {

            return 信息
        }

        private fun 初始化() {

            if (文件(缓存地址).取是文件()) {

                信息 = 字节.反序列化<LC服务器信息>(字节.读取(缓存地址))

            }

            if (信息 != null) {

                已准备好.设置(true)
                服务器可用.设置(true)
                return

            }

            val _结果 = 连接(默认域名获取 + 应用ID).同步()

            if (!_结果.成功()) {

                已准备好.设置(true)
                return

            }

            val _表 = _结果.JSON表()

            if (_表 == null) {

                已准备好.设置(true)
                return

            }

            信息 = LC服务器信息()

            信息!!.更新时间 = _表.读取("ttl") as Int
            信息!!.接口服务器 = _表.读取("api_server") as String
            信息!!.统计服务器 = _表.读取("stats_server") as String
            信息!!.实时通信服务器 = _表.读取("rtm_router_server") as String
            信息!!.推送服务器 = _表.读取("push_server") as String
            信息!!.引擎服务器 = _表.读取("engine_server") as String

            字节.保存(缓存地址, 字节.序列化(信息))

            已准备好.设置(true)
            服务器可用.设置(true)

        }

        fun 重新准备() {

            已准备好.设置(false)
            服务器可用.设置(false)
            处理.异步(简单函数 { this.初始化() })

        }

        fun 同步准备(): Boolean {

            while (!已准备好.读取()) {

                线程.暂停(100)

            }

            return 服务器可用.读取()

        }

        class LC服务器信息 {

            var 更新时间: Int? = null // ttl
            var 接口服务器: String? = null // api_server
            var 统计服务器: String? = null // stats_server
            var 实时通信服务器: String? = null // rtm_router_server
            var 推送服务器: String? = null // push_server
            var 引擎服务器: String? = null // engine_server

        }

        companion object {

            private val 服务器缓存 = "_后端/LC服务器缓存"
            private val 默认域名获取 = "https://app-router.leancloud.cn/2/route?appId="
        }

    }

    class LC返回值<内容> : 后端.后端返回值<内容> {

        constructor(_错误码: Int) {

            置错误码(_错误码)
            置状态(_错误码 == -1)
        }

        constructor(_状态码: Int, _数据: Map<*, *>) {

            置状态(_状态码 >= 200 && _状态码 < 300)
            if (!成功()) {
                置错误码(_数据["code"] as Int)
            } else {
                置错误码(-1)
            }
        }

        override fun 取错误信息(): String {

            when (取错误码()) {
                -1 -> return "成功"
                0 -> return "本地网络错误"
                1 -> return "服务器错误"
                100 -> return "无法连接服务器"
                101 -> return "查询的 Class 或关联的 Pointer 不存在"
                103 -> return "非法 Class 名称"
                104 -> return "缺少 ObjectId"
                105 -> return "无效的 Key名称 (列名)"
                106 -> return "无效的 Pointer 格式"
                107 -> return "无效的 JSON 对象 解析失败"
                108 -> return "API 仅供内部使用"
                109 -> return "无权限执行此操作"
                111 -> return "存储的值不匹配列的类型"
                112 -> return "推送订阅的频道无效"
                113 -> return "Class 中的某个字段设定成必须，保存的对象缺少该字段。"
                116 -> return "要存储的对象超过了大小限制 (16M)"
                117 -> return "无法操作只读字段 更新失败"
                118 -> return "无法进行禁止的操作"
                120 -> return "查询结果无法从缓存中找到"
                121 -> return "JSON key 的名称不能包含 _ 和 ."
                122 -> return "文件名称只能是英文字母、数字和下划线，长度限制 36。"
                124 -> return "请求超时无响应"
                125 -> return "电子邮箱地址无效"
                126 -> return "ID无效 用户不存在"
                127 -> return "手机号码无效"
                128 -> return "操作的Relation数量为0或超过1000"
                137 -> return "唯一字段重复"
                139 -> return "权限组名称只能以英文字母、数字或下划线组成。"
                140 -> return "服务器超过免费额度 (本月)"
                141 -> return "云引擎调用超时"
                142 -> return "云引擎校验错误"
                145 -> return "本设备没有启用支付功能"
                150 -> return "转换数据到图片失败"
                154 -> return "超过应用可用上限"
                160 -> return "账户余额不足"

                200 -> return "用户名为空"
                201 -> return "密码为空"
                202 -> return "用户名被占用"
                203 -> return "邮箱被占用"
                204 -> return "没有提供邮箱"
                205 -> return "邮箱地址没有对应的用户"
                206 -> return "无权操作/未登录"
                207 -> return "第三方登录已禁用"
                208 -> return "第三方帐号已经绑定其他用户"
                210 -> return "密码错误"
                211 -> return "用户不存在"
                212 -> return "需要提供手机号码"
                213 -> return "该号码没有对应用户"
                214 -> return "手机号码已经被注册"
                215 -> return "手机号码未验证"
                216 -> return "邮箱地址未验证"
                217 -> return "用户名不能为空"
                218 -> return "密码不能为空"
                219 -> return "失败次数超限 请稍后再试"

                250 -> return "第三方账户没有返回用户唯一标示"
                251 -> return "无效的账户连接 (微博)"
                252 -> return "无效的微信授权信息"
                300 -> return "CQL语法错误 (数据库)"
                301 -> return "新增对象失败"
                302 -> return "无效的 GeoPoint 类型"
                303 -> return "插入数据库失败"
                304 -> return "LeanCloud 内部异常"
                305 -> return "条件不满足 删除/更新失败"
                401 -> return "客户端非法"
                403 -> return "无权操作/未登录"
                429 -> return "超过应用的流控限制"
                430 -> return "超过上传文件流控限制"
                431 -> return "超过云引擎 hook 调用流控限制"
                502 -> return "服务器维护中 (LeanCloud)"
                503 -> return "应用临时维护"
                511 -> return "API 暂时不可用"
                524 -> return "与后端应用服务器通讯失败"
                529 -> return "当前 IP 并发超过限制"
                600 -> return "无效的短信签名"
                601 -> return "发送短信过于频繁"
                602 -> return "号码错误/发送失败"
                603 -> return "短信验证码错误/过期"
                604 -> return "找不到自定义的短信模板"
                605 -> return "短信模板未审核/没有设置默认签名"
                606 -> return "渲染短信模板失败 通常是模板语法问题"
                700 -> return "无效的查询或者排序字段"
            // 以下为通信IM模块错误码 未补全
                1006 -> return "连接非正常关闭"
                4100 -> return "应用不存在/实时通信禁用"
            }
            return "未知错误"
        }


    }

    companion object {

        val API版本 = "1.1"

        val 本地网络错误 = LC返回值<Unit>(0)
        val 连接服务器失败 = LC返回值<Unit>(100)
        // 模拟 java-sdk 发起请求

        private val 模拟SDK版本 = "5.0.0"
        private val 默认请求UA = "LeanCloud SDK v$模拟SDK版本"
    }

}
