package com.ccw.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ccw.dao.CitiesMapper;
import com.ccw.user.pojo.Cities;
import com.ccw.service.CitiesService;
import com.ccw.entity.PageResult;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

/****
 * @Author:ujiuye
 * @Description:Cities业务层接口实现类
 * @Date 2021/2/1 14:19
 *****/
@Service
public class CitiesServiceImpl extends ServiceImpl<CitiesMapper, Cities> implements CitiesService {


    /**
     * Cities条件+分页查询
     * @param cities 查询条件
     * @param page 页码
     * @param size 页大小
     * @return 分页结果
     */
    @Override
    public PageResult<Cities> findPage(Cities cities, int page, int size){
         Page<Cities> mypage = new Page<>(page, size);
        QueryWrapper<Cities> queryWrapper = this.createQueryWrapper(cities);
        IPage<Cities> iPage = this.page(mypage, queryWrapper);
        return new PageResult<Cities>(iPage.getTotal(),iPage.getRecords());
    }

    /**
     * Cities分页查询
     * @param page
     * @param size
     * @return
     */
    @Override
    public PageResult<Cities> findPage(int page, int size){
        Page<Cities> mypage = new Page<>(page, size);
        IPage<Cities> iPage = this.page(mypage, new QueryWrapper<Cities>());

        return new PageResult<Cities>(iPage.getTotal(),iPage.getRecords());
    }

    /**
     * Cities条件查询
     * @param cities
     * @return
     */
    @Override
    public List<Cities> findList(Cities cities){
        //构建查询条件
        QueryWrapper<Cities> queryWrapper = this.createQueryWrapper(cities);
        //根据构建的条件查询数据
        return this.list(queryWrapper);
    }


    /**
     * Cities构建查询对象
     * @param cities
     * @return
     */
    public QueryWrapper<Cities> createQueryWrapper(Cities cities){
        QueryWrapper<Cities> queryWrapper = new QueryWrapper<>();
        if(cities!=null){
            // 唯一ID
            if(!StringUtils.isEmpty(cities.getId())){
                 queryWrapper.eq("id",cities.getId());
            }
            // 城市ID
            if(!StringUtils.isEmpty(cities.getCityid())){
                 queryWrapper.eq("cityid",cities.getCityid());
            }
            // 城市名称
            if(!StringUtils.isEmpty(cities.getCity())){
                 queryWrapper.eq("city",cities.getCity());
            }
            // 省份ID
            if(!StringUtils.isEmpty(cities.getProvinceid())){
                 queryWrapper.eq("provinceid",cities.getProvinceid());
            }
        }
        return queryWrapper;
    }

    /**
     * 删除
     * @param id
     */
    @Override
    public void delete(Integer id){
        this.removeById(id);
    }

    /**
     * 修改Cities
     * @param cities
     */
    @Override
    public void update(Cities cities){
        this.updateById(cities);
    }

    /**
     * 增加Cities
     * @param cities
     */
    @Override
    public void add(Cities cities){
        this.save(cities);
    }

    /**
     * 根据ID查询Cities
     * @param id
     * @return
     */
    @Override
    public Cities findById(Integer id){
        return  this.getById(id);
    }

    /**
     * 查询Cities全部数据
     * @return
     */
    @Override
    public List<Cities> findAll() {
        return this.list(new QueryWrapper<Cities>());
    }
}
