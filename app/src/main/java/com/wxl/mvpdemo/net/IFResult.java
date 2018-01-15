package com.wxl.mvpdemo.net;

/**
 * Created by zhanghongqiang on 16/7/27  下午1:27
 * ToDo:
 */
public interface IFResult {

    public int errorCode();
    public String errorMsg();
    public Object result();
    public Boolean success();
}
