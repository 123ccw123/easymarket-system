package com.ccw.order.feign;

import com.ccw.entity.Result;
import com.ccw.order.pojo.PayLog;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

@FeignClient(value = "ORDER")
public interface OrderFeign {
    @GetMapping("/order/selectPayLogFromRedis")
    public Result<PayLog> selectPayLogFromRedis();

    @PutMapping("/order/updateStatus/{out_trade_no}/{transaction_id}")
    public Result updateStatus(@PathVariable(value = "out_trade_no") String out_trade_no, @PathVariable(value = "transaction_id") String transaction_id);
    }
