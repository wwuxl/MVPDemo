package com.wxl.mvpdemo.net;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by zhanghongqiang on 16/7/27  下午1:35
 * ToDo:
 */
public class ResponseList<T> implements Serializable, IFResult {

    /**
     * 0成功，1未登录，2其他错误
     */
    public int errorCode = -1;
    public String errorMsg;
    public ArrayList<T> result;
    public boolean success;

    @Override
    public int errorCode() {
        return errorCode;
    }

    @Override
    public String errorMsg() {
        return errorMsg;
    }

    @Override
    public Object result() {
        return result;
    }

    @Override
    public Boolean success() {
        return success;
    }
}
