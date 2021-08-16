package com.ccw.sellersgoods.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ccw.sellersgoods.pojo.GoodsDesc;
import com.ccw.entity.PageResult;

import java.util.List;

/****
 * @Author:ujiuye
 * @Description:GoodsDesc业务层接口
 * @Date 2021/2/1 14:19
 *****/

public interface GoodsDescService extends IService<GoodsDesc> {

    /***
     * GoodsDesc多条件分页查询
     * @param goodsDesc
     * @param page
     * @param size
     * @return
     */
    PageResult<GoodsDesc> findPage(GoodsDesc goodsDesc, int page, int size);

    /***
     * GoodsDesc分页查询
     * @param page
     * @param size
     * @return
     */
    PageResult<GoodsDesc> findPage(int page, int size);

    /***
     * GoodsDesc多条件搜索方法
     * @param goodsDesc
     * @return
     */
    List<GoodsDesc> findList(GoodsDesc goodsDesc);

    /***
     * 删除GoodsDesc
     * @param id
     */
    void delete(Long id);

    /***
     * 修改GoodsDesc数据
     * @param goodsDesc
     */
    void update(GoodsDesc goodsDesc);

    /***
     * 新增GoodsDesc
     * @param goodsDesc
     */
    void add(GoodsDesc goodsDesc);

    /**
     * 根据ID查询GoodsDesc
     * @param id
     * @return
     */
     GoodsDesc findById(Long id);

    /***
     * 查询所有GoodsDesc
     * @return
     */
    List<GoodsDesc> findAll();
}
