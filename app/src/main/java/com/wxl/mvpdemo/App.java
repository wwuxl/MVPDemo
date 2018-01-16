package com.wxl.mvpdemo;

import android.app.Application;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;

/**
 * Created by wxl on 2018/1/15.
 */

public class App extends Application {

    private static Context mContext;
    private static Thread    mMainThread;
    private static long        mMainThreadId;
    private static Looper    mMainLooper;
    private static Handler mMainHandler;

    public static Context getContext() {
        return mContext;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        // 上下文
        mContext = getApplicationContext();
        // 主线程
        mMainThread = Thread.currentThread();
        // mMainThreadId = mMainThread.getId();
        mMainThreadId = android.os.Process.myTid();
        mMainLooper = getMainLooper();
        // 创建主线程的handler
        mMainHandler = new Handler();
    }

    public static Thread getMainThread()
    {
        return mMainThread;
    }
    public static long getMainThreadId()
    {
        return mMainThreadId;
    }
    public static Looper getMainThreadLooper()
    {
        return mMainLooper;
    }
    public static Handler getMainHandler()
    {
        return mMainHandler;
    }


}
