package com.ccw.seckill.task;

import com.ccw.seckill.dao.SeckillGoodsMapper;
import com.ccw.seckill.entity.SeckillStatus;
import com.ccw.seckill.pojo.SeckillGoods;
import com.ccw.seckill.pojo.SeckillOrder;
import com.ccw.utils.IdWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class MultiThreadCreateOrder {

    @Autowired
    private IdWorker idWorker;
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private SeckillGoodsMapper seckillGoodsMapper;
    @Async
    public void createOrder(){

        SeckillStatus seckillStatus = (SeckillStatus) redisTemplate.boundListOps("seckillUserQueue").rightPop();
        if (seckillStatus!=null) {
            Long id = seckillStatus.getGoodsId();
            String username = seckillStatus.getUsername();
            String timeKey = seckillStatus.getTime();

            //从秒杀商品队列中取得数据，判断数据是否存在，如果不存在就直接返回
            Object pop = redisTemplate.boundListOps("seckillDoodCountList" + id).rightPop();
            if (pop==null) {
                //清除队列信息
                this.cleanQueue(username);
                return;
            }
            //1、从redis中获取商品信息
            SeckillGoods seckillGoods = (SeckillGoods) redisTemplate.boundHashOps("seckillGoods" + timeKey).get(id);
            if (seckillGoods == null) {
                throw new RuntimeException("该秒杀商品不存在！");
            }
            if (seckillGoods.getStockCount() <= 0) {
                throw new RuntimeException("该商品已经被抢光！");
            }
            //2、创建订单并赋值
            SeckillOrder seckillOrder = new SeckillOrder();
            seckillOrder.setId(idWorker.nextId());
            seckillOrder.setSeckillId(id);    //订单编号
            seckillOrder.setMoney(seckillGoods.getCostPrice());   //商品价格
            seckillOrder.setUserId(username);           //当前登录人信息
            seckillOrder.setSellerId(seckillGoods.getSellerId());   //当前商家id
            seckillOrder.setCreateTime(new Date());    //订单创建时间
            seckillOrder.setStatus("0");            // 订单状态  未付款
            //3、将订单保存到缓存中
            redisTemplate.boundHashOps("seckillGoods").put(username, seckillOrder);

            seckillStatus.setStatus(2);    //设置支付状态，秒杀待支付
            seckillStatus.setOrderId(seckillOrder.getId());  //获取订单编号
            seckillStatus.setMoney(seckillOrder.getMoney().floatValue());  //获取订单金额
            //更新缓存中的状态对象
            redisTemplate.boundHashOps("userQueueStatus").put(username,seckillStatus);
            //4、更新库存，库存为空时清除redis中的数据
            //seckillGoods.setStockCount(seckillGoods.getStockCount() - 1);  //线程不安全
            //利用redis线程安全进行扣除库存
            Long queueCount = redisTemplate.boundHashOps("userQueueCount").increment(seckillGoods.getId(), -1);
            seckillGoods.setStockCount(queueCount.intValue());

            if (seckillGoods.getStockCount() <= 0) {
                seckillGoodsMapper.updateById(seckillGoods);
                redisTemplate.boundHashOps("seckillGoods" + timeKey).delete(id);
            } else {
                redisTemplate.boundHashOps("seckillGoods" + timeKey).put(id, seckillGoods);
            }
        }
    }
    private void cleanQueue(String username){
        //1、清除用户秒杀对象
        redisTemplate.boundHashOps("userQueueStatus").delete(username);
        //2、清除用户所携带的计数器
        redisTemplate.boundHashOps("userQueueCount").delete(username);
    }
}
