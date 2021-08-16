package com.ccw.sellersgoods.service.Impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ccw.sellersgoods.dao.*;
import com.ccw.sellersgoods.group.GoodsEntity;
import com.ccw.sellersgoods.pojo.*;
import com.ccw.sellersgoods.service.GoodsService;
import com.ccw.entity.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.List;
import java.util.Map;

/****
 * @Author:ujiuye
 * @Description:Goods业务层接口实现类
 * @Date 2021/2/1 14:19
 *****/
@Service
@Transactional //开启注解式事务管理，添加事务回滚
public class GoodsServiceImpl extends ServiceImpl<GoodsMapper, Goods> implements GoodsService {


    @Autowired
    private GoodsMapper goodsMapper;
    @Autowired
    private GoodsDescMapper goodsDescMapper;
    @Autowired
    private ItemMapper itemMapper;
    @Autowired
    private ItemCatMapper itemCatMapper;
    @Autowired
    private BrandMapper brandMapper;
    /**
     * Goods条件+分页查询
     * @param goods 查询条件
     * @param page 页码
     * @param size 页大小
     * @return 分页结果
     */
    @Override
    public PageResult<Goods> findPage(Goods goods, int page, int size){
         Page<Goods> mypage = new Page<>(page, size);
        QueryWrapper<Goods> queryWrapper = this.createQueryWrapper(goods);
        IPage<Goods> iPage = this.page(mypage, queryWrapper);
        return new PageResult<Goods>(iPage.getTotal(),iPage.getRecords());
    }

    /**
     * Goods分页查询
     * @param page
     * @param size
     * @return
     */
    @Override
    public PageResult<Goods> findPage(int page, int size){
        Page<Goods> mypage = new Page<>(page, size);
        IPage<Goods> iPage = this.page(mypage, new QueryWrapper<Goods>());

        return new PageResult<Goods>(iPage.getTotal(),iPage.getRecords());
    }

    /**
     * Goods条件查询
     * @param goods
     * @return
     */
    @Override
    public List<Goods> findList(Goods goods){
        //构建查询条件
        QueryWrapper<Goods> queryWrapper = this.createQueryWrapper(goods);
        //根据构建的条件查询数据
        return this.list(queryWrapper);
    }


    /**
     * Goods构建查询对象
     * @param goods
     * @return
     */
    public QueryWrapper<Goods> createQueryWrapper(Goods goods){
        QueryWrapper<Goods> queryWrapper = new QueryWrapper<>();
        if(goods!=null){
            // 主键
            if(!StringUtils.isEmpty(goods.getId())){
                 queryWrapper.eq("id",goods.getId());
            }
            // 商家ID
            if(!StringUtils.isEmpty(goods.getSellerId())){
                 queryWrapper.eq("seller_id",goods.getSellerId());
            }
            // SPU名
            if(!StringUtils.isEmpty(goods.getGoodsName())){
                 queryWrapper.eq("goods_name",goods.getGoodsName());
            }
            // 默认SKU
            if(!StringUtils.isEmpty(goods.getDefaultItemId())){
                 queryWrapper.eq("default_item_id",goods.getDefaultItemId());
            }
            // 状态
            if(!StringUtils.isEmpty(goods.getAuditStatus())){
                 queryWrapper.eq("audit_status",goods.getAuditStatus());
            }
            // 是否上架
            if(!StringUtils.isEmpty(goods.getIsMarketable())){
                 queryWrapper.eq("is_marketable",goods.getIsMarketable());
            }
            // 品牌
            if(!StringUtils.isEmpty(goods.getBrandId())){
                 queryWrapper.eq("brand_id",goods.getBrandId());
            }
            // 副标题
            if(!StringUtils.isEmpty(goods.getCaption())){
                 queryWrapper.eq("caption",goods.getCaption());
            }
            // 一级类目
            if(!StringUtils.isEmpty(goods.getCategory1Id())){
                 queryWrapper.eq("category1_id",goods.getCategory1Id());
            }
            // 二级类目
            if(!StringUtils.isEmpty(goods.getCategory2Id())){
                 queryWrapper.eq("category2_id",goods.getCategory2Id());
            }
            // 三级类目
            if(!StringUtils.isEmpty(goods.getCategory3Id())){
                 queryWrapper.eq("category3_id",goods.getCategory3Id());
            }
            // 小图
            if(!StringUtils.isEmpty(goods.getSmallPic())){
                 queryWrapper.eq("small_pic",goods.getSmallPic());
            }
            // 商城价
            if(!StringUtils.isEmpty(goods.getPrice())){
                 queryWrapper.eq("price",goods.getPrice());
            }
            // 分类模板ID
            if(!StringUtils.isEmpty(goods.getTypeTemplateId())){
                 queryWrapper.eq("type_template_id",goods.getTypeTemplateId());
            }
            // 是否启用规格
            if(!StringUtils.isEmpty(goods.getIsEnableSpec())){
                 queryWrapper.eq("is_enable_spec",goods.getIsEnableSpec());
            }
            // 是否删除
            if(!StringUtils.isEmpty(goods.getIsDelete())){
                 queryWrapper.eq("is_delete",goods.getIsDelete());
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
        //查询商品信息
        Goods goods = goodsMapper.selectById(id);
        //判断该商品是否上架
        if (("1").equals(goods.getIsMarketable())) {
            throw new RuntimeException("该商品还处于上架状态，不能删除！");
        }
        //将商品删除信息修改为逻辑删除将删除位设置为1
        goods.setIsDelete("1");
        //执行操作
        goodsMapper.updateById(goods);
    }

    /**
     * 修改Goods
     * @param goodsEntity
     */
    @Override
    public void update(GoodsEntity goodsEntity){
        //设置审核状态 未审核
        goodsEntity.getGoods().setAuditStatus("1");
        //修改SPU信息
        goodsMapper.updateById(goodsEntity.getGoods());
        //修改扩展表信息
        goodsDescMapper.updateById(goodsEntity.getGoodsDesc());
        //根据SKU_id修改SKU信息
        QueryWrapper<Item> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("goods_id",goodsEntity.getGoods().getId());
        //删除所有sku信息，最后再重新添加
        itemMapper.delete(queryWrapper);
        //保存修改过的信息
        this.addMessage(goodsEntity);
    }

    /**
     * 增加Goods
     * @param goodsEntity
     */
    @Override
    public void add(GoodsEntity goodsEntity){
        //1、设置审核状态
        goodsEntity.getGoods().setAuditStatus("0");
        this.save(goodsEntity.getGoods());
        //2、保存SPU的信息
        goodsMapper.insert(goodsEntity.getGoods());
        //3、根据SPU的id转给扩展表，因为他们的表关系为一对一
        goodsEntity.getGoodsDesc().setGoodsId(goodsEntity.getGoods().getId());
        //4、保存扩展表的信息
        goodsDescMapper.insert(goodsEntity.getGoodsDesc());
        //5、保存SKU的信息  spu+规格
        //判断是否启用规格
        this.addMessage(goodsEntity);
    }

    private void addMessage(GoodsEntity goodsEntity){
        if (("1").equals(goodsEntity.getGoods().getIsEnableSpec())){
            if (!CollectionUtils.isEmpty(goodsEntity.getItemList())) {
                for (Item item : goodsEntity.getItemList()) {
                    //获取spu名称
                    String name = goodsEntity.getGoods().getGoodsName();
                    Map<String,String> specNmae = JSON.parseObject(item.getSpec(),Map.class);
                    for (String s : specNmae.keySet()) {
                        name += specNmae.get(s) + " ";
                    }
                    item.setTitle(name);
                    this.setGoodsItemValue(goodsEntity,item);
                    itemMapper.insert(item);
                }
            }
        }else {
            Item item = new Item();
            item.setTitle(goodsEntity.getGoods().getGoodsName());
            item.setPrice(goodsEntity.getGoods().getPrice());
            item.setStatus("1");
            item.setIsDefault("1");
            item.setNum(9999);
            item.setSpec("{}");
            this.setGoodsItemValue(goodsEntity,item);
            itemMapper.insert(item);
        }
    }
    private void setGoodsItemValue(GoodsEntity goodsEntity,Item item){
        //创建时间
        item.setCreateTime(new Date());
        //更新事件
        item.setUpdateTime(new Date());
        //商品id
        item.setGoodsId(goodsEntity.getGoods().getId());
        //商家id
        item.setSellerId(goodsEntity.getGoods().getSellerId());
        //分类id第三级
        item.setCategoryId(goodsEntity.getGoods().getCategory3Id());

        //分类名称
        ItemCat itemCat = itemCatMapper.selectById(item.getCategoryId());
        item.setCategory(itemCat.getName());
        //品牌名称
        Brand brand = brandMapper.selectById(goodsEntity.getGoods().getBrandId());
        item.setBrand(brand.getName());
        List<Map> list = JSON.parseArray(goodsEntity.getGoodsDesc().getItemImages(), Map.class);
        if (CollectionUtils.isEmpty(list)) {
            item.setImage((String)list.get(0).get("url")); //图片路径
        }
    }
    /**
     * 根据ID查询Goods
     * @param id
     * @return
     */
    @Override
    public GoodsEntity findById(Long id){
        //查询SPU信息
        Goods goods = goodsMapper.selectById(id);
        //查询扩展表信息
        GoodsDesc goodsDesc = goodsDescMapper.selectById(id);
        //查询SKU信息
        QueryWrapper<Item> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("goods_id",id);
        List<Item> items = itemMapper.selectList(queryWrapper);
        //设置到综合实体类
        GoodsEntity goodsEntity = new GoodsEntity();
        goodsEntity.setGoods(goods);
        goodsEntity.setGoodsDesc(goodsDesc);
        goodsEntity.setItemList(items);
        return  goodsEntity;
    }

    /**
     * 查询Goods全部数据
     * @return
     */
    @Override
    public List<Goods> findAll() {
        return this.list(new QueryWrapper<Goods>());
    }

    /*对商品进行审核并进行上下架*/
    @Override
    public void audit(Long id) {
        //查询商品信息
        Goods goods = goodsMapper.selectById(id);
        //判断商品是否存在
        if (("1").equals(goods.getAuditStatus())) {
            throw new RuntimeException("该商品已经被删除，请重新添加数据！");
        }
        //设置审核状态 通过、驳回、或者关闭
        goods.setAuditStatus("1");
        //设置上下架状态
        goods.setIsMarketable("1");
        //修改商品状态
        goodsMapper.updateById(goods);
    }
}
