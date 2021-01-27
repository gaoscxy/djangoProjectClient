package com.gaos.book.api;

import androidx.annotation.NonNull;

import com.gaos.book.base.BaseBean;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public abstract  class BaseObserver<T> implements Observer<BaseBean<T>> {
    @Override
    public final void onNext(@NonNull BaseBean<T> result) {
        int code = result.getCode();
        if (code != 200) {
            onFailure(new Throwable(), code,ResponseException.getErrMsg(code));//该异常可以汇报服务端
        } else {
            onSuccess(result.getData());
        }
    }

    @Override
    public void onError(@NonNull Throwable e) {
        ResponseException exception = new ResponseException(e);
        onFailure(e,exception.getStatusCode(),exception.getMessage());
    }

    @Override
    public void onComplete() {
    }

    @Override
    public void onSubscribe(@NonNull Disposable d) {
    }

    public abstract void onSuccess(T result);
    public abstract void onFailure(Throwable e,int errcode,String errormsg);
}
