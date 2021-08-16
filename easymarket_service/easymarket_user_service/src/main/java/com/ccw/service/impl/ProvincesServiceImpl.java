package com.ccw.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ccw.dao.ProvincesMapper;
import com.ccw.user.pojo.Provinces;
import com.ccw.service.ProvincesService;
import com.ccw.entity.PageResult;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

/****
 * @Author:ujiuye
 * @Description:Provinces业务层接口实现类
 * @Date 2021/2/1 14:19
 *****/
@Service
public class ProvincesServiceImpl extends ServiceImpl<ProvincesMapper, Provinces> implements ProvincesService {


    /**
     * Provinces条件+分页查询
     * @param provinces 查询条件
     * @param page 页码
     * @param size 页大小
     * @return 分页结果
     */
    @Override
    public PageResult<Provinces> findPage(Provinces provinces, int page, int size){
         Page<Provinces> mypage = new Page<>(page, size);
        QueryWrapper<Provinces> queryWrapper = this.createQueryWrapper(provinces);
        IPage<Provinces> iPage = this.page(mypage, queryWrapper);
        return new PageResult<Provinces>(iPage.getTotal(),iPage.getRecords());
    }

    /**
     * Provinces分页查询
     * @param page
     * @param size
     * @return
     */
    @Override
    public PageResult<Provinces> findPage(int page, int size){
        Page<Provinces> mypage = new Page<>(page, size);
        IPage<Provinces> iPage = this.page(mypage, new QueryWrapper<Provinces>());

        return new PageResult<Provinces>(iPage.getTotal(),iPage.getRecords());
    }

    /**
     * Provinces条件查询
     * @param provinces
     * @return
     */
    @Override
    public List<Provinces> findList(Provinces provinces){
        //构建查询条件
        QueryWrapper<Provinces> queryWrapper = this.createQueryWrapper(provinces);
        //根据构建的条件查询数据
        return this.list(queryWrapper);
    }


    /**
     * Provinces构建查询对象
     * @param provinces
     * @return
     */
    public QueryWrapper<Provinces> createQueryWrapper(Provinces provinces){
        QueryWrapper<Provinces> queryWrapper = new QueryWrapper<>();
        if(provinces!=null){
            // 唯一ID
            if(!StringUtils.isEmpty(provinces.getId())){
                 queryWrapper.eq("id",provinces.getId());
            }
            // 省份ID
            if(!StringUtils.isEmpty(provinces.getProvinceid())){
                 queryWrapper.eq("provinceid",provinces.getProvinceid());
            }
            // 省份名称
            if(!StringUtils.isEmpty(provinces.getProvince())){
                 queryWrapper.eq("province",provinces.getProvince());
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
     * 修改Provinces
     * @param provinces
     */
    @Override
    public void update(Provinces provinces){
        this.updateById(provinces);
    }

    /**
     * 增加Provinces
     * @param provinces
     */
    @Override
    public void add(Provinces provinces){
        this.save(provinces);
    }

    /**
     * 根据ID查询Provinces
     * @param id
     * @return
     */
    @Override
    public Provinces findById(Integer id){
        return  this.getById(id);
    }

    /**
     * 查询Provinces全部数据
     * @return
     */
    @Override
    public List<Provinces> findAll() {
        return this.list(new QueryWrapper<Provinces>());
    }
}
