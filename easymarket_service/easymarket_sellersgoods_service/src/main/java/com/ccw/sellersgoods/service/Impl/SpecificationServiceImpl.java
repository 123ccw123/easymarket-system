package com.ccw.sellersgoods.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ccw.sellersgoods.dao.SpecificationMapper;
import com.ccw.sellersgoods.dao.SpecificationOptionMapper;
import com.ccw.sellersgoods.group.SpecEntity;
import com.ccw.sellersgoods.pojo.Specification;
import com.ccw.sellersgoods.pojo.SpecificationOption;
import com.ccw.sellersgoods.service.SpecificationService;
import com.ccw.entity.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Reference;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Map;

/****
 * @Author:ujiuye
 * @Description:Specification业务层接口实现类
 * @Date 2021/2/1 14:19
 *****/
@Service
@Transactional  //注解式事务，自动添加事务回滚
public class SpecificationServiceImpl extends ServiceImpl<SpecificationMapper, Specification> implements SpecificationService {
    @Autowired
    private SpecificationMapper specificationMapper;

    @Autowired
    private SpecificationOptionMapper specificationOptionMapper;
    /**
     * Specification条件+分页查询
     * @param specification 查询条件
     * @param page 页码
     * @param size 页大小
     * @return 分页结果
     */
    @Override
    public PageResult<Specification> findPage(Specification specification, int page, int size){
         Page<Specification> mypage = new Page<>(page, size);
        QueryWrapper<Specification> queryWrapper = this.createQueryWrapper(specification);
        IPage<Specification> iPage = this.page(mypage, queryWrapper);
        return new PageResult<Specification>(iPage.getTotal(),iPage.getRecords());
    }

    /**
     * Specification分页查询
     * @param page
     * @param size
     * @return
     */
    @Override
    public PageResult<Specification> findPage(int page, int size){
        Page<Specification> mypage = new Page<>(page, size);
        IPage<Specification> iPage = this.page(mypage, new QueryWrapper<Specification>());

        return new PageResult<Specification>(iPage.getTotal(),iPage.getRecords());
    }

    /**
     * Specification条件查询
     * @param specification
     * @return
     */
    @Override
    public List<Specification> findList(Specification specification){
        //构建查询条件
        QueryWrapper<Specification> queryWrapper = this.createQueryWrapper(specification);
        //根据构建的条件查询数据
        return this.list(queryWrapper);
    }


    /**
     * Specification构建查询对象
     * @param specification
     * @return
     */
    public QueryWrapper<Specification> createQueryWrapper(Specification specification){
        QueryWrapper<Specification> queryWrapper = new QueryWrapper<>();
        if(specification!=null){
            // 主键
            if(!StringUtils.isEmpty(specification.getId())){
                 queryWrapper.eq("id",specification.getId());
            }
            // 名称
            if(!StringUtils.isEmpty(specification.getSpecName())){
                 queryWrapper.eq("spec_name",specification.getSpecName());
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
        //根据id删除规格名称
        this.removeById(id);
        //根据id删除阈值对应的规格选项
        QueryWrapper<SpecificationOption>queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("spec_id",id);
        //执行删除
        specificationOptionMapper.delete(queryWrapper);
    }

    /**
     * 修改Specification
     * @param specEntity
     */
    @Override
    public void update(SpecEntity specEntity){
        //根据规格名称修改对象
        this.updateById(specEntity.getSpecification());
        //根据id修改选项集合
        QueryWrapper<SpecificationOption> optionQueryWrapper = new QueryWrapper<>();
        optionQueryWrapper.eq("spec_id",specEntity.getSpecification().getId());
        //执行删除操作
        specificationOptionMapper.delete(optionQueryWrapper);
        //重新插入规格选项
        if (!CollectionUtils.isEmpty(specEntity.getSpecificationOptionList())) {
            for (SpecificationOption specificationOption : specEntity.getSpecificationOptionList()) {
                //设置规格名称的id
                specificationOption.setSpecId(specEntity.getSpecification().getId());
                //重新添加规格选项
                specificationOptionMapper.insert(specificationOption);
            }
        }

    }

    /**
     * 增加Specification
     * @param specification
     */


    /**
     * 根据ID查询Specification 测试规格名称查询
     * @param id
     * @return
     */
    @Override
    public SpecEntity findById(Long id){
        Specification specification = specificationMapper.selectById(id);
        QueryWrapper<SpecificationOption> wrapper = new QueryWrapper();
        wrapper.eq("spec_id",id);
        List<SpecificationOption> specificationOptionList = specificationOptionMapper.selectList(wrapper);
        SpecEntity specEntity = new SpecEntity();
        specEntity.setSpecification(specification);
        specEntity.setSpecificationOptionList(specificationOptionList);
        return specEntity;
    }

    /**
     * 查询Specification全部数据
     * @return
     */
    @Override
    public List<Specification> findAll() {
        return this.list(new QueryWrapper<Specification>());
    }

    /*下拉列表查询*/
    @Override
    public List<Map> selectOption() {
        return specificationMapper.selectOption();
    }

    /*新增specificatio*/
    /*@Override
    public void add(Specification specification){
        this.save(specification);
    }*/
    @Override
    public void add(SpecEntity specEntity) {
        //保存规格名称
        this.save(specEntity.getSpecification());
        //获取规格名称ID
        if (!CollectionUtils.isEmpty(specEntity.getSpecificationOptionList())){
            for (SpecificationOption specificationOption : specEntity.getSpecificationOptionList()) {
                //向规格选项中添加得到的id
                specificationOption.setSpecId(specEntity.getSpecification().getId());
                //保存规格选项
                specificationOptionMapper.insert(specificationOption);
            }
        }
    }
}
