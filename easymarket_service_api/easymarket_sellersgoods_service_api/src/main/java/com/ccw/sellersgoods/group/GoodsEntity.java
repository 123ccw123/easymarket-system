package com.ccw.sellersgoods.group;

import com.ccw.sellersgoods.pojo.Goods;
import com.ccw.sellersgoods.pojo.GoodsDesc;
import com.ccw.sellersgoods.pojo.Item;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class GoodsEntity implements Serializable {
    private Goods goods;   //SPU的对象
    private GoodsDesc goodsDesc; //扩展对象信息
    private List<Item> itemList; //SKU的对象

    public Goods getGoods() {
        return goods;
    }

    public void setGoods(Goods goods) {
        this.goods = goods;
    }

    public GoodsDesc getGoodsDesc() {
        return goodsDesc;
    }

    public void setGoodsDesc(GoodsDesc goodsDesc) {
        this.goodsDesc = goodsDesc;
    }

    public List<Item> getItemList() {
        return itemList;
    }

    public void setItemList(List<Item> itemList) {
        this.itemList = itemList;
    }
}
