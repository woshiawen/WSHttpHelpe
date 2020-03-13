package com.wshttp.config;


import android.util.ArrayMap;
import android.util.Log;

import androidx.annotation.NonNull;

import com.trello.rxlifecycle3.LifecycleTransformer;
import com.wshttp.config.m.ActivityRequest;
import com.wshttp.config.m.FragmentAppRequest;
import com.wshttp.config.m.FragmentV4Request;
import com.wshttp.util.FormatUtil;

import java.io.IOException;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import okhttp3.MultipartBody;
import okhttp3.ResponseBody;
import rx.functions.Action1;


/**
 * 连接的帮助类
 */
public class WSClientHelper {
    public static final String TAG = "WSClientHelper";
    private static ActivityRequest activityRequest;
    private static FragmentV4Request fragmentV4Request;
    private static FragmentAppRequest fragmentAppRequest;


    /**
     * 责任链模式实现
     * @param activity
     * @return
     */
    public static LifecycleTransformer<Object> getLifecycle(Object activity) {
        if (activityRequest == null || fragmentV4Request == null || fragmentAppRequest == null) {
            activityRequest = new ActivityRequest();
            fragmentV4Request = new FragmentV4Request();
            fragmentAppRequest = new FragmentAppRequest();
            activityRequest.setBaseRequest(fragmentV4Request);
            fragmentV4Request.setBaseRequest(fragmentAppRequest);
            //TODO:此处需要添加扩展类 让其可以自定义此生命周期
        }
        LifecycleTransformer<Object> lifecycle = activityRequest.getLifecycle(activity);
        return lifecycle;
    }

    public static <T> void get(@NonNull String url, @NonNull ArrayMap params, @NonNull Class<T> t, Object activity, @NonNull WSCallBack callBack) {
        WSHttpClient
                .getHttpInstance()
                .getWsHttpApi()
                .get(url, params)
                .compose(getLifecycle(activity))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer() {
                    @Override
                    public void accept(Object o) throws Exception {
                        try {
                            T temp = FormatUtil.getFormatUtil().formatJson(o, t);
                            callBack.onSuccess(temp);
                        } catch (Exception e) {
                            callBack.onFail(e.getLocalizedMessage());
                        }
                    }

                });


    }


    public static <T> void post(@NonNull String url, @NonNull ArrayMap params, @NonNull Class<T> t, Object activity, @NonNull WSCallBack callBack) {
        WSHttpClient.getHttpInstance().getWsHttpApi()
                .post(url, params)
                .compose(getLifecycle(activity))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer() {
                    @Override
                    public void accept(Object json) throws Exception {
                        try {
                            T temp = FormatUtil.getFormatUtil().formatJson(json, t);
                            callBack.onSuccess(temp);

                        } catch (Exception e) {
                            callBack.onFail(e.getLocalizedMessage());
                        }
                    }
                });
    }


    //文件上传和下载
    public static <T> void uploadFileWithMultipartBody(@NonNull String url, MultipartBody body, Object activity, @NonNull WSCallBack callBack, Class<T> t) {
        WSHttpClient.getHttpInstance().getWsHttpApi()
                .uploadFileWithMultipartBody(url, body)
                .compose(getLifecycle(activity))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Object>() {
                    @Override
                    public void accept(Object json) throws Exception {
                        try {
                            T temp = FormatUtil.getFormatUtil().formatJson(json, t);
                            callBack.onSuccess(temp);

                        } catch (Exception e) {
                            callBack.onFail(e.getLocalizedMessage());
                        }

                    }
                }

                        );
    }

    /**
     * 单文件或者多文件上传
     *
     * @param url
     * @param parts
     * @param activity
     * @param callBack
     * @param t
     * @param <T>
     */
    public static <T> void uploadFileWithParts(@NonNull String url, List<MultipartBody.Part> parts, Object activity, @NonNull WSCallBack callBack, Class<T> t) {
        WSHttpClient.getHttpInstance().getWsHttpApi()
                .uploadFileWithParts(url, parts)
                .compose(getLifecycle(activity))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Object>() {
                    @Override
                    public void accept(Object json) throws Exception {
                        try {
                            T temp = FormatUtil.getFormatUtil().formatJson(json, t);
                            callBack.onSuccess(temp);

                        } catch (Exception e) {
                            callBack.onFail(e.getLocalizedMessage());
                        }
                    }
                });
    }


    public static <T> void downLoadFile(@NonNull String url, Object activity, Class<T> t, @NonNull WSCallBack callBack) {

        WSHttpClient.getHttpInstance().getWsHttpApi()
                .downLoadFile(url)
                .compose(getLifecycle(activity))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Object>() {
                    @Override
                    public void accept(Object o) throws Exception {
                        if (o instanceof ResponseBody) {
                            try {
                                String string = ((ResponseBody) o).string();
                                callBack.onSuccess(string);
                            } catch (IOException e) {
                                e.printStackTrace();
                                callBack.onFail(e.getLocalizedMessage());
                            } finally {
                                ((ResponseBody) o).close();
                            }
                        }
                    }
                });
    }


}
