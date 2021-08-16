package com.ccw.seckill.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ccw.seckill.entity.SeckillStatus;
import com.ccw.seckill.pojo.SeckillOrder;
import com.ccw.entity.PageResult;

import java.util.List;

/****
 * @Author:ujiuye
 * @Description:SeckillOrder业务层接口
 * @Date 2021/2/1 14:19
 *****/

public interface SeckillOrderService extends IService<SeckillOrder> {

    /***
     * SeckillOrder多条件分页查询
     * @param seckillOrder
     * @param page
     * @param size
     * @return
     */
    PageResult<SeckillOrder> findPage(SeckillOrder seckillOrder, int page, int size);

    /***
     * SeckillOrder分页查询
     * @param page
     * @param size
     * @return
     */
    PageResult<SeckillOrder> findPage(int page, int size);

    /***
     * SeckillOrder多条件搜索方法
     * @param seckillOrder
     * @return
     */
    List<SeckillOrder> findList(SeckillOrder seckillOrder);

    /***
     * 删除SeckillOrder
     * @param id
     */
    void delete(Long id);

    /***
     * 修改SeckillOrder数据
     * @param seckillOrder
     */
    void update(SeckillOrder seckillOrder);

    /***
     * 新增SeckillOrder
     * @param seckillOrder
     */
    void add(SeckillOrder seckillOrder);

    /**
     * 根据ID查询SeckillOrder
     * @param id
     * @return
     */
     SeckillOrder findById(Long id);

    /***
     * 查询所有SeckillOrder
     * @return
     */
    List<SeckillOrder> findAll();

    /**
     * 创建订单
     * @param id 商品id
     * @param timeKey  时间段
     * @param username  当前登录用户
     * @return
     */
    boolean add(Long id,String timeKey,String username);

    /**
     * 根据当前对象查询秒杀状态
     * @param username
     * @return
     */
    SeckillStatus queryStatusFromRedis(String username);

    /**
     * 更新支付状态
     * @param out_trade_no   订单编号
     * @param transaction_id    交易流水号
     * @param username    用户名称
     */
    void updateStatus(String out_trade_no,String transaction_id,String username);

    /**
     * 关闭订单，回滚库存
     * @param username
     */
    void closeOrder(String username);
}
