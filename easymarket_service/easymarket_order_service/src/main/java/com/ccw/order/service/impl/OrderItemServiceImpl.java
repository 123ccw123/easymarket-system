package com.ccw.order.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ccw.order.dao.OrderItemMapper;
import com.ccw.order.pojo.OrderItem;
import com.ccw.order.service.OrderItemService;
import com.ccw.entity.PageResult;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

/****
 * @Author:ujiuye
 * @Description:OrderItem业务层接口实现类
 * @Date 2021/2/1 14:19
 *****/
@Service
public class OrderItemServiceImpl extends ServiceImpl<OrderItemMapper, OrderItem> implements OrderItemService {


    /**
     * OrderItem条件+分页查询
     * @param orderItem 查询条件
     * @param page 页码
     * @param size 页大小
     * @return 分页结果
     */
    @Override
    public PageResult<OrderItem> findPage(OrderItem orderItem, int page, int size){
         Page<OrderItem> mypage = new Page<>(page, size);
        QueryWrapper<OrderItem> queryWrapper = this.createQueryWrapper(orderItem);
        IPage<OrderItem> iPage = this.page(mypage, queryWrapper);
        return new PageResult<OrderItem>(iPage.getTotal(),iPage.getRecords());
    }

    /**
     * OrderItem分页查询
     * @param page
     * @param size
     * @return
     */
    @Override
    public PageResult<OrderItem> findPage(int page, int size){
        Page<OrderItem> mypage = new Page<>(page, size);
        IPage<OrderItem> iPage = this.page(mypage, new QueryWrapper<OrderItem>());

        return new PageResult<OrderItem>(iPage.getTotal(),iPage.getRecords());
    }

    /**
     * OrderItem条件查询
     * @param orderItem
     * @return
     */
    @Override
    public List<OrderItem> findList(OrderItem orderItem){
        //构建查询条件
        QueryWrapper<OrderItem> queryWrapper = this.createQueryWrapper(orderItem);
        //根据构建的条件查询数据
        return this.list(queryWrapper);
    }


    /**
     * OrderItem构建查询对象
     * @param orderItem
     * @return
     */
    public QueryWrapper<OrderItem> createQueryWrapper(OrderItem orderItem){
        QueryWrapper<OrderItem> queryWrapper = new QueryWrapper<>();
        if(orderItem!=null){
            // 
            if(!StringUtils.isEmpty(orderItem.getId())){
                 queryWrapper.eq("id",orderItem.getId());
            }
            // 商品id
            if(!StringUtils.isEmpty(orderItem.getItemId())){
                 queryWrapper.eq("item_id",orderItem.getItemId());
            }
            // SPU_ID
            if(!StringUtils.isEmpty(orderItem.getGoodsId())){
                 queryWrapper.eq("goods_id",orderItem.getGoodsId());
            }
            // 订单id
            if(!StringUtils.isEmpty(orderItem.getOrderId())){
                 queryWrapper.eq("order_id",orderItem.getOrderId());
            }
            // 商品标题
            if(!StringUtils.isEmpty(orderItem.getTitle())){
                queryWrapper.like("title",orderItem.getTitle());
            }
            // 商品单价
            if(!StringUtils.isEmpty(orderItem.getPrice())){
                 queryWrapper.eq("price",orderItem.getPrice());
            }
            // 商品购买数量
            if(!StringUtils.isEmpty(orderItem.getNum())){
                 queryWrapper.eq("num",orderItem.getNum());
            }
            // 商品总金额
            if(!StringUtils.isEmpty(orderItem.getTotalFee())){
                 queryWrapper.eq("total_fee",orderItem.getTotalFee());
            }
            // 商品图片地址
            if(!StringUtils.isEmpty(orderItem.getPicPath())){
                 queryWrapper.eq("pic_path",orderItem.getPicPath());
            }
            // 
            if(!StringUtils.isEmpty(orderItem.getSellerId())){
                 queryWrapper.eq("seller_id",orderItem.getSellerId());
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
     * 修改OrderItem
     * @param orderItem
     */
    @Override
    public void update(OrderItem orderItem){
        this.updateById(orderItem);
    }

    /**
     * 增加OrderItem
     * @param orderItem
     */
    @Override
    public void add(OrderItem orderItem){
        this.save(orderItem);
    }

    /**
     * 根据ID查询OrderItem
     * @param id
     * @return
     */
    @Override
    public OrderItem findById(Long id){
        return  this.getById(id);
    }

    /**
     * 查询OrderItem全部数据
     * @return
     */
    @Override
    public List<OrderItem> findAll() {
        return this.list(new QueryWrapper<OrderItem>());
    }
}
