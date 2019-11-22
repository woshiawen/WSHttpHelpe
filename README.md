# WSHttpHelpe
OkHttp 和Rertorfit2.0 + 工厂模式等设计的一个便捷开发的网络库框架




1.添加依赖 
 implementation 'com.github.woshiawen:WSHttpHelpe:v1.0'
 
2. 在Application中的onCreate中初始话

public class BaseApplication extends Application {
    private HttpConfig httpConfig;

    @Override
    public void onCreate() {
        super.onCreate();

        //默认配置URL
        if (httpConfig == null) {
            httpConfig = new HttpConfig();
        }
        WSHttpClient.init(httpConfig);
    }
}

