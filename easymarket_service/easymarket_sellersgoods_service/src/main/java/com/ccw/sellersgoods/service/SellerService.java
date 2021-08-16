package com.ccw.sellersgoods.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ccw.sellersgoods.pojo.Seller;
import com.ccw.entity.PageResult;

import java.util.List;

/****
 * @Author:ujiuye
 * @Description:Seller业务层接口
 * @Date 2021/2/1 14:19
 *****/

public interface SellerService extends IService<Seller> {

    /***
     * Seller多条件分页查询
     * @param seller
     * @param page
     * @param size
     * @return
     */
    PageResult<Seller> findPage(Seller seller, int page, int size);

    /***
     * Seller分页查询
     * @param page
     * @param size
     * @return
     */
    PageResult<Seller> findPage(int page, int size);

    /***
     * Seller多条件搜索方法
     * @param seller
     * @return
     */
    List<Seller> findList(Seller seller);

    /***
     * 删除Seller
     * @param id
     */
    void delete(String id);

    /***
     * 修改Seller数据
     * @param seller
     */
    void update(Seller seller);

    /***
     * 新增Seller
     * @param seller
     */
    void add(Seller seller);

    /**
     * 根据ID查询Seller
     * @param id
     * @return
     */
     Seller findById(String id);

    /***
     * 查询所有Seller
     * @return
     */
    List<Seller> findAll();
}
