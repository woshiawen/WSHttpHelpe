package com.wshttp.config.m;

import rx.Observable;

public abstract class BaseRequest {

    private BaseRequest baseRequest;

    public BaseRequest getBaseRequest() {
        return baseRequest;
    }

    public void setBaseRequest(BaseRequest baseRequest) {
        this.baseRequest = baseRequest;
    }

    public abstract <T> Observable.Transformer<T, T> getLifecycle(Object context);

}
