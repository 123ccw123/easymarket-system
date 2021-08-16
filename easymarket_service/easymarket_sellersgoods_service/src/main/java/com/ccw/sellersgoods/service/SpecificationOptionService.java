package com.ccw.sellersgoods.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ccw.sellersgoods.pojo.SpecificationOption;
import com.ccw.entity.PageResult;

import java.util.List;

/****
 * @Author:ujiuye
 * @Description:SpecificationOption业务层接口
 * @Date 2021/2/1 14:19
 *****/

public interface SpecificationOptionService extends IService<SpecificationOption> {

    /***
     * SpecificationOption多条件分页查询
     * @param specificationOption
     * @param page
     * @param size
     * @return
     */
    PageResult<SpecificationOption> findPage(SpecificationOption specificationOption, int page, int size);

    /***
     * SpecificationOption分页查询
     * @param page
     * @param size
     * @return
     */
    PageResult<SpecificationOption> findPage(int page, int size);

    /***
     * SpecificationOption多条件搜索方法
     * @param specificationOption
     * @return
     */
    List<SpecificationOption> findList(SpecificationOption specificationOption);

    /***
     * 删除SpecificationOption
     * @param id
     */
    void delete(Long id);

    /***
     * 修改SpecificationOption数据
     * @param specificationOption
     */
    void update(SpecificationOption specificationOption);

    /***
     * 新增SpecificationOption
     * @param specificationOption
     */
    void add(SpecificationOption specificationOption);

    /**
     * 根据ID查询SpecificationOption
     * @param id
     * @return
     */
     SpecificationOption findById(Long id);

    /***
     * 查询所有SpecificationOption
     * @return
     */
    List<SpecificationOption> findAll();
}
