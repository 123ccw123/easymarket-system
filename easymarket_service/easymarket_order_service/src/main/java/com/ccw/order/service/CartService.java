package com.ccw.order.service;

import com.ccw.order.group.Cart;

import java.util.List;

public interface CartService {

    /*添加原始购物车*/

    /**
     * @param src_cartList 原始购物车
     * @param itemId sku的id
     * @param num  购买的数量
     * @return
     */
    public List<Cart> addGoodsToCart(List<Cart> src_cartList,Long itemId,Integer num);

    /**
     * 在缓存中查询当前登录用户的购物车列表
     * @param username
     * @return
     */
    public List<Cart> findCartListFromRedis(String username);

    public void saveCartListToRedis(String username,List<Cart> cartList);
}
