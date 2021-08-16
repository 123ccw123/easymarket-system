package com.ccw.sellersgoods.service.Impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ccw.sellersgoods.dao.SpecificationOptionMapper;
import com.ccw.sellersgoods.dao.TypeTemplateMapper;
import com.ccw.sellersgoods.pojo.SpecificationOption;
import com.ccw.sellersgoods.pojo.TypeTemplate;
import com.ccw.sellersgoods.service.TypeTemplateService;
import com.ccw.entity.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Map;

/****
 * @Author:ujiuye
 * @Description:TypeTemplate业务层接口实现类
 * @Date 2021/2/1 14:19
 *****/
@Service
public class TypeTemplateServiceImpl extends ServiceImpl<TypeTemplateMapper, TypeTemplate> implements TypeTemplateService {


    @Autowired
    private SpecificationOptionMapper specificationOptionMapper;
    /**
     * TypeTemplate条件+分页查询
     * @param typeTemplate 查询条件
     * @param page 页码
     * @param size 页大小
     * @return 分页结果
     */
    @Override
    public PageResult<TypeTemplate> findPage(TypeTemplate typeTemplate, int page, int size){
         Page<TypeTemplate> mypage = new Page<>(page, size);
        QueryWrapper<TypeTemplate> queryWrapper = this.createQueryWrapper(typeTemplate);
        IPage<TypeTemplate> iPage = this.page(mypage, queryWrapper);
        return new PageResult<TypeTemplate>(iPage.getTotal(),iPage.getRecords());
    }

    /**
     * TypeTemplate分页查询
     * @param page
     * @param size
     * @return
     */
    @Override
    public PageResult<TypeTemplate> findPage(int page, int size){
        Page<TypeTemplate> mypage = new Page<>(page, size);
        IPage<TypeTemplate> iPage = this.page(mypage, new QueryWrapper<TypeTemplate>());

        return new PageResult<TypeTemplate>(iPage.getTotal(),iPage.getRecords());
    }

    /**
     * TypeTemplate条件查询
     * @param typeTemplate
     * @return
     */
    @Override
    public List<TypeTemplate> findList(TypeTemplate typeTemplate){
        //构建查询条件
        QueryWrapper<TypeTemplate> queryWrapper = this.createQueryWrapper(typeTemplate);
        //根据构建的条件查询数据
        return this.list(queryWrapper);
    }


    /**
     * TypeTemplate构建查询对象
     * @param typeTemplate
     * @return
     */
    public QueryWrapper<TypeTemplate> createQueryWrapper(TypeTemplate typeTemplate){
        QueryWrapper<TypeTemplate> queryWrapper = new QueryWrapper<>();
        if(typeTemplate!=null){
            // 
            if(!StringUtils.isEmpty(typeTemplate.getId())){
                 queryWrapper.eq("id",typeTemplate.getId());
            }
            // 模板名称
            if(!StringUtils.isEmpty(typeTemplate.getName())){
                queryWrapper.like("name",typeTemplate.getName());
            }
            // 关联规格
            if(!StringUtils.isEmpty(typeTemplate.getSpecIds())){
                 queryWrapper.eq("spec_ids",typeTemplate.getSpecIds());
            }
            // 关联品牌
            if(!StringUtils.isEmpty(typeTemplate.getBrandIds())){
                 queryWrapper.eq("brand_ids",typeTemplate.getBrandIds());
            }
            // 自定义属性
            if(!StringUtils.isEmpty(typeTemplate.getCustomAttributeItems())){
                 queryWrapper.eq("custom_attribute_items",typeTemplate.getCustomAttributeItems());
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
     * 修改TypeTemplate
     * @param typeTemplate
     */
    @Override
    public void update(TypeTemplate typeTemplate){
        this.updateById(typeTemplate);
    }

    /**
     * 增加TypeTemplate
     * @param typeTemplate
     */
    @Override
    public void add(TypeTemplate typeTemplate){
        this.save(typeTemplate);
    }

    /**
     * 根据ID查询TypeTemplate
     * @param id
     * @return
     */
    @Override
    public TypeTemplate findById(Long id){
        return  this.getById(id);
    }

    /**
     * 查询TypeTemplate全部数据
     * @return
     */
    @Override
    public List<TypeTemplate> findAll() {
        return this.list(new QueryWrapper<TypeTemplate>());
    }

    @Override
    public List<Map> finSpecList(Long id) {
        //1、根据模板id查询到对应的模板对象
        TypeTemplate typeTemplate = this.findById(id);
        //2、判断模板对象是否为空，通过模板对象得到相应的规格JSON字符串
        List<Map> maps = null;
        if (!StringUtils.isEmpty(typeTemplate)){
            //3、将JSON字符串转化为JSON对象
             maps = JSON.parseArray(typeTemplate.getSpecIds(), Map.class);
            //4、遍历规格名称集合
            for (Map map : maps) {
                //5、根据规格id获取到对应的规格选项
                Long specId = new Long ((Integer)map.get("id"));
                QueryWrapper<SpecificationOption> queryWrapper = new QueryWrapper<>();
                queryWrapper.eq("spec_id",specId);
                List<SpecificationOption> select = specificationOptionMapper.selectList(queryWrapper);
                //6、将规格选项设置到JSON对象中
                map.put("option",select);
            }
        }
        return maps;
    }
}
