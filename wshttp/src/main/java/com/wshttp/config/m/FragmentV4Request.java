package com.wshttp.config.m;

import com.trello.rxlifecycle3.LifecycleTransformer;
import com.wshttp.base.WSFragmentV4;

public class FragmentV4Request extends BaseRequest {
    @Override
    public <T> LifecycleTransformer<Object> getLifecycle(Object context) {

        if (context instanceof WSFragmentV4){
            return  ((WSFragmentV4) context).bindToLifecycle();
        }else{
            return getBaseRequest().getLifecycle(context);
        }
    }
}
