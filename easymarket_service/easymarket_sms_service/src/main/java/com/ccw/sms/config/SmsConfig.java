package com.ccw.sms.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SmsConfig {
    private String queueName = "easymarket.sms.queue";

    @Bean
    public Queue simpleQueue(){
        return new Queue(queueName);
    }
}
