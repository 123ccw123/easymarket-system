package com.ccw.sellersgoods.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ccw.sellersgoods.dao.SpecificationOptionMapper;
import com.ccw.sellersgoods.pojo.SpecificationOption;
import com.ccw.sellersgoods.service.SpecificationOptionService;
import com.ccw.entity.PageResult;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

/****
 * @Author:ujiuye
 * @Description:SpecificationOption业务层接口实现类
 * @Date 2021/2/1 14:19
 *****/
@Service
public class SpecificationOptionServiceImpl extends ServiceImpl<SpecificationOptionMapper, SpecificationOption> implements SpecificationOptionService {


    /**
     * SpecificationOption条件+分页查询
     * @param specificationOption 查询条件
     * @param page 页码
     * @param size 页大小
     * @return 分页结果
     */
    @Override
    public PageResult<SpecificationOption> findPage(SpecificationOption specificationOption, int page, int size){
         Page<SpecificationOption> mypage = new Page<>(page, size);
        QueryWrapper<SpecificationOption> queryWrapper = this.createQueryWrapper(specificationOption);
        IPage<SpecificationOption> iPage = this.page(mypage, queryWrapper);
        return new PageResult<SpecificationOption>(iPage.getTotal(),iPage.getRecords());
    }

    /**
     * SpecificationOption分页查询
     * @param page
     * @param size
     * @return
     */
    @Override
    public PageResult<SpecificationOption> findPage(int page, int size){
        Page<SpecificationOption> mypage = new Page<>(page, size);
        IPage<SpecificationOption> iPage = this.page(mypage, new QueryWrapper<SpecificationOption>());

        return new PageResult<SpecificationOption>(iPage.getTotal(),iPage.getRecords());
    }

    /**
     * SpecificationOption条件查询
     * @param specificationOption
     * @return
     */
    @Override
    public List<SpecificationOption> findList(SpecificationOption specificationOption){
        //构建查询条件
        QueryWrapper<SpecificationOption> queryWrapper = this.createQueryWrapper(specificationOption);
        //根据构建的条件查询数据
        return this.list(queryWrapper);
    }


    /**
     * SpecificationOption构建查询对象
     * @param specificationOption
     * @return
     */
    public QueryWrapper<SpecificationOption> createQueryWrapper(SpecificationOption specificationOption){
        QueryWrapper<SpecificationOption> queryWrapper = new QueryWrapper<>();
        if(specificationOption!=null){
            // 规格项ID
            if(!StringUtils.isEmpty(specificationOption.getId())){
                 queryWrapper.eq("id",specificationOption.getId());
            }
            // 规格项名称
            if(!StringUtils.isEmpty(specificationOption.getOptionName())){
                 queryWrapper.eq("option_name",specificationOption.getOptionName());
            }
            // 规格ID
            if(!StringUtils.isEmpty(specificationOption.getSpecId())){
                 queryWrapper.eq("spec_id",specificationOption.getSpecId());
            }
            // 排序值
            if(!StringUtils.isEmpty(specificationOption.getOrders())){
                 queryWrapper.eq("orders",specificationOption.getOrders());
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
     * 修改SpecificationOption
     * @param specificationOption
     */
    @Override
    public void update(SpecificationOption specificationOption){
        this.updateById(specificationOption);
    }

    /**
     * 增加SpecificationOption
     * @param specificationOption
     */
    @Override
    public void add(SpecificationOption specificationOption){
        this.save(specificationOption);
    }

    /**
     * 根据ID查询SpecificationOption
     * @param id
     * @return
     */
    @Override
    public SpecificationOption findById(Long id){
        return  this.getById(id);
    }

    /**
     * 查询SpecificationOption全部数据
     * @return
     */
    @Override
    public List<SpecificationOption> findAll() {
        return this.list(new QueryWrapper<SpecificationOption>());
    }
}
