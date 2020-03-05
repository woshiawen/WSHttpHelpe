package com.wshttp.config.m;

import com.trello.rxlifecycle3.LifecycleTransformer;
import com.wshttp.base.WsFragmentApp;

public class FragmentAppRequest extends BaseRequest {
    @Override
    public <T> LifecycleTransformer<Object> getLifecycle(Object context) {

        if (context instanceof WsFragmentApp){
            return  ((WsFragmentApp) context).bindToLifecycle();
        }else{
            throw new NullPointerException("请添加Lifecycle对象");
        }
    }
}
