package com.example.wsapplication;

import android.app.Application;

import com.wshttp.config.WSHttpClient;

public class BaseApplication extends Application {

  private HttpConfig httpConfig;

  @Override
  public void onCreate() {
    super.onCreate();
    if (httpConfig == null) {
      httpConfig = new HttpConfig();
    }
    WSHttpClient.init(httpConfig);
  }
}
