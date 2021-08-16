package com.ccw.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ccw.user.pojo.Cities;
import com.ccw.entity.PageResult;

import java.util.List;

/****
 * @Author:ujiuye
 * @Description:Cities业务层接口
 * @Date 2021/2/1 14:19
 *****/

public interface CitiesService extends IService<Cities> {

    /***
     * Cities多条件分页查询
     * @param cities
     * @param page
     * @param size
     * @return
     */
    PageResult<Cities> findPage(Cities cities, int page, int size);

    /***
     * Cities分页查询
     * @param page
     * @param size
     * @return
     */
    PageResult<Cities> findPage(int page, int size);

    /***
     * Cities多条件搜索方法
     * @param cities
     * @return
     */
    List<Cities> findList(Cities cities);

    /***
     * 删除Cities
     * @param id
     */
    void delete(Integer id);

    /***
     * 修改Cities数据
     * @param cities
     */
    void update(Cities cities);

    /***
     * 新增Cities
     * @param cities
     */
    void add(Cities cities);

    /**
     * 根据ID查询Cities
     * @param id
     * @return
     */
     Cities findById(Integer id);

    /***
     * 查询所有Cities
     * @return
     */
    List<Cities> findAll();
}
