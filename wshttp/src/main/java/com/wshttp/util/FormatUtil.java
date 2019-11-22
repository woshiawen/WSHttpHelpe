package com.wshttp.util;


import com.google.gson.Gson;

public class FormatUtil {

    private static FormatUtil formatUtil = new FormatUtil();
    private static final String TAG = "FormatUtil";


    public static FormatUtil getFormatUtil() {
        return formatUtil;
    }


    public <T> T formatJson(Object json, Class<T> t) {
        Gson gson = new Gson();
       String srtJson = gson.toJson(json);
        T result = gson.fromJson(srtJson, t);
        return result;
    }


}
