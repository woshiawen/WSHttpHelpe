# WSHttpHelper
OkHttp 和Rertorfit2.0 + 工厂模式等设计的一个便捷网络库帮助类,本帮助类使用了OkHttp3.0+Retrofit2.0+RxJava+RxAndroid实现,
只需要简单几步，即可实现快速搭建一个App的网络框架.




1.添加权限
在AndroidManifest.xml中添加 网络访问的权限
<uses-permission android:name="android.permission.INTERNET"/>

2.添加以下依赖

    //本项目依赖
    implementation 'com.github.woshiawen:WSHttpHelpe:v1.0.6'


    //以下为选择实现 如果你的项目里已经存在了 则无需实现  如果不存在则实现
    // Retrofit库
    implementation 'com.squareup.retrofit2:converter-gson:2.0.1'
    implementation 'com.squareup.retrofit2:retrofit:2.0.2'
    implementation 'com.squareup.retrofit2:adapter-rxjava:2.0.1'
    // Okhttp库
    implementation 'com.squareup.okhttp3:okhttp:3.14.1'

    // RxJava+RxAndroid
    implementation 'io.reactivex:rxjava:1.1.5'
    implementation 'io.reactivex:rxandroid:1.2.1'

    //这两个是 RxAppCompatActivity 里面的
    implementation 'com.trello:rxlifecycle:0.4.0'
    implementation 'com.trello:rxlifecycle-components:0.4.0'
    //解析器
    implementation 'com.google.code.gson:gson:2.8.6'



3.在Application的onCreate中添加以下

            //默认配置URL
            if (httpConfig == null) {
                httpConfig = new HttpConfig();
            }
            WSHttpClient.init(httpConfig);

解释:HttpConfig是一个继承RequestConfig的子类,onHttpBaseUrl()为必须实现的方式，下面看示例，
可配置：连接超时时间  IO写入超时时间 调用超时时间  单个连接读取 超时时间  日志拦截器 网络拦截器  缓存 CookieJava 这些常用配置
具体请看 RequestConfig这个类

    public class HttpConfig extends RequestConfig {
      public static String BASE_URL ="http://10.0.2.0:8088/api";
      @Override
      public String onHttpBaseUrl() {
        return BASE_URL;
      }
    }


