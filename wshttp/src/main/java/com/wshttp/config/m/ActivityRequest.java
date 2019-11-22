package com.wshttp.config.m;

import com.wshttp.base.WSActivity;

import rx.Observable;

/**
 * 责任链模式
 */
public class ActivityRequest extends BaseRequest {


    @Override
    public <T> Observable.Transformer<T, T> getLifecycle(Object context) {
        if (context instanceof WSActivity){
            return  ((WSActivity) context).bindToLifecycle();
        }else{
            return getBaseRequest().getLifecycle(context);
        }
    }
}
