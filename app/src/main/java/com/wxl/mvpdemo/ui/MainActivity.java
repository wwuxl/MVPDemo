package com.wxl.mvpdemo.ui;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.view.LayoutInflater;

import com.wxl.mvpdemo.R;
import com.wxl.mvpdemo.model.Comment;
import com.wxl.mvpdemo.net.Api;
import com.wxl.mvpdemo.net.ResponseResult;
import com.wxl.mvpdemo.rx.RxBus;
import com.wxl.mvpdemo.rx.RxUtils;
import com.wxl.mvpdemo.rx.subscribers.OnResponseListener;

import java.util.List;

import io.reactivex.functions.Consumer;

public class MainActivity extends NetActivity {


    private List<Comment> mComments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }

    @Override
    protected void initEvent() {
        RxBus.getInstance().register(MainActivity.class, new Consumer<MainActivity>() {
            @Override
            public void accept(MainActivity t) throws Exception {
                mPageView.showStatusPage(current_status);
            }
        });

    }

    @Override
    protected void onSubLoadData() {
        RxUtils.rx(Api.getService().getCommentList(1, 20, 1), new OnResponseListener<ResponseResult<Comment>>() {
            @Override
            public void onLoading() {

            }

            @Override
            public void onError(Throwable t) {
                current_status=mPageView.STATUS_ERROR;
                RxBus.getInstance().post(MainActivity.this);
            }

            @Override
            public void onNext(ResponseResult<Comment> result) {
                mComments = result.result.data;
                if(mComments!=null||mComments.size()==0){
                    current_status=mPageView.STATUS_EMPTY;
                    RxBus.getInstance().post(MainActivity.this);
                    return;
                }

                current_status=mPageView.STATUS_SUCCEED;
                RxBus.getInstance().post(MainActivity.this);

            }

            @Override
            public void onComplete() {

            }
        });
    }

    @Override
    public ViewDataBinding initSubContentView() {
        DataBindingUtil.inflate(LayoutInflater.from(this),R.layout.activity_main,null,false);
        return null;
    }

    @Override
    public String initTitle() {
        return "评价";
    }



}
