package com.wshttp.config.m;

import com.wshttp.base.WSFragmentV4;

import rx.Observable;

public class FragmentV4Request extends BaseRequest {
    @Override
    public <T> Observable.Transformer<T, T> getLifecycle(Object context) {

        if (context instanceof WSFragmentV4){
            return  ((WSFragmentV4) context).bindToLifecycle();
        }else{
            return getBaseRequest().getLifecycle(context);
        }
    }
}
