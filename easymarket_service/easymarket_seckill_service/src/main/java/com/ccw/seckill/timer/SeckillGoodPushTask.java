package com.ccw.seckill.timer;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ccw.seckill.dao.SeckillGoodsMapper;
import com.ccw.seckill.pojo.SeckillGoods;
import com.ccw.seckill.task.MultiThreadCreateOrder;
import com.ccw.utils.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.Date;
import java.util.List;
import java.util.Set;

@Component
public class SeckillGoodPushTask {

    @Autowired
    private SeckillGoodsMapper seckillGoodsMapper;
    @Autowired
    private RedisTemplate redisTemplate;
    //第一位表示的秒 ，表示选择 -表示范围 /表示步长 ？表示通配符
    @Scheduled(cron = "0/30 * * * * ?")
    public void loadGoodsPushRedis(){
        //1、根据当前时间获取当前时间菜单时间段
        List<Date> dateMenus = DateUtil.getDateMenus();
        //2、遍历时间段获取符合条件的秒杀商品并存入缓存中
        for (Date dateMenu : dateMenus) {
            //根据每个时间段获取开始的时间，并转换为字符串
            String beginTime = DateUtil.date2Str(dateMenu);
            //查询数据库，设置查询条件
            QueryWrapper wrapper = new QueryWrapper();
            wrapper.eq("status","1");                   //审核通过
            wrapper.gt("stock_count","0");              //库存数量大于0
            wrapper.ge("start_time",DateUtil.date2StrFull(dateMenu));             //开始时间大于开始时间段
            wrapper.lt("end_time",DateUtil.date2Str(DateUtil.addDateHour(dateMenu,2)));
            //获取已经暂存到redis中的数据并剔除
            Set<String> keys = redisTemplate.boundHashOps("seckillGoods" + beginTime).keys();
            if (!CollectionUtils.isEmpty(keys)){
                //for (String key : keys) {
                    wrapper.notIn("id",keys);
                // }
            }
            //执行查询数据库
            List<SeckillGoods> list = seckillGoodsMapper.selectList(wrapper);
            if (!CollectionUtils.isEmpty(list)) {
                for (SeckillGoods seckillGoods : list) {
                    System.out.println("保存秒杀商品" + seckillGoods.getId());
                    System.out.println("seckillGoods"+beginTime);
                    redisTemplate.boundHashOps("seckillGoods"+beginTime).put(seckillGoods.getId(),seckillGoods);

                    //将商品根据库存数量保存到队列中
                    redisTemplate.boundListOps("seckillDoodCountList"+seckillGoods.getId()).leftPushAll(this.pushIds(seckillGoods.getStockCount(),seckillGoods.getId()));
                    //将该款商品的库存记录存入到缓存中
                    //redisTemplate.boundHashOps("seckillDoodCountList").put(seckillGoods.getId(),seckillGoods.getStockCount());
                    redisTemplate.boundHashOps("seckillDoodCountList").increment(seckillGoods.getId(),seckillGoods.getStockCount());
                    //设置超时时间
                    redisTemplate.expireAt("seckillGoods"+beginTime,DateUtil.addDateHour(dateMenu,2));
                }
            }
        }
    }
    private Long[] pushIds(int len, Long id){
        Long[] ids = new Long[len];
        for (int i = 0; i < ids.length ; i++) {
            ids[i] = id;
        }
        return ids;
    }
}
