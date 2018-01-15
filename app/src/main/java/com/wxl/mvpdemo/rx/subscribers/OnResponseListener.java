package com.wxl.mvpdemo.rx.subscribers;

/**
 * Created by wxl on 2018/1/15.
 */

public interface OnResponseListener<T> {
    void onLoading();
    void onNext(T t);
    void onError(Throwable t);
    void onComplete();

}
