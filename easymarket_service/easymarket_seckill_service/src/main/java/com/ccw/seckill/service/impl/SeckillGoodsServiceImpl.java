package com.ccw.seckill.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ccw.seckill.dao.SeckillGoodsMapper;
import com.ccw.seckill.pojo.SeckillGoods;
import com.ccw.seckill.service.SeckillGoodsService;
import com.ccw.entity.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

/****
 * @Author:ujiuye
 * @Description:SeckillGoods业务层接口实现类
 * @Date 2021/2/1 14:19
 *****/
@Service
public class SeckillGoodsServiceImpl extends ServiceImpl<SeckillGoodsMapper, SeckillGoods> implements SeckillGoodsService {


    @Autowired
    private RedisTemplate redisTemplate;
    /**
     * SeckillGoods条件+分页查询
     * @param seckillGoods 查询条件
     * @param page 页码
     * @param size 页大小
     * @return 分页结果
     */
    @Override
    public PageResult<SeckillGoods> findPage(SeckillGoods seckillGoods, int page, int size){
         Page<SeckillGoods> mypage = new Page<>(page, size);
        QueryWrapper<SeckillGoods> queryWrapper = this.createQueryWrapper(seckillGoods);
        IPage<SeckillGoods> iPage = this.page(mypage, queryWrapper);
        return new PageResult<SeckillGoods>(iPage.getTotal(),iPage.getRecords());
    }

    /**
     * SeckillGoods分页查询
     * @param page
     * @param size
     * @return
     */
    @Override
    public PageResult<SeckillGoods> findPage(int page, int size){
        Page<SeckillGoods> mypage = new Page<>(page, size);
        IPage<SeckillGoods> iPage = this.page(mypage, new QueryWrapper<SeckillGoods>());

        return new PageResult<SeckillGoods>(iPage.getTotal(),iPage.getRecords());
    }

    /**
     * SeckillGoods条件查询
     * @param seckillGoods
     * @return
     */
    @Override
    public List<SeckillGoods> findList(SeckillGoods seckillGoods){
        //构建查询条件
        QueryWrapper<SeckillGoods> queryWrapper = this.createQueryWrapper(seckillGoods);
        //根据构建的条件查询数据
        return this.list(queryWrapper);
    }


    /**
     * SeckillGoods构建查询对象
     * @param seckillGoods
     * @return
     */
    public QueryWrapper<SeckillGoods> createQueryWrapper(SeckillGoods seckillGoods){
        QueryWrapper<SeckillGoods> queryWrapper = new QueryWrapper<>();
        if(seckillGoods!=null){
            // 
            if(!StringUtils.isEmpty(seckillGoods.getId())){
                 queryWrapper.eq("id",seckillGoods.getId());
            }
            // spu ID
            if(!StringUtils.isEmpty(seckillGoods.getGoodsId())){
                 queryWrapper.eq("goods_id",seckillGoods.getGoodsId());
            }
            // sku ID
            if(!StringUtils.isEmpty(seckillGoods.getItemId())){
                 queryWrapper.eq("item_id",seckillGoods.getItemId());
            }
            // 标题
            if(!StringUtils.isEmpty(seckillGoods.getTitle())){
                queryWrapper.like("title",seckillGoods.getTitle());
            }
            // 商品图片
            if(!StringUtils.isEmpty(seckillGoods.getSmallPic())){
                 queryWrapper.eq("small_pic",seckillGoods.getSmallPic());
            }
            // 原价格
            if(!StringUtils.isEmpty(seckillGoods.getPrice())){
                 queryWrapper.eq("price",seckillGoods.getPrice());
            }
            // 秒杀价格
            if(!StringUtils.isEmpty(seckillGoods.getCostPrice())){
                 queryWrapper.eq("cost_price",seckillGoods.getCostPrice());
            }
            // 商家ID
            if(!StringUtils.isEmpty(seckillGoods.getSellerId())){
                 queryWrapper.eq("seller_id",seckillGoods.getSellerId());
            }
            // 添加日期
            if(!StringUtils.isEmpty(seckillGoods.getCreateTime())){
                 queryWrapper.eq("create_time",seckillGoods.getCreateTime());
            }
            // 审核日期
            if(!StringUtils.isEmpty(seckillGoods.getCheckTime())){
                 queryWrapper.eq("check_time",seckillGoods.getCheckTime());
            }
            // 审核状态
            if(!StringUtils.isEmpty(seckillGoods.getStatus())){
                 queryWrapper.eq("status",seckillGoods.getStatus());
            }
            // 开始时间
            if(!StringUtils.isEmpty(seckillGoods.getStartTime())){
                 queryWrapper.eq("start_time",seckillGoods.getStartTime());
            }
            // 结束时间
            if(!StringUtils.isEmpty(seckillGoods.getEndTime())){
                 queryWrapper.eq("end_time",seckillGoods.getEndTime());
            }
            // 秒杀商品数
            if(!StringUtils.isEmpty(seckillGoods.getNum())){
                 queryWrapper.eq("num",seckillGoods.getNum());
            }
            // 剩余库存数
            if(!StringUtils.isEmpty(seckillGoods.getStockCount())){
                 queryWrapper.eq("stock_count",seckillGoods.getStockCount());
            }
            // 描述
            if(!StringUtils.isEmpty(seckillGoods.getIntroduction())){
                 queryWrapper.eq("introduction",seckillGoods.getIntroduction());
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
     * 修改SeckillGoods
     * @param seckillGoods
     */
    @Override
    public void update(SeckillGoods seckillGoods){
        this.updateById(seckillGoods);
    }

    /**
     * 增加SeckillGoods
     * @param seckillGoods
     */
    @Override
    public void add(SeckillGoods seckillGoods){
        this.save(seckillGoods);
    }

    /**
     * 根据ID查询SeckillGoods
     * @param id
     * @return
     */
    @Override
    public SeckillGoods findById(Long id){
        return  this.getById(id);
    }

    /**
     * 查询SeckillGoods全部数据
     * @return
     */
    @Override
    public List<SeckillGoods> findAll() {
        return this.list(new QueryWrapper<SeckillGoods>());
    }

    @Override
    public List<SeckillGoods> getSeckillGoods(String key) {
        System.out.println("seckillGoods"+key+"------------"+redisTemplate.boundHashOps("seckillGoods"+key).keys());
        return redisTemplate.boundHashOps("seckillGoods"+key).values();
    }

    @Override
    public SeckillGoods findOne(String key, Long id) {
        return (SeckillGoods) redisTemplate.boundHashOps("seckillGoods"+key).get(id);
    }
}
