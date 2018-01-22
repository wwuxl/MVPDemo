package com.wxl.mvpdemo.ui.page;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;

import com.wxl.mvpdemo.R;
import com.wxl.mvpdemo.databinding.ViewEmptyPageBinding;
import com.wxl.mvpdemo.databinding.ViewErrorPageBinding;
import com.wxl.mvpdemo.databinding.ViewLoadingPageBinding;

/**
 * Created by wxl on 2018/1/17.
 */

public abstract class LoadPageView extends FrameLayout{

    //正在加载
    public static final int STATUS_LOADING=0x11;
    //网络错误
    public static final int STATUS_ERROR=0x12;
    //数据为空
    public static final int STATUS_EMPTY=0x13;
    //成功 有数据
    public static final int STATUS_SUCCEED=0x14;
    //当前界面的显示状态
    public int current_state = STATUS_LOADING;

    private ViewLoadingPageBinding mLoadBinding;
    private ViewErrorPageBinding mErrorBinding;
    private ViewEmptyPageBinding mEmptyBinding;
    private ViewDataBinding mViewDataBinding;

    public LoadPageView(@NonNull Context context) {
        this(context,null);
    }

    public LoadPageView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public LoadPageView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();

    }
    private void initView() {
        mLoadBinding = DataBindingUtil.inflate(LayoutInflater.from(getContext()), R.layout.view_loading_page,null,false);
        mErrorBinding = DataBindingUtil.inflate(LayoutInflater.from(getContext()), R.layout.view_error_page,null,false);
        mEmptyBinding = DataBindingUtil.inflate(LayoutInflater.from(getContext()), R.layout.view_empty_page,null,false);

        mViewDataBinding = initContentView();
        addView(mLoadBinding.getRoot());
        addView(mErrorBinding.getRoot());
        addView(mEmptyBinding.getRoot());
        if(mViewDataBinding!=null){
            addView(mViewDataBinding.getRoot());
        }
        showPage();

        onLoadData();
    }

    public void showStatusPage(int status){
        current_state=status;
       showPage();
    }



    /**
     * 显示界面
     */
    private void showPage() {
        //显示正在加载的loadingView
        mLoadBinding.getRoot().setVisibility(current_state==STATUS_LOADING?VISIBLE:View.GONE);
        //显示错误界面
        mErrorBinding.getRoot().setVisibility(current_state==STATUS_ERROR?VISIBLE:GONE);
        //显示空界面
        mEmptyBinding.getRoot().setVisibility(current_state==STATUS_EMPTY?VISIBLE:GONE);
        //显示成功界面
        mViewDataBinding.getRoot().setVisibility(current_state==STATUS_SUCCEED?VISIBLE:GONE);

    }


    public abstract void onLoadData();

    public abstract ViewDataBinding initContentView();
}
