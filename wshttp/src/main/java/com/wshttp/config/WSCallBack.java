package com.wshttp.config;

public abstract class WSCallBack<T> {


    protected abstract void onSuccess(T t);

    public void onFail(String error){

    }




}
