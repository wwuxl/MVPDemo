package com.wxl.mvpdemo.rx.subscribers;

import android.util.Log;
import android.widget.Toast;

import com.wxl.mvpdemo.App;
import com.wxl.mvpdemo.net.IFResult;

import io.reactivex.subscribers.ResourceSubscriber;

/**
 * Created by wxl on 2018/1/15.
 */

public class ResponseSubscriber<T> extends ResourceSubscriber<T> {

    private OnResponseListener<T> mListener;
    //IResult,获取数据成功
    public static final int errorCode_success = 0;//成功
    public static final int errorCode_logout = 1;//未登录

    public ResponseSubscriber(OnResponseListener<T> listener){
        mListener = listener;
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (mListener!=null) {
            //开始请求网络
            mListener.onLoading();
        }
    }

    @Override
    public void onNext(T t) {
        //请求成功
        if (mListener!=null) {
            //根据状态判断，执行相应的操作；
            if (t!=null) {
                IFResult result= (IFResult) t;

                //未登录
                if(result.errorCode()==errorCode_logout){
                    //跳登录界面

                    return;
                }

                if (result.errorCode()!=errorCode_success) {
                    Toast.makeText(App.getContext(), result.errorMsg(), Toast.LENGTH_SHORT).show();
                    //请求不成功
                    return;
                }
                Log.e("===","result == "+((IFResult) t).result());
                //请求成功
                mListener.onNext(t);

            }
        }
    }

    @Override
    public void onError(Throwable t) {
        if (mListener!=null) {
            //网络错误
            mListener.onError(t);
        }

    }

    @Override
    public void onComplete() {

    }
}
