package com.ccw.sellersgoods.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ccw.order.pojo.OrderItem;
import com.ccw.sellersgoods.pojo.Item;
import org.apache.ibatis.annotations.Update;

/****
 * @Author:ujiuye
 * @Description:Item的Dao
 * @Date 2021/2/1 14:19
 *****/
public interface ItemMapper extends BaseMapper<Item> {
    @Update("update tb_item set num=num-#{num} where id=#{itemId} and num>=#{num}")
    public void decrCount(OrderItem orderItem);
}
