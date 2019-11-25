package com.example.wsapplication;

import com.wshttp.config.RequestConfig;

public class HttpConfig extends RequestConfig {

  public static final String BASE_URL ="http://118.24.65.174:8080/TaskPlatformWeb/servlet/";

  @Override
  public String onHttpBaseUrl() {
    return BASE_URL;
  }

}
