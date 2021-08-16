package com.ccw.sellersgoods.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ccw.sellersgoods.group.GoodsEntity;
import com.ccw.sellersgoods.pojo.Goods;
import com.ccw.entity.PageResult;

import java.util.List;

/****
 * @Author:ujiuye
 * @Description:Goods业务层接口
 * @Date 2021/2/1 14:19
 *****/

public interface GoodsService extends IService<Goods> {

    /***
     * Goods多条件分页查询
     * @param goods
     * @param page
     * @param size
     * @return
     */
    PageResult<Goods> findPage(Goods goods, int page, int size);

    /***
     * Goods分页查询
     * @param page
     * @param size
     * @return
     */
    PageResult<Goods> findPage(int page, int size);

    /***
     * Goods多条件搜索方法
     * @param goods
     * @return
     */
    List<Goods> findList(Goods goods);

    /***
     * 删除Goods
     * @param id
     */
    void delete(Long id);

    /***
     * 修改Goods数据
     * @param goodsEntity
     */
    void update(GoodsEntity goodsEntity);

    /***
     * 新增Goods
     * @param goodsEntity
     */
    void add(GoodsEntity goodsEntity);

    /**
     * 根据ID查询Goods
     * @param id
     * @return
     */
     GoodsEntity findById(Long id);

    /***
     * 查询所有Goods
     * @return
     */
    List<Goods> findAll();

    /*对于商品的审核*/
    void audit(Long id);
}
