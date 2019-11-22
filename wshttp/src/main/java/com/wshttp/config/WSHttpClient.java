package com.wshttp.config;

import java.net.Proxy;
import java.util.concurrent.TimeUnit;

import okhttp3.CacheControl;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.schedulers.Schedulers;



public class WSHttpClient {

    public static WSHttpClient wsHttpClient;
    public static WSHttpApi wsHttpApi;
    public static RequestConfig requestConfig;


    public static final String TAG ="BaseApplication";

    private WSHttpClient() {
        createClient();
    }


    /**
     * 初始化配置类
     * 建议在Application的onCreate中初始化
     *
     * 可自定义实现RequestConfig类 用以配置自己的配置项 例如
     * WSHttpClient.init(new RequestConfig(String baseUrl))
     *
     * @param requestConfigs
     */
    public static void init(RequestConfig requestConfigs) {
        if (requestConfig == null) {
           requestConfig = requestConfigs;
        }
    }


    public static WSHttpClient getHttpInstance() {
        if (wsHttpClient == null) {
            synchronized (WSHttpClient.class) {
                if (wsHttpClient == null) {
                    wsHttpClient = new WSHttpClient();
                }
            }
        }
        return wsHttpClient;
    }



    /**
     * 创建连接
     */
    public static void createClient() {

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(requestConfig.onConnectTimeoutNum(), TimeUnit.MILLISECONDS)
//                .writeTimeout(requestConfig.onWriteTimeOutNum(), TimeUnit.MILLISECONDS)
//                .callTimeout(requestConfig.onCallTimeOutNum(), TimeUnit.MILLISECONDS)    //调用超时
//                .readTimeout(requestConfig.onReadTimeOutNum(), TimeUnit.MILLISECONDS)
                .addInterceptor(requestConfig.onInterceptor())
//                .addNetworkInterceptor(requestConfig.onNetWorkInterceptor())
//                .cookieJar(requestConfig.onCookieJar())
                .proxy(Proxy.NO_PROXY)
//                .cache(requestConfig.onCache())
                .build();


        CacheControl.Builder cacheControl = new CacheControl.Builder();
        //设置缓存最长时限,如果超过此时限那么将重新从 网络 拉取数据
        cacheControl.maxAge(2,TimeUnit.DAYS)
                //Response数据缓存的最长时间，超出此时间将不会使用；   如果未设置 则不使用缓存数据
                .maxStale(30,TimeUnit.MINUTES);



        // 其实Request就是配置整个请求体的各个参数  也是配置这个请求中的各个参数
//        Request request = new Request.Builder()
//                .addHeader("Content-Type", "formatData")
//                .url("")
//                .cacheControl(cacheControl.build())
//                //四种请求方式
////                .get()
////                .post()
////                .patch()
////                .put()
//                .method("get", null)
//                .delete()
//                //添加和移除一个标记
////                .tag()
//                .build();
//
//
//
//        //最后把Reuqest  +
//        okHttpClient.newCall(request).enqueue(new Callback() {
//            @Override
//            public void onFailure(Call call, IOException e) {
//                //请求失败
//            }
//
//            @Override
//            public void onResponse(Call call, Response response) throws IOException {
//                //请求成功
//
//
//
//
//                String json = response.body().string();
//                Map<String, List<String>> headersMap = response.headers().toMultimap();
//
//                for (String key : headersMap.keySet()) {
//                    //获取所有请求头，Cookies在此列中 可在此进行持久化处理
//
//                }
//
//
//
//
//            }
//        });
//

//        Retrofit retrofit = new Retrofit.Builder()
//                .client(okHttpClient)
//                .baseUrl(requestConfig.onHttpBaseUrl())
//                .addConverterFactory(GsonConverterFactory.create())
//                .addCallAdapterFactory(RxJavaCallAdapterFactory.createWithScheduler(Schedulers.io()))
//                .build();



        String baseUrl = requestConfig.onHttpBaseUrl();
        Retrofit retrofit = new Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.createWithScheduler(Schedulers.io()))
                .build();
        wsHttpApi = retrofit.create(WSHttpApi.class);
    }


    public WSHttpApi getWsHttpApi(){
        return wsHttpApi;
    }


}
