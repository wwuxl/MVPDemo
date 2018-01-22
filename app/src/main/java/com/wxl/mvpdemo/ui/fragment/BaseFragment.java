package com.wxl.mvpdemo.ui.fragment;

import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wxl.mvpdemo.ui.page.LoadPageView;

/**
 * Created by wxl on 2018/1/22.
 */

public abstract class BaseFragment extends Fragment {

    public LoadPageView mPageView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mPageView = new LoadPageView(getContext()) {
            @Override
            public void onLoadData() {
                onSubLoadData();
            }

            @Override
            public ViewDataBinding initContentView() {
                return onCreateSuccessedView();
            }
        };

        return mPageView;
    }

    public abstract void onSubLoadData();

    public abstract ViewDataBinding onCreateSuccessedView();
}
