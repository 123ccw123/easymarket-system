package com.ccw.sellersgoods.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ccw.order.group.Cart;
import com.ccw.order.pojo.OrderItem;
import com.ccw.sellersgoods.dao.ItemMapper;
import com.ccw.sellersgoods.pojo.Item;
import com.ccw.sellersgoods.pojo.ItemCat;
import com.ccw.sellersgoods.service.ItemService;
import com.ccw.entity.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

/****
 * @Author:ujiuye
 * @Description:Item业务层接口实现类
 * @Date 2021/2/1 14:19
 *****/
@Service
public class ItemServiceImpl extends ServiceImpl<ItemMapper, Item> implements ItemService {

    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private ItemMapper itemMapper;
    /**
     * Item条件+分页查询
     * @param item 查询条件
     * @param page 页码
     * @param size 页大小
     * @return 分页结果
     */
    @Override
    public PageResult<Item> findPage(Item item, int page, int size){
         Page<Item> mypage = new Page<>(page, size);
        QueryWrapper<Item> queryWrapper = this.createQueryWrapper(item);
        IPage<Item> iPage = this.page(mypage, queryWrapper);
        return new PageResult<Item>(iPage.getTotal(),iPage.getRecords());
    }

    /**
     * Item分页查询
     * @param page
     * @param size
     * @return
     */
    @Override
    public PageResult<Item> findPage(int page, int size){
        Page<Item> mypage = new Page<>(page, size);
        IPage<Item> iPage = this.page(mypage, new QueryWrapper<Item>());

        return new PageResult<Item>(iPage.getTotal(),iPage.getRecords());
    }

    /**
     * Item条件查询
     * @param item
     * @return
     */
    @Override
    public List<Item> findList(Item item){
        //构建查询条件
        QueryWrapper<Item> queryWrapper = this.createQueryWrapper(item);
        //根据构建的条件查询数据
        return this.list(queryWrapper);
    }


    /**
     * Item构建查询对象
     * @param item
     * @return
     */
    public QueryWrapper<Item> createQueryWrapper(Item item){
        QueryWrapper<Item> queryWrapper = new QueryWrapper<>();
        if(item!=null){
            // 商品id，同时也是商品编号
            if(!StringUtils.isEmpty(item.getId())){
                 queryWrapper.eq("id",item.getId());
            }
            // 商品标题
            if(!StringUtils.isEmpty(item.getTitle())){
                queryWrapper.like("title",item.getTitle());
            }
            // 商品卖点
            if(!StringUtils.isEmpty(item.getSellPoint())){
                 queryWrapper.eq("sell_point",item.getSellPoint());
            }
            // 商品价格，单位为：元
            if(!StringUtils.isEmpty(item.getPrice())){
                 queryWrapper.eq("price",item.getPrice());
            }
            // 
            if(!StringUtils.isEmpty(item.getStockCount())){
                 queryWrapper.eq("stock_count",item.getStockCount());
            }
            // 库存数量
            if(!StringUtils.isEmpty(item.getNum())){
                 queryWrapper.eq("num",item.getNum());
            }
            // 商品条形码
            if(!StringUtils.isEmpty(item.getBarcode())){
                 queryWrapper.eq("barcode",item.getBarcode());
            }
            // 商品图片
            if(!StringUtils.isEmpty(item.getImage())){
                 queryWrapper.eq("image",item.getImage());
            }
            // 所属类目，叶子类目
            if(!StringUtils.isEmpty(item.getCategoryId())){
                 queryWrapper.eq("categoryId",item.getCategoryId());
            }
            // 商品状态，1-正常，2-下架，3-删除
            if(!StringUtils.isEmpty(item.getStatus())){
                 queryWrapper.eq("status",item.getStatus());
            }
            // 创建时间
            if(!StringUtils.isEmpty(item.getCreateTime())){
                 queryWrapper.eq("create_time",item.getCreateTime());
            }
            // 更新时间
            if(!StringUtils.isEmpty(item.getUpdateTime())){
                 queryWrapper.eq("update_time",item.getUpdateTime());
            }
            // 
            if(!StringUtils.isEmpty(item.getItemSn())){
                 queryWrapper.eq("item_sn",item.getItemSn());
            }
            // 
            if(!StringUtils.isEmpty(item.getCostPirce())){
                 queryWrapper.eq("cost_pirce",item.getCostPirce());
            }
            // 
            if(!StringUtils.isEmpty(item.getMarketPrice())){
                 queryWrapper.eq("market_price",item.getMarketPrice());
            }
            // 
            if(!StringUtils.isEmpty(item.getIsDefault())){
                 queryWrapper.eq("is_default",item.getIsDefault());
            }
            // 
            if(!StringUtils.isEmpty(item.getGoodsId())){
                 queryWrapper.eq("goods_id",item.getGoodsId());
            }
            // 
            if(!StringUtils.isEmpty(item.getSellerId())){
                 queryWrapper.eq("seller_id",item.getSellerId());
            }
            // 
            if(!StringUtils.isEmpty(item.getCartThumbnail())){
                 queryWrapper.eq("cart_thumbnail",item.getCartThumbnail());
            }
            // 
            if(!StringUtils.isEmpty(item.getCategory())){
                 queryWrapper.eq("category",item.getCategory());
            }
            // 
            if(!StringUtils.isEmpty(item.getBrand())){
                 queryWrapper.eq("brand",item.getBrand());
            }
            // 
            if(!StringUtils.isEmpty(item.getSpec())){
                 queryWrapper.eq("spec",item.getSpec());
            }
            // 
            if(!StringUtils.isEmpty(item.getSeller())){
                 queryWrapper.eq("seller",item.getSeller());
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
     * 修改Item
     * @param item
     */
    @Override
    public void update(Item item){
        this.updateById(item);
    }

    /**
     * 增加Item
     * @param item
     */
    @Override
    public void add(Item item){
        this.save(item);
    }

    /**
     * 根据ID查询Item
     * @param id
     * @return
     */
    @Override
    public Item findById(Long id){
        return  this.getById(id);
    }

    /**
     * 查询Item全部数据
     * @return
     */
    @Override
    public List<Item> findAll() {
        return this.list(new QueryWrapper<Item>());
    }

    @Override
    public List<Item> findByStstus(String status) {
        //创建查询对象
        QueryWrapper<Item> wrapper = new QueryWrapper<>();
        //设置查询条件
        wrapper.eq("status",status);
        return this.list(wrapper);
    }

    @Override
    public void decrCount(String username) {
        List<Cart> cartList = (List<Cart>) redisTemplate.boundHashOps("cartList").get(username);
        if (cartList!=null) {
            for (Cart cart : cartList) {
                for (OrderItem orderItem : cart.getOrderItemList()) {
                    itemMapper.decrCount(orderItem);
                }
            }
        }
    }

}
