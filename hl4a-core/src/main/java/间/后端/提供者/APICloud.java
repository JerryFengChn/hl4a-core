package 间.后端.提供者;

/*

public class APICloud extends 后端 {

    public static final String 请求地址 = "https://d.apicloud.com/";
    public static final String 请求头名称_应用ID = "X-APICloud-AppId";
    public static final String 请求头名称_应用KEY = "X-APICloud-AppKey";


    private String 应用ID;
    private String 应用KEY;

    private 连接工厂 工厂;

    public APICloud(String _应用ID, String _应用KEY) {

        断言.空(_应用ID, "ApplicationId 不能为空！");
        断言.空(_应用KEY, "应用KEY 不能为空！");

        应用ID = _应用ID;
        应用KEY = _应用KEY;

        工厂 = new 连接工厂();

        工厂.JSON输出(true);

        工厂.地址(请求地址);

        工厂.请求头(请求头名称_应用KEY, 应用ID);

    }

    private String 创建应用Key() {

        long _毫秒 = 时间.时间戳();

        String _KEY = 应用ID + "UZ" + 应用KEY + "UZ" + _毫秒;

        _KEY = 加密.摘要("SHA1", _KEY) + "." + _毫秒;

        return _KEY;

    }

    private 连接 创建请求(String _后缀, String _模式) {

        连接 _连接 = 工厂.新建(_后缀, _模式);
        _连接.请求头(请求头名称_应用KEY, 创建应用Key());
        return _连接;

    }

    @Override
    public 返回值 同步创建对象(String _表, Map _参数) {

        连接 _连接 = 创建请求("mcm/api/" + _表, "POST");
        _连接.JSON(_参数);
        
        return null;
    }


}

*/
