package com.ccw.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ccw.user.pojo.Areas;
import com.ccw.entity.PageResult;

import java.util.List;

/****
 * @Author:ujiuye
 * @Description:Areas业务层接口
 * @Date 2021/2/1 14:19
 *****/

public interface AreasService extends IService<Areas> {

    /***
     * Areas多条件分页查询
     * @param areas
     * @param page
     * @param size
     * @return
     */
    PageResult<Areas> findPage(Areas areas, int page, int size);

    /***
     * Areas分页查询
     * @param page
     * @param size
     * @return
     */
    PageResult<Areas> findPage(int page, int size);

    /***
     * Areas多条件搜索方法
     * @param areas
     * @return
     */
    List<Areas> findList(Areas areas);

    /***
     * 删除Areas
     * @param id
     */
    void delete(Integer id);

    /***
     * 修改Areas数据
     * @param areas
     */
    void update(Areas areas);

    /***
     * 新增Areas
     * @param areas
     */
    void add(Areas areas);

    /**
     * 根据ID查询Areas
     * @param id
     * @return
     */
     Areas findById(Integer id);

    /***
     * 查询所有Areas
     * @return
     */
    List<Areas> findAll();
}
