package com.wxl.mvpdemo.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by wxl on 2017/9/26.
 * 评价
 */

public class Comment implements Serializable{


    /**
     * buyerId : 19
     * buyerImg :
     * buyerName : 匿名
     * commentImg1 : http://img.shop.sibumbg.cn/a3f0eb875f0749d49c174a6a2205e516.jpg
     * commentImg2 : http://img.shop.sibumbg.cn/4f34aebfe59e4bc7bfadb01f329662f3.jpg
     * commentText : sadsasadsadsdsad撒多所大sasasa
     * createDt : 2017-09-19 14:12:35
     * goodsComments : [{"evaluationStatus":0,"goodsCommentId":81,"goodsId":21577,"goodsName":"测试商品 拍下无效","imgUrl":"http://image.mall.doubozhibo.com/9fc3fd1b110f4ebc985c90771c437463.png","shopCommentId":49}]
     * isAnonymous : 1
     * isImage : 1
     * orderCode : 50090400020
     * orderId : 134
     * score : 3
     * shopCommentId : 49
     */

    public int buyerId;
    public String buyerImg;
    public String buyerName;
    public String commentImg1;
    public String commentImg2;
    public String commentImg3;
    public String commentText;
    public String createDt;
    public int isAnonymous;
    public int isImage;
    public String orderCode;
    public int orderId;
    public int score;
    public int shopCommentId;
    public String shopReply;
    public List<GoodsComments> goodsComments;

    public static class GoodsComments implements Serializable{
        /**
         * evaluationStatus : 0
         * goodsCommentId : 81
         * goodsId : 21577
         * goodsName : 测试商品 拍下无效
         * imgUrl : http://image.mall.doubozhibo.com/9fc3fd1b110f4ebc985c90771c437463.png
         * shopCommentId : 49
         */

        public int evaluationStatus;//0-赞；1-踩,
        public int goodsCommentId;
        public int goodsId;
        public String goodsName;
        public String skuValue;
        public String imgUrl;
        public int shopCommentId;

    }
}
