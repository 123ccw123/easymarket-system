package com.ccw.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ccw.dao.AddressMapper;
import com.ccw.user.pojo.Address;
import com.ccw.service.AddressService;
import com.ccw.entity.PageResult;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

/****
 * @Author:ujiuye
 * @Description:Address业务层接口实现类
 * @Date 2021/2/1 14:19
 *****/
@Service
public class AddressServiceImpl extends ServiceImpl<AddressMapper, Address> implements AddressService {


    /**
     * Address条件+分页查询
     * @param address 查询条件
     * @param page 页码
     * @param size 页大小
     * @return 分页结果
     */
    @Override
    public PageResult<Address> findPage(Address address, int page, int size){
         Page<Address> mypage = new Page<>(page, size);
        QueryWrapper<Address> queryWrapper = this.createQueryWrapper(address);
        IPage<Address> iPage = this.page(mypage, queryWrapper);
        return new PageResult<Address>(iPage.getTotal(),iPage.getRecords());
    }

    /**
     * Address分页查询
     * @param page
     * @param size
     * @return
     */
    @Override
    public PageResult<Address> findPage(int page, int size){
        Page<Address> mypage = new Page<>(page, size);
        IPage<Address> iPage = this.page(mypage, new QueryWrapper<Address>());

        return new PageResult<Address>(iPage.getTotal(),iPage.getRecords());
    }

    /**
     * Address条件查询
     * @param address
     * @return
     */
    @Override
    public List<Address> findList(Address address){
        //构建查询条件
        QueryWrapper<Address> queryWrapper = this.createQueryWrapper(address);
        //根据构建的条件查询数据
        return this.list(queryWrapper);
    }


    /**
     * Address构建查询对象
     * @param address
     * @return
     */
    public QueryWrapper<Address> createQueryWrapper(Address address){
        QueryWrapper<Address> queryWrapper = new QueryWrapper<>();
        if(address!=null){
            // 
            if(!StringUtils.isEmpty(address.getId())){
                 queryWrapper.eq("id",address.getId());
            }
            // 用户ID
            if(!StringUtils.isEmpty(address.getUserId())){
                 queryWrapper.eq("user_id",address.getUserId());
            }
            // 省
            if(!StringUtils.isEmpty(address.getProvinceId())){
                 queryWrapper.eq("province_id",address.getProvinceId());
            }
            // 市
            if(!StringUtils.isEmpty(address.getCityId())){
                 queryWrapper.eq("city_id",address.getCityId());
            }
            // 县/区
            if(!StringUtils.isEmpty(address.getTownId())){
                 queryWrapper.eq("town_id",address.getTownId());
            }
            // 手机
            if(!StringUtils.isEmpty(address.getMobile())){
                 queryWrapper.eq("mobile",address.getMobile());
            }
            // 详细地址
            if(!StringUtils.isEmpty(address.getAddress())){
                queryWrapper.like("address",address.getAddress());
            }
            // 联系人
            if(!StringUtils.isEmpty(address.getContact())){
                 queryWrapper.eq("contact",address.getContact());
            }
            // 是否是默认 1默认 0否
            if(!StringUtils.isEmpty(address.getIsDefault())){
                 queryWrapper.eq("is_default",address.getIsDefault());
            }
            // 备注
            if(!StringUtils.isEmpty(address.getNotes())){
                 queryWrapper.eq("notes",address.getNotes());
            }
            // 创建日期
            if(!StringUtils.isEmpty(address.getCreateDate())){
                 queryWrapper.eq("create_date",address.getCreateDate());
            }
            // 别名
            if(!StringUtils.isEmpty(address.getAlias())){
                 queryWrapper.eq("alias",address.getAlias());
            }
        }
        return queryWrapper;
    }

    /**
     * 删除
     * @param id
     */
    @Override
    public void delete(Long id){
        this.removeById(id);
    }

    /**
     * 修改Address
     * @param address
     */
    @Override
    public void update(Address address){
        this.updateById(address);
    }

    /**
     * 增加Address
     * @param address
     */
    @Override
    public void add(Address address){
        this.save(address);
    }

    /**
     * 根据ID查询Address
     * @param id
     * @return
     */
    @Override
    public Address findById(Long id){
        return  this.getById(id);
    }

    /**
     * 查询Address全部数据
     * @return
     */
    @Override
    public List<Address> findAll() {
        return this.list(new QueryWrapper<Address>());
    }

    /*根据用户名查询当前用户的收货地址*/
    @Override
    public List<Address> findListByUsername(String username) {
        QueryWrapper wrapper = new QueryWrapper<>();
        wrapper.eq("user_id",username);
        return this.list(wrapper);
    }
}
