package com.ccw.sellersgoods.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ccw.sellersgoods.pojo.Brand;
import com.ccw.entity.PageResult;

import java.util.List;
import java.util.Map;

/****
 * @Author:ujiuye
 * @Description:Brand业务层接口
 * @Date 2021/2/1 14:19
 *****/

public interface BrandService extends IService<Brand> {

    /***
     * Brand多条件分页查询
     * @param brand
     * @param page
     * @param size
     * @return
     */
    PageResult<Brand> findPage(Brand brand, int page, int size);

    /***
     * Brand分页查询
     * @param page
     * @param size
     * @return
     */
    PageResult<Brand> findPage(int page, int size);

    /***
     * Brand多条件搜索方法
     * @param brand
     * @return
     */
    List<Brand> findList(Brand brand);

    /***
     * 删除Brand
     * @param id
     */
    void delete(Long id);

    /***
     * 修改Brand数据
     * @param brand
     */
    void update(Brand brand);

    /***
     * 新增Brand
     * @param brand
     */
    void add(Brand brand);

    /**
     * 根据ID查询Brand
     * @param id
     * @return
     */
     Brand findById(Long id);

    /***
     * 查询所有Brand
     * @return
     */
    List<Brand> findAll();

    /*查询下拉列表*/
    public List<Map> selectOption();
}
