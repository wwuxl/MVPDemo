package com.wxl.mvpdemo.ui;

import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.view.View;

import com.wxl.mvpdemo.ui.page.LoadPageView;

import io.reactivex.disposables.CompositeDisposable;

public abstract class NetActivity extends BaseActivity {

    //rxjava访问的Subscription集合
    public CompositeDisposable mDisposables = new CompositeDisposable();
    public LoadPageView mPageView;
    public int current_status;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initEvent();
    }

    protected abstract void initEvent();

    @Override
    protected View initView() {
        mPageView = new LoadPageView(this) {
            @Override
            public void onLoadData() {
               onSubLoadData();
            }

            @Override
            public ViewDataBinding initContentView() {
                return initSubContentView();
            }
        };

        return mPageView;
    }
    //加载数据
    protected abstract void onSubLoadData();
    //网络请求成功的显示界面
    public abstract ViewDataBinding initSubContentView();

}
