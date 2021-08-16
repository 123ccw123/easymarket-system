package com.ccw.seckill.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ccw.seckill.dao.SeckillGoodsMapper;
import com.ccw.seckill.dao.SeckillOrderMapper;
import com.ccw.seckill.entity.SeckillStatus;
import com.ccw.seckill.pojo.SeckillGoods;
import com.ccw.seckill.pojo.SeckillOrder;
import com.ccw.seckill.service.SeckillOrderService;
import com.ccw.entity.PageResult;
import com.ccw.seckill.task.MultiThreadCreateOrder;
import com.ccw.utils.IdWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.BoundHashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.security.Key;
import java.util.Date;
import java.util.List;

/****
 * @Author:ujiuye
 * @Description:SeckillOrder业务层接口实现类
 * @Date 2021/2/1 14:19
 *****/
@Service
public class SeckillOrderServiceImpl extends ServiceImpl<SeckillOrderMapper, SeckillOrder> implements SeckillOrderService {

    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private MultiThreadCreateOrder threadCreateOrder;
    @Autowired
    private SeckillGoodsMapper seckillGoodsMapper;
    /**
     * SeckillOrder条件+分页查询
     * @param seckillOrder 查询条件
     * @param page 页码
     * @param size 页大小
     * @return 分页结果
     */
    @Override
    public PageResult<SeckillOrder> findPage(SeckillOrder seckillOrder, int page, int size){
         Page<SeckillOrder> mypage = new Page<>(page, size);
        QueryWrapper<SeckillOrder> queryWrapper = this.createQueryWrapper(seckillOrder);
        IPage<SeckillOrder> iPage = this.page(mypage, queryWrapper);
        return new PageResult<SeckillOrder>(iPage.getTotal(),iPage.getRecords());
    }

    /**
     * SeckillOrder分页查询
     * @param page
     * @param size
     * @return
     */
    @Override
    public PageResult<SeckillOrder> findPage(int page, int size){
        Page<SeckillOrder> mypage = new Page<>(page, size);
        IPage<SeckillOrder> iPage = this.page(mypage, new QueryWrapper<SeckillOrder>());

        return new PageResult<SeckillOrder>(iPage.getTotal(),iPage.getRecords());
    }

    /**
     * SeckillOrder条件查询
     * @param seckillOrder
     * @return
     */
    @Override
    public List<SeckillOrder> findList(SeckillOrder seckillOrder){
        //构建查询条件
        QueryWrapper<SeckillOrder> queryWrapper = this.createQueryWrapper(seckillOrder);
        //根据构建的条件查询数据
        return this.list(queryWrapper);
    }


    /**
     * SeckillOrder构建查询对象
     * @param seckillOrder
     * @return
     */
    public QueryWrapper<SeckillOrder> createQueryWrapper(SeckillOrder seckillOrder){
        QueryWrapper<SeckillOrder> queryWrapper = new QueryWrapper<>();
        if(seckillOrder!=null){
            // 主键
            if(!StringUtils.isEmpty(seckillOrder.getId())){
                 queryWrapper.eq("id",seckillOrder.getId());
            }
            // 秒杀商品ID
            if(!StringUtils.isEmpty(seckillOrder.getSeckillId())){
                 queryWrapper.eq("seckill_id",seckillOrder.getSeckillId());
            }
            // 支付金额
            if(!StringUtils.isEmpty(seckillOrder.getMoney())){
                 queryWrapper.eq("money",seckillOrder.getMoney());
            }
            // 用户
            if(!StringUtils.isEmpty(seckillOrder.getUserId())){
                 queryWrapper.eq("user_id",seckillOrder.getUserId());
            }
            // 商家
            if(!StringUtils.isEmpty(seckillOrder.getSellerId())){
                 queryWrapper.eq("seller_id",seckillOrder.getSellerId());
            }
            // 创建时间
            if(!StringUtils.isEmpty(seckillOrder.getCreateTime())){
                 queryWrapper.eq("create_time",seckillOrder.getCreateTime());
            }
            // 支付时间
            if(!StringUtils.isEmpty(seckillOrder.getPayTime())){
                 queryWrapper.eq("pay_time",seckillOrder.getPayTime());
            }
            // 状态
            if(!StringUtils.isEmpty(seckillOrder.getStatus())){
                 queryWrapper.eq("status",seckillOrder.getStatus());
            }
            // 收货人地址
            if(!StringUtils.isEmpty(seckillOrder.getReceiverAddress())){
                 queryWrapper.eq("receiver_address",seckillOrder.getReceiverAddress());
            }
            // 收货人电话
            if(!StringUtils.isEmpty(seckillOrder.getReceiverMobile())){
                 queryWrapper.eq("receiver_mobile",seckillOrder.getReceiverMobile());
            }
            // 收货人
            if(!StringUtils.isEmpty(seckillOrder.getReceiver())){
                 queryWrapper.eq("receiver",seckillOrder.getReceiver());
            }
            // 交易流水
            if(!StringUtils.isEmpty(seckillOrder.getTransactionId())){
                 queryWrapper.eq("transaction_id",seckillOrder.getTransactionId());
            }
        }
        return queryWrapper;
    }

    /**
     * 删除
     * @param id
     */
    @Override
    public void delete(Long id){
        this.removeById(id);
    }

    /**
     * 修改SeckillOrder
     * @param seckillOrder
     */
    @Override
    public void update(SeckillOrder seckillOrder){
        this.updateById(seckillOrder);
    }

    /**
     * 增加SeckillOrder
     * @param seckillOrder
     */
    @Override
    public void add(SeckillOrder seckillOrder){
        this.save(seckillOrder);
    }

    /**
     * 根据ID查询SeckillOrder
     * @param id
     * @return
     */
    @Override
    public SeckillOrder findById(Long id){
        return  this.getById(id);
    }

    /**
     * 查询SeckillOrder全部数据
     * @return
     */
    @Override
    public List<SeckillOrder> findAll() {
        return this.list(new QueryWrapper<SeckillOrder>());
    }

    @Override
    public boolean add(Long id, String timeKey, String username) {
        Long count = redisTemplate.boundHashOps("userQueueCount").increment(username, 1);
        if (count>1){
            throw  new RuntimeException("请勿重复刷单！");
        }
        //1、创建秒杀状态对象
        SeckillStatus seckillStatus = new SeckillStatus(username,new Date(),1,id,timeKey);
        //2、将秒杀状态放对象入队列
        redisTemplate.boundListOps("seckillUserQueue").leftPush(seckillStatus);
        //3、将秒杀对象存入缓存中
        redisTemplate.boundHashOps("userQueueStatus").put(username,seckillStatus);
        threadCreateOrder.createOrder();
        return true;
    }

    @Override
    public SeckillStatus queryStatusFromRedis(String username) {
        return (SeckillStatus) redisTemplate.boundHashOps("userQueueStatus").get(username);
    }

    @Override
    public void updateStatus(String out_trade_no, String transaction_id, String username) {
        //1.根据用户名提取订单信息
        SeckillOrder seckillOrder = (SeckillOrder) redisTemplate.boundHashOps("seckillOrder");
        //2.修改订单状态
        seckillOrder.setStatus("1");
        seckillOrder.setTransactionId(transaction_id);
        //3.保存订单到数据库
        this.save(seckillOrder);
        //4.清空缓存中的数据信息
        redisTemplate.boundHashOps("seckillOrder").delete(username);
        //5.清空用户的抢单计数器
        redisTemplate.boundHashOps("userQueueCount").delete(username);
        //6.清空缓存中的用户信息
        redisTemplate.boundHashOps("userQueueStatus").delete(username);
    }

    @Override
    public void closeOrder(String username) {
        //1.从缓存中取得用户秒杀状态
        SeckillStatus userQueueStatus = (SeckillStatus) redisTemplate.boundHashOps("userQueueStatus").get(username);
        //2.从缓存中获取用户的秒杀订单
        SeckillOrder seckillOrder = (SeckillOrder) redisTemplate.boundHashOps("seckillOrder").get(username);
        //3.判断订单是否为空，不为空的话删除订单
        if (userQueueStatus!=null&&seckillOrder!=null) {
            //4.判断缓存中是否有该商品
            SeckillGoods seckillGoods = (SeckillGoods) redisTemplate.boundHashOps("seckillGoods" + userQueueStatus.getTime()).get(userQueueStatus.getGoodsId());
            //5.如果没有该商品，从数据库中进行获取
            if (seckillGoods==null){
                seckillGoods = seckillGoodsMapper.selectById(userQueueStatus.getGoodsId());
            }
            //6.修改秒杀商品的存储数据
            redisTemplate.boundListOps("seckillGoodList"+seckillGoods.getId()).leftPush(seckillGoods.getId());
            Long count = redisTemplate.boundHashOps("seckillGoodsCount").increment(seckillGoods.getId(),1);
            //7.更新回缓存
            seckillGoods.setStockCount(count.intValue());
            redisTemplate.boundHashOps("seckillGoods"+userQueueStatus.getTime()).put(seckillGoods.getId(),seckillGoods);
            //8.删除缓存中用户抢单计数器，秒杀状态数据
            redisTemplate.boundHashOps("seckillGoodsCount").delete(username);
            redisTemplate.boundHashOps("userQueueStatus").delete(username);
        }
    }
}
