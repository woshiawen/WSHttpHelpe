package com.example.wsapplication;


import android.os.Bundle;
import android.os.Environment;
import android.util.ArrayMap;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;
import com.wshttp.base.WSActivity;
import com.wshttp.config.DownLoadEntity;
import com.wshttp.config.WSCallBack;
import com.wshttp.config.WSClientHelper;

import java.io.File;
import java.io.IOException;

public class MainActivity extends WSActivity {

  private static final String TAG = "MainActivity";
  private EditText commandET;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    commandET = (EditText) findViewById(R.id.commandET);

    ((TextView) findViewById(R.id.testBtn)).setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View click) {
        onGet();
      }
    });

    findViewById(R.id.downLoadBtn).setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        onDownLoad();
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
            commandET.append("成功:   " + new Gson().toJson(o) + "\n------------------------------\n\n");
//            Log.e(TAG, "成功:   " + new Gson().toJson(o) + "\n\n\n" );

          }

          @Override
          public void onFail(String error) {
            Log.e(TAG, "error:   " + error + "\n\n\n");
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
            commandET.append("成功:   " + new Gson().toJson(o) + "\n------------------------------\n\n");
          }

          @Override
          public void onFail(String error) {
            commandET.append("失败:   " + error + "\n------------------------------\n\n");
          }
        }
    );

  }

  private void onDownLoad() {
    String path = Environment.getExternalStorageDirectory().getAbsolutePath();
    File downLoadFile = new File(path, "newApp.apk");
    if (!downLoadFile.getParentFile().exists()) {
      downLoadFile.getParentFile().mkdirs();
      try {
        downLoadFile.createNewFile();
      } catch (IOException e) {
        commandET.setText("成功:  IOException  +"+e.getMessage()+"  \n------------------------------\n\n");
        e.printStackTrace();
      }

    }
    WSClientHelper.downLoadFile(
        "https://imtt.dd.qq.com/16891/apk/DFF2B2F3C5103797B46AB024692DBB5B.apk?fsname=com.sdu.didi.psnger_5.4.8_742.apk&csr=1bbd",
        downLoadFile,
        this,
        new WSCallBack<DownLoadEntity>() {
          @Override
          protected void onSuccess(DownLoadEntity s) {
            commandET.setText("成功:   " + s.getPro() + "/100 \n------------------------------\n\n");
          }

          @Override
          public void onFail(String error) {
            runOnUiThread(new Runnable() {
              @Override
              public void run() {
                commandET.setText("失败:   " + error + "\n------------------------------\n\n");
              }
            });
          }
        });
  }


  private void onUplaod() {


  }

}
