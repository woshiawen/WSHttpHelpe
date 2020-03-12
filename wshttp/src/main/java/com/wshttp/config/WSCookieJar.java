package com.wshttp.config;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.Cookie;
import okhttp3.HttpUrl;

public class WSCookieJar  implements okhttp3.CookieJar {

    private final Map<String, List<Cookie>> cookiesMap = new HashMap<String, List<Cookie>>();


    @Override
    public void saveFromResponse(HttpUrl url, List<Cookie> cookies) {
        String host = url.host();
        List<Cookie> cookiesList = cookiesMap.get(host);
        if (cookiesList != null){
            cookiesMap.remove(host);
        }
        //再重新添加
        cookiesMap.put(host, cookies);

    }

    @Override
    public List<Cookie> loadForRequest(HttpUrl url) {
        List<Cookie> cookiesList = cookiesMap.get(url.host());
        return cookiesList != null ? cookiesList : new ArrayList<Cookie>();
    }
}
