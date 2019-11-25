package com.wshttp.config;

import com.parkingwang.okhttp3.LogInterceptor.LogInterceptor;

import java.io.IOException;
import java.util.List;

import okhttp3.Cache;
import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Response;

/**
 * 请求配置参数
 */
public abstract   class RequestConfig {

    //连接超时时间
    public static final long DEFAULT_CONNECT_TIME_OUT = 1000 * 5;
    //单个IO写入超时时间
    public static final long DEFAULT_WRITE_TIME_OUT = 1000 * 10;
    //调用超时时间
    public static final long DEFAULT_CALL_TIME_OUT = 1000 * 10;

    //单个连接读取超时时间
    public static final long DEFAULT_READ_TIME_OUT = 1000 * 10;
    //默认的BaseUrl
    public static  String DEFAULT_BASE_URL = "";

    public RequestConfig(String url) {
        DEFAULT_BASE_URL = url;
    }

    public RequestConfig() {
    }

    public  long onConnectTimeoutNum() {
        return DEFAULT_CONNECT_TIME_OUT;
    }


    public  long onWriteTimeOutNum() {
        return DEFAULT_WRITE_TIME_OUT;
    }


    public  long onReadTimeOutNum() {
        return DEFAULT_READ_TIME_OUT;
    }


    public  long onCallTimeOutNum() {
        return DEFAULT_CALL_TIME_OUT;
    }

    /**
     * 拦截器
     *
     * @return
     */
    public  Interceptor onInterceptor() {
        return new LogInterceptor();
    }


    public  Interceptor onNetWorkInterceptor() {
        return new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                return null;
            }
        };
    }

    public  Cache onCache() {
        return null;
    }


    public  CookieJar onCookieJar() {

        return new CookieJar() {
            @Override
            public void saveFromResponse(HttpUrl url, List<Cookie> cookies) {

            }

            @Override
            public List<Cookie> loadForRequest(HttpUrl url) {
                return null;
            }
        };
    }


    public abstract String onHttpBaseUrl();

}
