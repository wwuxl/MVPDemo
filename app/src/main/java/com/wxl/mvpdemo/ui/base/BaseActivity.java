package com.wxl.mvpdemo.ui.base;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;

import com.wxl.mvpdemo.R;
import com.wxl.mvpdemo.databinding.ActivityBaseBinding;
import com.wxl.mvpdemo.databinding.ViewEmptyPageBinding;
import com.wxl.mvpdemo.databinding.ViewErrorPageBinding;
import com.wxl.mvpdemo.databinding.ViewLoadingPageBinding;
import com.wxl.mvpdemo.net.Response;
import com.wxl.mvpdemo.rx.RxUtils;
import com.wxl.mvpdemo.rx.subscribers.OnResponseListener;

import io.reactivex.Flowable;

public abstract class BaseActivity<T> extends AppCompatActivity {

    public ActivityBaseBinding mBaseBinding;
    public static final int STATUS_LOADING=0x11;
    public static final int STATUS_ERROR=0x12;
    public static final int STATUS_EMPTY=0x13;
    public static final int STATUS_SUCCEED=0x14;
    private ViewLoadingPageBinding mLoadBinding;
    private ViewErrorPageBinding mErrorBinding;
    private ViewEmptyPageBinding mEmptyBinding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBaseBinding = DataBindingUtil.setContentView(this, R.layout.activity_base);
        mBaseBinding.tvTitle.setText(initTitle());
        initPageView();


    }

    private void initPageView() {
        mLoadBinding = DataBindingUtil.inflate(LayoutInflater.from(this), R.layout.view_loading_page,null,false);
        mErrorBinding = DataBindingUtil.inflate(LayoutInflater.from(this), R.layout.view_error_page,null,false);
        mEmptyBinding = DataBindingUtil.inflate(LayoutInflater.from(this), R.layout.view_empty_page,null,false);


        //mBaseBinding.fltContent.removeAllViews();
        RxUtils.rx(loadData(), new OnResponseListener<Response<T>>() {
            @Override
            public void onLoading() {
                mBaseBinding.fltContent.addView(mLoadBinding.getRoot());
            }

            @Override
            public void onNext(Response<T> userResponse) {
                mBaseBinding.fltContent.addView(mEmptyBinding.getRoot());

                View view = initContentView();
                if(view!=null){
                    mBaseBinding.fltContent.addView(view);
                }
            }

            @Override
            public void onError(Throwable t) {
                mBaseBinding.fltContent.addView(mErrorBinding.getRoot());
            }

            @Override
            public void onComplete() {

            }
        });


    }

    public abstract Flowable<Response<T>> loadData();

    public abstract String initTitle();

    public abstract View initContentView();

}
