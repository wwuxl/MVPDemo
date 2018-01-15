package com.wxl.mvpdemo.rx;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.PublishSubject;
import io.reactivex.subjects.Subject;

/**
 * RxBus
 * Created by YoKeyword on 2015/6/17.
 * ToDo:http://nerds.weddingpartyapp.com/tech/2014/12/24/implementing-an-event-bus-with-rxjava-rxbus/
 */
public class RxBus {

    private static volatile RxBus defaultInstance;

    // 主题
    private final Subject bus;

    // PublishSubject只会把在订阅发生的时间点之后来自原始Observable的数据发射给观察者
    private RxBus() {
        bus =  PublishSubject.create();
    }

    // 单例RxBus
    public static RxBus getInstance() {
        if (defaultInstance == null) {
            synchronized (RxBus.class) {
                if (defaultInstance == null) {
                    defaultInstance = new RxBus();
                }
            }
        }
        return defaultInstance;
    }

    // 提供了一个新的事件
    public void post(Object o) {
        bus.onNext(o);
    }


    public <T> Disposable register(final Class<T> eventClass, Consumer<T> onNext) {
        return bus
                .filter(new Predicate() {
                    @Override
                    public boolean test(Object o) throws Exception {
                        return o.getClass().equals(eventClass);
                    }
                })
                .map(new Function<Object, T>() {
                    @Override
                    public T apply(Object o) throws Exception {
                        return (T) o;
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(onNext);
    }
}