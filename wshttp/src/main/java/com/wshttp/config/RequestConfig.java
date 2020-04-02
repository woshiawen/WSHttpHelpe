package com.wshttp.config;

import com.parkingwang.okhttp3.LogInterceptor.LogInterceptor;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.Cache;
import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.Headers;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 请求配置参数
 */
public abstract class RequestConfig {

    //连接超时时间
    public static final long DEFAULT_CONNECT_TIME_OUT = 1000 * 5;
    //单个IO写入超时时间
    public static final long DEFAULT_WRITE_TIME_OUT = 1000 * 60;
    //调用超时时间
    public static final long DEFAULT_CALL_TIME_OUT = 1000 * 60;

    //单个连接读取超时时间
    public static final long DEFAULT_READ_TIME_OUT = 1000 * 60;
    //默认的BaseUrl
    public static String DEFAULT_BASE_URL = "";

    public RequestConfig(String url) {
        DEFAULT_BASE_URL = url;
    }

    public RequestConfig() {
    }

    public long onConnectTimeoutNum() {
        return DEFAULT_CONNECT_TIME_OUT;
    }


    public long onWriteTimeOutNum() {
        return DEFAULT_WRITE_TIME_OUT;
    }


    public long onReadTimeOutNum() {
        return DEFAULT_READ_TIME_OUT;
    }


    public long onCallTimeOutNum() {
        return DEFAULT_CALL_TIME_OUT;
    }

    /**
     * 拦截器
     *
     * @return
     */
    public Interceptor onInterceptor() {
        return new LogInterceptor();
    }


    public Interceptor onNetWorkInterceptor() {
        return new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                return null;
            }
        };
    }

    public Cache onCache() {
        return null;
    }


    /**
     * 重写此方法定义CookieJar
     * @return
     */
    public CookieJar onCookieJar() {
        return new WSCookieJar();
    }

    /**
     * 添加请求头信息的拦截器
     * @return
     */
    public  Interceptor onTokenInterceptor(){
        return new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request original = chain.request();
                Request request = original.newBuilder()
                        .headers(getHeaders())
                        .method(original.method(), original.body())
                        .build();
                return chain.proceed(request);
            }
        };
    }


    /**
     * 重写此方法自定义请求头
     *
     * @return
     */
    public Headers getHeaders(){
        return new Headers.Builder().add("xToken","xxxxxxxx").build();
    }


    public abstract String onHttpBaseUrl();


}
