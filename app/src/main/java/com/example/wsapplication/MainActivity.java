package com.example.wsapplication;


import android.os.Bundle;
import android.util.ArrayMap;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.gson.Gson;
import com.wshttp.base.WSActivity;
import com.wshttp.config.WSCallBack;
import com.wshttp.config.WSClientHelper;

public class MainActivity extends WSActivity {

  private static final String TAG = "MainActivity";

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    ((TextView) findViewById(R.id.testBtn)).setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View click) {
        onGet();
      }
    });
  }


  private void onGet() {
    ArrayMap params = new ArrayMap();
    params.put("version", "1.0");
    WSClientHelper.get(
        "GetBannerList",
        params,
        BannerEntity.class,
        this,
        new WSCallBack<BannerEntity>() {
          @Override
          protected void onSuccess(BannerEntity o) {
            // commandET.append("成功:   " + new Gson().toJson(o) + "\n\n\n");
            Log.e(TAG, "成功:   " + new Gson().toJson(o) + "\n\n\n" );

          }

          @Override
          public void onFail(String error) {
            Log.e(TAG, "error:   " + error + "\n\n\n" );
          }
        }
    );


  }

  private void onPost() {
    ArrayMap params = new ArrayMap();
    params.put("version", "1.0");

    WSClientHelper.post(
        "GetGulidBanner",
        params,
        BannerEntity.class,
        this,
        new WSCallBack<BannerEntity>() {
          @Override
          protected void onSuccess(BannerEntity o) {
            // commandET.append("成功:   " + new Gson().toJson(o) + "\n\n\n");
          }

          @Override
          public void onFail(String error) {
            // commandET.append("失败:   " + error + "\n");
          }
        }
    );

  }

  private void onDownLoad() {
    WSClientHelper.downLoadFile(
        "http://118.24.65.174:8080/wendesong.doc",
        this,
        String.class,
        new WSCallBack<String>() {
          @Override
          protected void onSuccess(String s) {
            //commandET.append("成功:   " + s + "\n\n\n");
          }

          @Override
          public void onFail(String error) {
            // commandET.append("失败:   " + error + "\n");
          }
        });


  }


  private void onUplaod() {


  }

}
