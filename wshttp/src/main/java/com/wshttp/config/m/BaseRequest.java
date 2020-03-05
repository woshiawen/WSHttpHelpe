package com.wshttp.config.m;

import com.trello.rxlifecycle3.LifecycleTransformer;

public abstract class BaseRequest {

    private BaseRequest baseRequest;

    public BaseRequest getBaseRequest() {
        return baseRequest;
    }

    public void setBaseRequest(BaseRequest baseRequest) {
        this.baseRequest = baseRequest;
    }

    public abstract <T> LifecycleTransformer<Object> getLifecycle(Object context);

}
