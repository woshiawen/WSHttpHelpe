package com.wshttp.config.m;

import com.wshttp.base.WsFragmentApp;

import rx.Observable;

public class FragmentAppRequest extends BaseRequest {
    @Override
    public <T> Observable.Transformer<T, T> getLifecycle(Object context) {

        if (context instanceof WsFragmentApp){
            return  ((WsFragmentApp) context).bindToLifecycle();
        }else{
            throw new NullPointerException("请添加Lifecycle对象");
        }
    }
}
