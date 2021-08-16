package com.ccw.seckill.listener;

import com.alibaba.fastjson.JSON;
import com.ccw.seckill.service.SeckillOrderService;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
@RabbitListener(queues = "${mq.pay.queue.seckillorder}")
public class SeckillListener {

    @Autowired
    private SeckillOrderService seckillOrderService;
    @RabbitHandler
    public void consumerMessage(@Payload String message){
        System.out.println("message:"+message);
        //转换数据类型
        Map<String,String> map = JSON.parseObject(message, Map.class);
        //得到支付状态，修改秒杀支付状态的接口
        String body = map.get("body");
        String[] strings = body.split("&");
        Map<String,String> resultMap = new HashMap<>();
        for (String spring : strings) {
            String[] var = spring.split("=");
            resultMap.put(var[0],var[1]);
        }
        if (map.get("trade_status")!=null&&map.get("trade_status").equals("TRADE_SUCCESS")) {

            seckillOrderService.updateStatus(map.get("out_trade_no"),map.get("trade_no"),resultMap.get("username"));
        }else {
            seckillOrderService.closeOrder(resultMap.get("username"));
        }
    }
}
