package com.wshttp.config.m;

import com.trello.rxlifecycle3.LifecycleTransformer;
import com.wshttp.base.WSActivity;


/**
 * 责任链模式
 */
public class ActivityRequest extends BaseRequest {


    @Override
    public <T> LifecycleTransformer<Object> getLifecycle(Object context) {
        if (context instanceof WSActivity){
            return  ((WSActivity) context).bindToLifecycle();
        }else{
            return getBaseRequest().getLifecycle(context);
        }
    }
}
