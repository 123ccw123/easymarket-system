package com.ccw.order.service.impl;

import com.ccw.entity.Result;
import com.ccw.order.group.Cart;
import com.ccw.order.pojo.OrderItem;
import com.ccw.order.service.CartService;
import com.ccw.sellersgoods.feign.ItemFeign;
import com.ccw.sellersgoods.pojo.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private ItemFeign itemFeign;
    @Override
    public List<Cart> addGoodsToCart(List<Cart> src_cartList, Long itemId, Integer num) {
        //1、根据SKU的id查询商品详情
        Result<Item> result = itemFeign.findById(itemId);
        if (result==null){
            throw new RuntimeException("找不到该商家的服务！");
        }
        if(result.getData()==null){
            throw new RuntimeException("购物车中不存在该商品！");
        }
        Item item = result.getData();

        if (!item.getStatus().equals("1")){
            throw new RuntimeException("该商品未上架！");
        }
        //2、查询商家名称和商家id
        String sellerId = item.getSellerId();
        String seller = item.getSeller();
        //3、判断购物车中的商家
        Cart cart = this.searchSellerBySellerId(src_cartList, sellerId);
        if (cart==null){
            Cart cartReturn = new Cart();
            cartReturn.setSellerName(seller);
            cartReturn.setSellerId(sellerId);
            OrderItem orderItem = this.createOrderItem(item, num);
            List<OrderItem> list = new ArrayList();
            list.add(orderItem);
            cartReturn.setOrderItemList(list);
            //将新创建购物车放入原购物车
            src_cartList.add(cartReturn);
        }else {
            //判断该购车中是否具有该商品
            OrderItem orderItem = this.searchOrderItemByItemId(cart.getOrderItemList(), item.getId());
            if (orderItem==null){
                orderItem = this.createOrderItem(item,num);
                cart.getOrderItemList().add(orderItem);
            }else {
                //如果有该商品则更改商品数量
                //增加
                orderItem.setNum(orderItem.getNum()+num);
                orderItem.setTotalFee(orderItem.getPrice().multiply(new BigDecimal(orderItem.getNum())));
                //减少
                if (orderItem.getNum()==0){
                    cart.getOrderItemList().remove(orderItem);
                }
                if (cart.getOrderItemList().size()==0){
                    src_cartList.remove(cart);
                }
            }
        }
        return src_cartList;
    }

    /*查询redis中的信息*/
    @Override
    public List<Cart> findCartListFromRedis(String username) {
        System.out.println("从redis中查询购物车");
        List<Cart> cartList = (List<Cart>) redisTemplate.boundHashOps("cartList").get(username);
        if (CollectionUtils.isEmpty(cartList)){
            cartList = new ArrayList<>();
        }
        return cartList;
    }

    /*存入redis*/
    @Override
    public void saveCartListToRedis(String username, List<Cart> cartList) {
        redisTemplate.boundHashOps("cartList").put(username,cartList);
    }

    /*根据商家ID查询购物车中是否有该商家*/
    private Cart searchSellerBySellerId(List<Cart> cartList,String sellerId){
        for (Cart cart : cartList) {
            if (cart.getSellerId().equals(sellerId)){
                return cart;
            }
        }
        return null;
    }
    /*添加订单列表*/
    private OrderItem createOrderItem(Item item,Integer num){
        if (num<0){
            throw new RuntimeException("购买数量非法！");
        }
        OrderItem orderItem = new OrderItem();
        orderItem.setNum(num);                  //购买数量
        orderItem.setItemId(item.getId());      //Spu的id
        orderItem.setPicPath(item.getImage());  //图片的地址
        orderItem.setGoodsId(item.getGoodsId());//商品的id
        orderItem.setPrice(item.getPrice());    //商品价格
        orderItem.setTitle(item.getTitle());    //商品的标题
        orderItem.setTotalFee(item.getPrice().multiply(new BigDecimal(num)));  //所有商品的做总金额
        orderItem.setSellerId(item.getSellerId());  //设置商家id
        return  orderItem;
    }
    /*查询该订单中是否含有该商品*/
    private OrderItem searchOrderItemByItemId(List<OrderItem> orderItemList,Long itemId){
        for (OrderItem orderItem : orderItemList) {
            if (orderItem.getItemId().longValue()==itemId.longValue()) {
                return orderItem;
            }
        }
        return null;
    }
}
