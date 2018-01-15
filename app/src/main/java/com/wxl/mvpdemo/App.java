package com.wxl.mvpdemo;

import android.app.Application;

/**
 * Created by wxl on 2018/1/15.
 */

public class App extends Application {

    public static App mContext;

    public static App getContext() {
        return mContext;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
    }


}
