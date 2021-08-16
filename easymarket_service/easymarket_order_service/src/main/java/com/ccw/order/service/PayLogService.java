package com.ccw.order.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ccw.order.pojo.PayLog;
import com.ccw.entity.PageResult;

import java.util.List;

/****
 * @Author:ujiuye
 * @Description:PayLog业务层接口
 * @Date 2021/2/1 14:19
 *****/

public interface PayLogService extends IService<PayLog> {

    /***
     * PayLog多条件分页查询
     * @param payLog
     * @param page
     * @param size
     * @return
     */
    PageResult<PayLog> findPage(PayLog payLog, int page, int size);

    /***
     * PayLog分页查询
     * @param page
     * @param size
     * @return
     */
    PageResult<PayLog> findPage(int page, int size);

    /***
     * PayLog多条件搜索方法
     * @param payLog
     * @return
     */
    List<PayLog> findList(PayLog payLog);

    /***
     * 删除PayLog
     * @param id
     */
    void delete(String id);

    /***
     * 修改PayLog数据
     * @param payLog
     */
    void update(PayLog payLog);

    /***
     * 新增PayLog
     * @param payLog
     */
    void add(PayLog payLog);

    /**
     * 根据ID查询PayLog
     * @param id
     * @return
     */
     PayLog findById(String id);

    /***
     * 查询所有PayLog
     * @return
     */
    List<PayLog> findAll();
}
