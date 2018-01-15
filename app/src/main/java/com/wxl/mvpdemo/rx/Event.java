package com.wxl.mvpdemo.rx;

/**
 * Created by zhanghongqiang on 2017/4/1.
 * ToDo:
 */

public interface Event {

    //添加和删除分类
    class EditCategory{

    }

    //添加和删除分类
    class ChangePhone{

    }

    //商品添加Ok
    class GoodsAddSuccess {
        public int id;
    }

    //修改店铺信息
    class EditShopInfo {
        public int id;
    }


}
