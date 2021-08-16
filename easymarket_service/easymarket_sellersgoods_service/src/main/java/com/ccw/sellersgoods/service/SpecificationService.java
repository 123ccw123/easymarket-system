package com.ccw.sellersgoods.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ccw.sellersgoods.group.SpecEntity;
import com.ccw.sellersgoods.pojo.Specification;
import com.ccw.entity.PageResult;

import java.util.List;
import java.util.Map;

/****
 * @Author:ujiuye
 * @Description:Specification业务层接口
 * @Date 2021/2/1 14:19
 *****/

public interface SpecificationService extends IService<Specification> {

    /***
     * Specification多条件分页查询
     * @param specification
     * @param page
     * @param size
     * @return
     */
    PageResult<Specification> findPage(Specification specification, int page, int size);

    /***
     * Specification分页查询
     * @param page
     * @param size
     * @return
     */
    PageResult<Specification> findPage(int page, int size);

    /***
     * Specification多条件搜索方法
     * @param specification
     * @return
     */
    List<Specification> findList(Specification specification);

    /***
     * 删除Specification  删除规格
     * @param id
     */
    void delete(Long id);

    /***
     * 修改Specification数据  修改规格
     * @param specEntity
     */
    void update(SpecEntity specEntity);

    /***
     * 新增Specification
     * @param specEntity
     */
    //void add(Specification specification);
    /*新增specificationService*/
    void add(SpecEntity specEntity);
    /**
     * 根据ID查询Specification  查询规格
     * @param id
     * @return
     */
    SpecEntity findById(Long id);

    /***
     * 查询所有Specification
     * @return
     */
    List<Specification> findAll();

    /*下拉列表查询*/
    public List<Map> selectOption();
}
