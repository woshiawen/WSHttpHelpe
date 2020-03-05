package com.example.wsapplication;

import com.wshttp.config.RequestConfig;

public class HttpConfig extends RequestConfig {

  public static final String BASE_URL ="http://180.76.144.228:8080/QuanBaoLM/";

  @Override
  public String onHttpBaseUrl() {
    return BASE_URL;
  }

}
