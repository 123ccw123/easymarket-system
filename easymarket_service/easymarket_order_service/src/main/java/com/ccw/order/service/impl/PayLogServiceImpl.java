package com.ccw.order.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ccw.order.dao.PayLogMapper;
import com.ccw.order.pojo.PayLog;
import com.ccw.order.service.PayLogService;
import com.ccw.entity.PageResult;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

/****
 * @Author:ujiuye
 * @Description:PayLog业务层接口实现类
 * @Date 2021/2/1 14:19
 *****/
@Service
public class PayLogServiceImpl extends ServiceImpl<PayLogMapper, PayLog> implements PayLogService {


    /**
     * PayLog条件+分页查询
     * @param payLog 查询条件
     * @param page 页码
     * @param size 页大小
     * @return 分页结果
     */
    @Override
    public PageResult<PayLog> findPage(PayLog payLog, int page, int size){
         Page<PayLog> mypage = new Page<>(page, size);
        QueryWrapper<PayLog> queryWrapper = this.createQueryWrapper(payLog);
        IPage<PayLog> iPage = this.page(mypage, queryWrapper);
        return new PageResult<PayLog>(iPage.getTotal(),iPage.getRecords());
    }

    /**
     * PayLog分页查询
     * @param page
     * @param size
     * @return
     */
    @Override
    public PageResult<PayLog> findPage(int page, int size){
        Page<PayLog> mypage = new Page<>(page, size);
        IPage<PayLog> iPage = this.page(mypage, new QueryWrapper<PayLog>());

        return new PageResult<PayLog>(iPage.getTotal(),iPage.getRecords());
    }

    /**
     * PayLog条件查询
     * @param payLog
     * @return
     */
    @Override
    public List<PayLog> findList(PayLog payLog){
        //构建查询条件
        QueryWrapper<PayLog> queryWrapper = this.createQueryWrapper(payLog);
        //根据构建的条件查询数据
        return this.list(queryWrapper);
    }


    /**
     * PayLog构建查询对象
     * @param payLog
     * @return
     */
    public QueryWrapper<PayLog> createQueryWrapper(PayLog payLog){
        QueryWrapper<PayLog> queryWrapper = new QueryWrapper<>();
        if(payLog!=null){
            // 支付订单号
            if(!StringUtils.isEmpty(payLog.getOutTradeNo())){
                 queryWrapper.eq("out_trade_no",payLog.getOutTradeNo());
            }
            // 创建日期
            if(!StringUtils.isEmpty(payLog.getCreateTime())){
                 queryWrapper.eq("create_time",payLog.getCreateTime());
            }
            // 支付完成时间
            if(!StringUtils.isEmpty(payLog.getPayTime())){
                 queryWrapper.eq("pay_time",payLog.getPayTime());
            }
            // 支付金额（分）
            if(!StringUtils.isEmpty(payLog.getTotalFee())){
                 queryWrapper.eq("total_fee",payLog.getTotalFee());
            }
            // 用户ID
            if(!StringUtils.isEmpty(payLog.getUserId())){
                 queryWrapper.eq("user_id",payLog.getUserId());
            }
            // 交易号码
            if(!StringUtils.isEmpty(payLog.getTransactionId())){
                 queryWrapper.eq("transaction_id",payLog.getTransactionId());
            }
            // 交易状态
            if(!StringUtils.isEmpty(payLog.getTradeState())){
                 queryWrapper.eq("trade_state",payLog.getTradeState());
            }
            // 订单编号列表
            if(!StringUtils.isEmpty(payLog.getOrderList())){
                 queryWrapper.eq("order_list",payLog.getOrderList());
            }
            // 支付类型
            if(!StringUtils.isEmpty(payLog.getPayType())){
                 queryWrapper.eq("pay_type",payLog.getPayType());
            }
        }
        return queryWrapper;
    }

    /**
     * 删除
     * @param id
     */
    @Override
    public void delete(String id){
        this.removeById(id);
    }

    /**
     * 修改PayLog
     * @param payLog
     */
    @Override
    public void update(PayLog payLog){
        this.updateById(payLog);
    }

    /**
     * 增加PayLog
     * @param payLog
     */
    @Override
    public void add(PayLog payLog){
        this.save(payLog);
    }

    /**
     * 根据ID查询PayLog
     * @param id
     * @return
     */
    @Override
    public PayLog findById(String id){
        return  this.getById(id);
    }

    /**
     * 查询PayLog全部数据
     * @return
     */
    @Override
    public List<PayLog> findAll() {
        return this.list(new QueryWrapper<PayLog>());
    }
}
