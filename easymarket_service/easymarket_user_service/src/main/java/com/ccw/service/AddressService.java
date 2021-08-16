package com.ccw.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ccw.user.pojo.Address;
import com.ccw.entity.PageResult;

import java.util.List;

/****
 * @Author:ujiuye
 * @Description:Address业务层接口
 * @Date 2021/2/1 14:19
 *****/

public interface AddressService extends IService<Address> {

    /***
     * Address多条件分页查询
     * @param address
     * @param page
     * @param size
     * @return
     */
    PageResult<Address> findPage(Address address, int page, int size);

    /***
     * Address分页查询
     * @param page
     * @param size
     * @return
     */
    PageResult<Address> findPage(int page, int size);

    /***
     * Address多条件搜索方法
     * @param address
     * @return
     */
    List<Address> findList(Address address);

    /***
     * 删除Address
     * @param id
     */
    void delete(Long id);

    /***
     * 修改Address数据
     * @param address
     */
    void update(Address address);

    /***
     * 新增Address
     * @param address
     */
    void add(Address address);

    /**
     * 根据ID查询Address
     * @param id
     * @return
     */
     Address findById(Long id);

    /***
     * 查询所有Address
     * @return
     */
    List<Address> findAll();

    /**
     * 根据用户名查询用户收货地址
     * @param username
     * @return
     */
    public List<Address> findListByUsername(String username);
}
