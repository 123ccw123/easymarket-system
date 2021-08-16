package com.ccw;

import com.ccw.config.DelayConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@SpringBootTest
@RunWith(SpringRunner.class)
public class TestQueue {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Test
    public void sendMessage() throws Exception{
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println("当前时间：" + dateFormat.format(new Date()));

        Map map = new HashMap();
        map.put("name","欢迎来到我的世界！");

        rabbitTemplate.convertAndSend(DelayConfig.QUEUE_MESSAGE_DELAY, map, new MessagePostProcessor() {
            @Override
            public Message postProcessMessage(Message map) throws AmqpException {
                map.getMessageProperties().setExpiration("1000");
                return map;
            }
        });
        try {
            System.in.read();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
