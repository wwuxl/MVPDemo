package com.wxl.mvpdemo.ui.page;

import android.content.Context;
import android.databinding.DataBindingUtil;
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

public class LoadPageView extends FrameLayout{
    //默认状态
    public static final int STATUS_NONE = 0x10;
    //正在加载
    public static final int STATUS_LOADING=0x11;
    //网络错误
    public static final int STATUS_ERROR=0x12;
    //数据为空
    public static final int STATUS_EMPTY=0x13;
    //成功 有数据
    public static final int STATUS_SUCCEED=0x14;
    //当前界面的显示状态
    public int current_state = STATUS_NONE;

    private ViewLoadingPageBinding mLoadBinding;
    private ViewErrorPageBinding mErrorBinding;
    private ViewEmptyPageBinding mEmptyBinding;

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

        addView(mLoadBinding.getRoot());
        addView(mErrorBinding.getRoot());
        addView(mEmptyBinding.getRoot());

        showPage();
    }

    /**
     * 显示界面
     */
    private void showPage() {
        //显示正在加载的loadingView
        if(current_state == STATUS_NONE || current_state == STATUS_LOADING){
            mLoadBinding.getRoot().setVisibility(View.VISIBLE);
        }else{
            mLoadBinding.getRoot().setVisibility(View.GONE);
        }
        //显示错误界面
        if(current_state==STATUS_ERROR){
            mErrorBinding.getRoot().setVisibility(VISIBLE);
        }else{
            mErrorBinding.getRoot().setVisibility(GONE);
        }
        //显示空界面
        if(current_state==STATUS_EMPTY){
            mEmptyBinding.getRoot().setVisibility(VISIBLE);
        }else{
            mEmptyBinding.getRoot().setVisibility(GONE);
        }

    }




}
