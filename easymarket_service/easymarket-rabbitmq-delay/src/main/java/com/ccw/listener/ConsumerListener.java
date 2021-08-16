package com.ccw.listener;

import com.ccw.config.DelayConfig;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component
@RabbitListener(queues = DelayConfig.QUEUE_MESSAGE)
public class ConsumerListener {
    @RabbitHandler
    public void consumeMessage(@Payload Object message){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println("当前时间：" + dateFormat.format(new Date()));
        System.out.println("接收时间："+message);
    }
}
