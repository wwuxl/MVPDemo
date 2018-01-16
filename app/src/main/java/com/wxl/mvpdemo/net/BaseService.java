package com.wxl.mvpdemo.net;

import com.wxl.mvpdemo.model.User;

import io.reactivex.Flowable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by wxl on 2018/1/15.
 */

public interface BaseService {

    @FormUrlEncoded
    @POST("app-login")
    Flowable<Response<User>> login(
            @Field("phone") String phone,
            @Field("passWord") String password
    );

    /**
     * 注册账户
     *
     * @param phone
     * @param password
     * @param verifyCode
     * @return
     */
    @FormUrlEncoded
    @POST("visitor/app-register")
    Flowable<Response<Object>> register(
            @Field("phone") String phone,
            @Field("passWord") String password,
            @Field("verifyCode") String verifyCode
    );

    /**
     * 找回密码
     *
     * @param phone
     * @param password
     * @param verifyCode
     * @return
     */
    @FormUrlEncoded
    @POST("visitor/resetPwd")
    Flowable<Response<Object>> resetPwd(
            @Field("phone") String phone,
            @Field("passWord") String password,
            @Field("verifyCode") String verifyCode
    );

    /**
     * 短信验证码
     *
     * @param phone
     * @param type  0, "注册" 1, "重置密码" 5, "修改登录手机号"
     * @return
     */
    @FormUrlEncoded
    @POST("visitor/sendSmsCode")
    Flowable<Response<Object>> sendSmsCode(
            @Field("phone") String phone,
            @Field("type") int type
    );
}
