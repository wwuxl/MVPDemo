package com.wxl.mvpdemo.rx;

import com.wxl.mvpdemo.rx.subscribers.OnResponseListener;
import com.wxl.mvpdemo.rx.subscribers.ResponseSubscriber;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by wxl on 2018/1/15.
 */

public class RxUtils {


    public static <T> Disposable rx( Flowable<T> flowable, OnResponseListener<T> listener) {
        return flowable
                .onBackpressureDrop()  //加上背压策略
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new ResponseSubscriber<T>(listener));
    }


    public static<T> void  ioThreadOnUiThread(ObservableOnSubscribe<T> subscribe, Consumer<T> consumer){
        Observable.create(subscribe)
                    .subscribeOn(Schedulers.io())
                    .unsubscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(consumer);

    }


}
