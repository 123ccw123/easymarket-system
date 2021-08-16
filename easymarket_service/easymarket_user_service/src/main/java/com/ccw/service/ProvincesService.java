package com.ccw.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ccw.user.pojo.Provinces;
import com.ccw.entity.PageResult;

import java.util.List;

/****
 * @Author:ujiuye
 * @Description:Provinces业务层接口
 * @Date 2021/2/1 14:19
 *****/

public interface ProvincesService extends IService<Provinces> {

    /***
     * Provinces多条件分页查询
     * @param provinces
     * @param page
     * @param size
     * @return
     */
    PageResult<Provinces> findPage(Provinces provinces, int page, int size);

    /***
     * Provinces分页查询
     * @param page
     * @param size
     * @return
     */
    PageResult<Provinces> findPage(int page, int size);

    /***
     * Provinces多条件搜索方法
     * @param provinces
     * @return
     */
    List<Provinces> findList(Provinces provinces);

    /***
     * 删除Provinces
     * @param id
     */
    void delete(Integer id);

    /***
     * 修改Provinces数据
     * @param provinces
     */
    void update(Provinces provinces);

    /***
     * 新增Provinces
     * @param provinces
     */
    void add(Provinces provinces);

    /**
     * 根据ID查询Provinces
     * @param id
     * @return
     */
     Provinces findById(Integer id);

    /***
     * 查询所有Provinces
     * @return
     */
    List<Provinces> findAll();
}
