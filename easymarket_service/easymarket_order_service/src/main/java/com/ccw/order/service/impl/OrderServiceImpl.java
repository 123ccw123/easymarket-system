package com.ccw.order.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ccw.order.dao.OrderItemMapper;
import com.ccw.order.dao.OrderMapper;
import com.ccw.order.dao.PayLogMapper;
import com.ccw.order.group.Cart;
import com.ccw.order.pojo.Order;
import com.ccw.order.pojo.OrderItem;
import com.ccw.order.pojo.PayLog;
import com.ccw.order.service.OrderService;
import com.ccw.entity.PageResult;
import com.ccw.sellersgoods.feign.ItemFeign;
import com.ccw.utils.IdWorker;
import com.ccw.utils.TokenDecode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/****
 * @Author:ujiuye
 * @Description:Order业务层接口实现类
 * @Date 2021/2/1 14:19
 *****/
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService {

    @Autowired
    private OrderItemMapper orderItemMapper;
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private IdWorker idWorker;
    @Autowired
    private ItemFeign itemFeign;
    @Autowired
    private PayLogMapper payLogMapper;
    @Autowired
    private TokenDecode tokenDecode;

    /**
     * Order条件+分页查询
     * @param order 查询条件
     * @param page 页码
     * @param size 页大小
     * @return 分页结果
     */
    @Override
    public PageResult<Order> findPage(Order order, int page, int size){
         Page<Order> mypage = new Page<>(page, size);
        QueryWrapper<Order> queryWrapper = this.createQueryWrapper(order);
        IPage<Order> iPage = this.page(mypage, queryWrapper);
        return new PageResult<Order>(iPage.getTotal(),iPage.getRecords());
    }

    /**
     * Order分页查询
     * @param page
     * @param size
     * @return
     */
    @Override
    public PageResult<Order> findPage(int page, int size){
        Page<Order> mypage = new Page<>(page, size);
        IPage<Order> iPage = this.page(mypage, new QueryWrapper<Order>());

        return new PageResult<Order>(iPage.getTotal(),iPage.getRecords());
    }

    /**
     * Order条件查询
     * @param order
     * @return
     */
    @Override
    public List<Order> findList(Order order){
        //构建查询条件
        QueryWrapper<Order> queryWrapper = this.createQueryWrapper(order);
        //根据构建的条件查询数据
        return this.list(queryWrapper);
    }


    /**
     * Order构建查询对象
     * @param order
     * @return
     */
    public QueryWrapper<Order> createQueryWrapper(Order order){
        QueryWrapper<Order> queryWrapper = new QueryWrapper<>();
        if(order!=null){
            // 订单id
            if(!StringUtils.isEmpty(order.getOrderId())){
                 queryWrapper.eq("order_id",order.getOrderId());
            }
            // 实付金额。精确到2位小数;单位:元。如:200.07，表示:200元7分
            if(!StringUtils.isEmpty(order.getPayment())){
                 queryWrapper.eq("payment",order.getPayment());
            }
            // 支付类型，1、在线支付，2、货到付款
            if(!StringUtils.isEmpty(order.getPaymentType())){
                 queryWrapper.eq("payment_type",order.getPaymentType());
            }
            // 邮费。精确到2位小数;单位:元。如:200.07，表示:200元7分
            if(!StringUtils.isEmpty(order.getPostFee())){
                 queryWrapper.eq("post_fee",order.getPostFee());
            }
            // 状态：1、未付款，2、已付款，3、未发货，4、已发货，5、交易成功，6、交易关闭,7、待评价
            if(!StringUtils.isEmpty(order.getStatus())){
                 queryWrapper.eq("status",order.getStatus());
            }
            // 订单创建时间
            if(!StringUtils.isEmpty(order.getCreateTime())){
                 queryWrapper.eq("create_time",order.getCreateTime());
            }
            // 订单更新时间
            if(!StringUtils.isEmpty(order.getUpdateTime())){
                 queryWrapper.eq("update_time",order.getUpdateTime());
            }
            // 付款时间
            if(!StringUtils.isEmpty(order.getPaymentTime())){
                 queryWrapper.eq("payment_time",order.getPaymentTime());
            }
            // 发货时间
            if(!StringUtils.isEmpty(order.getConsignTime())){
                 queryWrapper.eq("consign_time",order.getConsignTime());
            }
            // 交易完成时间
            if(!StringUtils.isEmpty(order.getEndTime())){
                 queryWrapper.eq("end_time",order.getEndTime());
            }
            // 交易关闭时间
            if(!StringUtils.isEmpty(order.getCloseTime())){
                 queryWrapper.eq("close_time",order.getCloseTime());
            }
            // 物流名称
            if(!StringUtils.isEmpty(order.getShippingName())){
                 queryWrapper.eq("shipping_name",order.getShippingName());
            }
            // 物流单号
            if(!StringUtils.isEmpty(order.getShippingCode())){
                 queryWrapper.eq("shipping_code",order.getShippingCode());
            }
            // 用户id
            if(!StringUtils.isEmpty(order.getUserId())){
                 queryWrapper.eq("user_id",order.getUserId());
            }
            // 买家留言
            if(!StringUtils.isEmpty(order.getBuyerMessage())){
                 queryWrapper.eq("buyer_message",order.getBuyerMessage());
            }
            // 买家昵称
            if(!StringUtils.isEmpty(order.getBuyerNick())){
                 queryWrapper.eq("buyer_nick",order.getBuyerNick());
            }
            // 买家是否已经评价
            if(!StringUtils.isEmpty(order.getBuyerRate())){
                 queryWrapper.eq("buyer_rate",order.getBuyerRate());
            }
            // 收货人地区名称(省，市，县)街道
            if(!StringUtils.isEmpty(order.getReceiverAreaName())){
                 queryWrapper.eq("receiver_area_name",order.getReceiverAreaName());
            }
            // 收货人手机
            if(!StringUtils.isEmpty(order.getReceiverMobile())){
                 queryWrapper.eq("receiver_mobile",order.getReceiverMobile());
            }
            // 收货人邮编
            if(!StringUtils.isEmpty(order.getReceiverZipCode())){
                 queryWrapper.eq("receiver_zip_code",order.getReceiverZipCode());
            }
            // 收货人
            if(!StringUtils.isEmpty(order.getReceiver())){
                 queryWrapper.eq("receiver",order.getReceiver());
            }
            // 过期时间，定期清理
            if(!StringUtils.isEmpty(order.getExpire())){
                 queryWrapper.eq("expire",order.getExpire());
            }
            // 发票类型(普通发票，电子发票，增值税发票)
            if(!StringUtils.isEmpty(order.getInvoiceType())){
                 queryWrapper.eq("invoice_type",order.getInvoiceType());
            }
            // 订单来源：1:app端，2：pc端，3：M端，4：微信端，5：手机qq端
            if(!StringUtils.isEmpty(order.getSourceType())){
                 queryWrapper.eq("source_type",order.getSourceType());
            }
            // 商家ID
            if(!StringUtils.isEmpty(order.getSellerId())){
                 queryWrapper.eq("seller_id",order.getSellerId());
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
     * 修改Order
     * @param order
     */
    @Override
    public void update(Order order){
        this.updateById(order);
    }

    /**
     * 增加Order
     * @param order
     */
    @Override
    public void add(Order order) {
        //1、根据当前登录用户在缓存取得购物车数据
        List<Cart> cartList = (List<Cart>) redisTemplate.boundHashOps("cartList").get(order.getUserId());
        double total_fee = 0.00;
        //2、遍历购物车列表
        if (cartList!=null) {
            List<String> orderList = new ArrayList<>();
            for (Cart cart : cartList) {
                //3、创建订单对象并保存
                Order tbOrder=new Order();
                long id = idWorker.nextId();
                System.out.println("订单编号" + id);
                tbOrder.setOrderId(id);                             //订单id
                orderList.add(tbOrder.getOrderId()+"");
                tbOrder.setPaymentType(order.getPaymentType());     //获取支付方式
                tbOrder.setStatus("1");                             //设置支付状态
                tbOrder.setCreateTime(new Date());                  //订单创建时间
                tbOrder.setUpdateTime(new Date());                  //更新事件
                tbOrder.setUserId(order.getUserId());               //查询当前用户id信息
                tbOrder.setReceiverAreaName(order.getReceiverAreaName()); //收货地址
                tbOrder.setReceiverMobile(order.getReceiverMobile());     //收货人电话
                tbOrder.setReceiver(order.getReceiver());           //收货人信息
                tbOrder.setSourceType(order.getSourceType());       //订单来源
                tbOrder.setSellerId(cart.getSellerId());           //商家id
                double money = 0.0;
                //4、向订单详情中设置订单编号
                for (OrderItem orderItem : cart.getOrderItemList()) {
                    long orderItemId = idWorker.nextId();
                    orderItem.setId(id);
                    orderItem.setOrderId(orderItemId);
                    //5、保存订单详情对象
                    orderItemMapper.insert(orderItem);
                    money += orderItem.getTotalFee().doubleValue();
                }
                tbOrder.setPayment(new BigDecimal(money));
                //支付的总金额
                total_fee += tbOrder.getPayment().doubleValue();
                orderMapper.insert(tbOrder);
                itemFeign.decrCount(order.getUserId());
            }
            //判断用户支付方式
            if (order.getPaymentType().equals("1")) {
                PayLog payLog = new PayLog();
                payLog.setOutTradeNo(idWorker.nextId()+""); //支付订单号
                payLog.setCreateTime(new Date());           //创建时间
                payLog.setUserId(order.getUserId());        //用户信息
                payLog.setTradeState("0");                  //交易状态，0表示未支付

                String orderStr = orderList.toString().replace("[","").replace("]","").replace(" ","");
                payLog.setOrderList(orderStr);              //订单编号
                payLog.setPayType("1");                     //线上支付
                //单位转换 元转分
                BigDecimal total_fee_big = BigDecimal.valueOf(total_fee);
                BigDecimal num = new BigDecimal(100);
                BigDecimal multiply = total_fee_big.multiply(num);
                payLog.setTotalFee(multiply.longValue());   //支付总金额
                redisTemplate.boundHashOps("payLog").put(order.getUserId(),payLog);  //存储到缓存中
                payLogMapper.insert(payLog);  //存储到数据库
            }
            //6、清空缓存中的购物车数据
            redisTemplate.boundHashOps("cartList").delete(order.getUserId());
        }
    }

    /**
     * 根据ID查询Order
     * @param id
     * @return
     */
    @Override
    public Order findById(Long id){
        return  this.getById(id);
    }

    /**
     * 查询Order全部数据
     * @return
     */
    @Override
    public List<Order> findAll() {
        return this.list(new QueryWrapper<Order>());
    }

    @Override
    public PayLog selectPayLogFromRedis(String username) {

        return (PayLog) redisTemplate.boundHashOps("payLog").get(username);
    }

    @Override
    public void updateStatus(String out_trade_no, String transaction_id) {
        //1、根据订单号从数据库中获取支付日志
        PayLog payLog = payLogMapper.selectById(out_trade_no);
        //2、修改支付日志状态
        payLog.setPayType("1");    //设置支付状态
        payLog.setPayTime(new Date());   //设置支付时间
        payLog.setTransactionId(transaction_id);  //设置支付流水
        payLogMapper.updateById(payLog);
        //3、修改支付状态
        String[] orderList = payLog.getOrderList().split(",");
        for (String orderId : orderList) {
            Order order = orderMapper.selectById(orderId);
            order.setStatus("2");
            order.setPaymentTime(new Date());
            orderMapper.updateById(order);
        }
        //清空缓存中的支付日志
        redisTemplate.boundHashOps("payLog").delete(payLog.getUserId());
    }
}
