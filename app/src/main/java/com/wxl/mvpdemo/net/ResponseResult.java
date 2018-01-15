package com.wxl.mvpdemo.net;

import java.io.Serializable;
import java.util.List;

/**
 * Created by zhanghongqiang on 16/7/27  下午1:29
 * ToDo:
 */
public class ResponseResult<T>  implements Serializable , IFResult{

    /**
     * 0成功，1未登录，2其他错误
     */
    public int errorCode=-1;
    public String errorMsg;
    public boolean success;
    public Result<T> result;

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


    public class Result<T> implements Serializable {

        public int totalCount;
        public int totalPage;
        public List<T> data;

    }
}
