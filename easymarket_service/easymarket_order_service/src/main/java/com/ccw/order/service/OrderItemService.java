package com.ccw.order.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ccw.order.pojo.OrderItem;
import com.ccw.entity.PageResult;

import java.util.List;

/****
 * @Author:ujiuye
 * @Description:OrderItem业务层接口
 * @Date 2021/2/1 14:19
 *****/

public interface OrderItemService extends IService<OrderItem> {

    /***
     * OrderItem多条件分页查询
     * @param orderItem
     * @param page
     * @param size
     * @return
     */
    PageResult<OrderItem> findPage(OrderItem orderItem, int page, int size);

    /***
     * OrderItem分页查询
     * @param page
     * @param size
     * @return
     */
    PageResult<OrderItem> findPage(int page, int size);

    /***
     * OrderItem多条件搜索方法
     * @param orderItem
     * @return
     */
    List<OrderItem> findList(OrderItem orderItem);

    /***
     * 删除OrderItem
     * @param id
     */
    void delete(Long id);

    /***
     * 修改OrderItem数据
     * @param orderItem
     */
    void update(OrderItem orderItem);

    /***
     * 新增OrderItem
     * @param orderItem
     */
    void add(OrderItem orderItem);

    /**
     * 根据ID查询OrderItem
     * @param id
     * @return
     */
     OrderItem findById(Long id);

    /***
     * 查询所有OrderItem
     * @return
     */
    List<OrderItem> findAll();
}
