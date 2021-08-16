package com.ccw.sellersgoods.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ccw.sellersgoods.dao.GoodsDescMapper;
import com.ccw.sellersgoods.pojo.GoodsDesc;
import com.ccw.sellersgoods.service.GoodsDescService;
import com.ccw.entity.PageResult;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

/****
 * @Author:ujiuye
 * @Description:GoodsDesc业务层接口实现类
 * @Date 2021/2/1 14:19
 *****/
@Service
public class GoodsDescServiceImpl extends ServiceImpl<GoodsDescMapper, GoodsDesc> implements GoodsDescService {


    /**
     * GoodsDesc条件+分页查询
     * @param goodsDesc 查询条件
     * @param page 页码
     * @param size 页大小
     * @return 分页结果
     */
    @Override
    public PageResult<GoodsDesc> findPage(GoodsDesc goodsDesc, int page, int size){
         Page<GoodsDesc> mypage = new Page<>(page, size);
        QueryWrapper<GoodsDesc> queryWrapper = this.createQueryWrapper(goodsDesc);
        IPage<GoodsDesc> iPage = this.page(mypage, queryWrapper);
        return new PageResult<GoodsDesc>(iPage.getTotal(),iPage.getRecords());
    }

    /**
     * GoodsDesc分页查询
     * @param page
     * @param size
     * @return
     */
    @Override
    public PageResult<GoodsDesc> findPage(int page, int size){
        Page<GoodsDesc> mypage = new Page<>(page, size);
        IPage<GoodsDesc> iPage = this.page(mypage, new QueryWrapper<GoodsDesc>());

        return new PageResult<GoodsDesc>(iPage.getTotal(),iPage.getRecords());
    }

    /**
     * GoodsDesc条件查询
     * @param goodsDesc
     * @return
     */
    @Override
    public List<GoodsDesc> findList(GoodsDesc goodsDesc){
        //构建查询条件
        QueryWrapper<GoodsDesc> queryWrapper = this.createQueryWrapper(goodsDesc);
        //根据构建的条件查询数据
        return this.list(queryWrapper);
    }


    /**
     * GoodsDesc构建查询对象
     * @param goodsDesc
     * @return
     */
    public QueryWrapper<GoodsDesc> createQueryWrapper(GoodsDesc goodsDesc){
        QueryWrapper<GoodsDesc> queryWrapper = new QueryWrapper<>();
        if(goodsDesc!=null){
            // SPU_ID
            if(!StringUtils.isEmpty(goodsDesc.getGoodsId())){
                 queryWrapper.eq("goods_id",goodsDesc.getGoodsId());
            }
            // 描述
            if(!StringUtils.isEmpty(goodsDesc.getIntroduction())){
                 queryWrapper.eq("introduction",goodsDesc.getIntroduction());
            }
            // 规格结果集，所有规格，包含isSelected
            if(!StringUtils.isEmpty(goodsDesc.getSpecificationItems())){
                 queryWrapper.eq("specification_items",goodsDesc.getSpecificationItems());
            }
            // 自定义属性（参数结果）
            if(!StringUtils.isEmpty(goodsDesc.getCustomAttributeItems())){
                 queryWrapper.eq("custom_attribute_items",goodsDesc.getCustomAttributeItems());
            }
            // 
            if(!StringUtils.isEmpty(goodsDesc.getItemImages())){
                 queryWrapper.eq("item_images",goodsDesc.getItemImages());
            }
            // 包装列表
            if(!StringUtils.isEmpty(goodsDesc.getPackageList())){
                 queryWrapper.eq("package_list",goodsDesc.getPackageList());
            }
            // 售后服务
            if(!StringUtils.isEmpty(goodsDesc.getSaleService())){
                 queryWrapper.eq("sale_service",goodsDesc.getSaleService());
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
     * 修改GoodsDesc
     * @param goodsDesc
     */
    @Override
    public void update(GoodsDesc goodsDesc){
        this.updateById(goodsDesc);
    }

    /**
     * 增加GoodsDesc
     * @param goodsDesc
     */
    @Override
    public void add(GoodsDesc goodsDesc){
        this.save(goodsDesc);
    }

    /**
     * 根据ID查询GoodsDesc
     * @param id
     * @return
     */
    @Override
    public GoodsDesc findById(Long id){
        return  this.getById(id);
    }

    /**
     * 查询GoodsDesc全部数据
     * @return
     */
    @Override
    public List<GoodsDesc> findAll() {
        return this.list(new QueryWrapper<GoodsDesc>());
    }
}
