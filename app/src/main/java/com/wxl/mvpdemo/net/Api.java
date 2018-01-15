package com.wxl.mvpdemo.net;

import com.facebook.stetho.okhttp3.StethoInterceptor;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.wxl.mvpdemo.BuildConfig;
import com.wxl.mvpdemo.constant.Constants;
import com.wxl.mvpdemo.model.User;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by wxl on 2018/1/15.
 */

public class Api {

    private static BaseService mService;


    public static BaseService getmService(){
        if(mService==null){
            synchronized (Api.class){
                if(mService==null){
                    //OkHttp拦截器,用来添加头部信息
                    Interceptor mTokenInterceptor = new Interceptor() {
                        @Override
                        public okhttp3.Response intercept(Chain chain) throws IOException {
                            //获取保存的token
                            //添加到头部
                            User user =new User();

                            if (user == null) {
                                Request newRequest = chain.request()
                                        .newBuilder()
                                        .build();
                                return chain.proceed(newRequest);
                            }
                            //添加头部信息
                            Request newRequest = chain.request()
                                    .newBuilder()
                                    .header(Constants.TOKEN, user.token)
                                    .header(Constants.UID,12+"")
                                    .build();
                            return chain.proceed(newRequest);
                        }
                    };

                    //OkHttp设置
                    OkHttpClient.Builder builder = new OkHttpClient.Builder();
                    builder.retryOnConnectionFailure(true)
                            //传递数据超时
                            .readTimeout(12,TimeUnit.SECONDS)
                            //请求超时
                            .connectTimeout(10, TimeUnit.SECONDS)
                            //网络拦截器， 添加token
                            .addNetworkInterceptor(mTokenInterceptor);

                    // debug模式下，集成Stetho 设置chrome调试
                    if (BuildConfig.DEBUG) {
                        builder.addNetworkInterceptor(new StethoInterceptor());
                    }

                    Retrofit retrofit=new Retrofit.Builder()
                            //绑定地址
                            .baseUrl(Constants.URL)
                            //okhttp
                            .client(builder.build())
                            //gson转换工厂
                            .addConverterFactory(GsonConverterFactory.create())
                            //解析rxjava的适配器
                            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                            .build();

                    mService=retrofit.create(BaseService.class);

                }
            }
        }
        return mService;

    }

}
