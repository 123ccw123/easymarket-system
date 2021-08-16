package com.ccw.pay.service;

import java.util.Map;

public interface AliPayService {
    /**
     * 预下单
     * @param out_trade_no 订单编号
     * @param total_fee  支付金额：分
     * @return
     */
    public Map<String,Object> createNative(String out_trade_no, String total_fee);

    /**
     * 检测订单支付状态
     * @param out_trade_no 订单编号
     * @return
     */
    public Map<String,String> selectPayStatus(String out_trade_no);

    //秒杀的预下单
    public Map<String,String> createNative(Map<String,String> parameters);
}
