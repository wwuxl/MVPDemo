package com.wxl.mvpdemo.ui;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.wxl.mvpdemo.R;
import com.wxl.mvpdemo.databinding.ActivityBaseBinding;
import com.wxl.mvpdemo.databinding.ViewEmptyPageBinding;
import com.wxl.mvpdemo.databinding.ViewErrorPageBinding;
import com.wxl.mvpdemo.databinding.ViewLoadingPageBinding;

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
        mBaseBinding.fltContent.addView(initView());

    }

    protected abstract View initView();


    public abstract String initTitle();



}
