package com.ccw.seckill;

import com.ccw.utils.IdWorker;
import com.ccw.utils.TokenDecode;
import org.springframework.core.env.Environment;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableDiscoveryClient
@MapperScan(basePackages = "com.ccw.seckill.dao")
@EnableFeignClients
@EnableScheduling //开启定时任务的注解
@EnableAsync //开启异步处理
public class SeckillApplication {

    @Autowired
    private TokenDecode tokenDecode;
    @Autowired
    private Environment env;
    public static void main(String[] args) {
        SpringApplication.run(SeckillApplication.class);
    }
    @Bean
    public IdWorker idWorker(){
        return new IdWorker(2,1);
    }

    //创建队列
    @Bean
    public Queue createQueue(){
        return new Queue(env.getProperty("mq.pay.queue.order"));
    }
    //创建秒杀队列
    @Bean(name = "seckillQueue")
    public Queue createSeckillQueue(){
        return new Queue(env.getProperty("mq.pay.queue.seckillorder"));
    }

    //创建交换机
    @Bean
    public DirectExchange basicExchange(){
        return new DirectExchange(env.getProperty("mq.pay.queue.order"));
    }

    //创建秒杀交换机
    @Bean(name = "seckillExchange")
    public DirectExchange createSeckillExchange(){
        return new DirectExchange(env.getProperty("mq.pay.queue.seckillorder"));
    }
    //绑定秒杀
    @Bean(name = "SeckillBinding")
    public Binding basicSeckillBinding(){
        return BindingBuilder.bind(createQueue()).to(basicExchange()).with(env.getProperty("mq.pay.queue.seckillkey"));
    }
}
