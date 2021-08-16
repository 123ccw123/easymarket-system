package com.ccw.order.controller;

import com.ccw.entity.Result;
import com.ccw.entity.StatusCode;
import com.ccw.order.group.Cart;
import com.ccw.order.service.CartService;
import com.ccw.utils.TokenDecode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @Autowired
    private TokenDecode tokenDecode;

    /*查询购物车信息*/
    @RequestMapping("/findCartList")
    public List<Cart> findCartList(){

        //从令牌中获取用户信息
        Map<String, String> userInfo = tokenDecode.getUserInfo();
        String username = userInfo.get("username");

        List<Cart> cartListFromRedis = cartService.findCartListFromRedis(username);
        return cartListFromRedis;
    }

    /*添加购物车信息*/
    @RequestMapping("/addCartList")
    public Result addCartList(Long itemId, Integer num){
        //获取当前登录用户信息
        //String username = "ujiuye";
        //从令牌中获取用户信息
        Map<String, String> userInfo = tokenDecode.getUserInfo();
        String username = userInfo.get("username");
        System.out.println(username);

        List<Cart> cartList = this.findCartList();
        cartList = cartService.addGoodsToCart(cartList,itemId,num);

        //更新缓存中的购物车信息
        cartService.saveCartListToRedis(username,cartList);
        return new Result(true, StatusCode.OK,"添加购物车成功!");
    }
}
