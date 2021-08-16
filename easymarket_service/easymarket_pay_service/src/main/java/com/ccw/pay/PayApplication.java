package com.ccw.pay;

import com.ccw.pay.config.FeignInterceptor;
import com.ccw.utils.IdWorker;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;


@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@EnableDiscoveryClient
@EnableFeignClients(basePackages = "com.ccw.order.feign")
public class PayApplication {
    public static void main(String[] args) {
        SpringApplication.run(PayApplication.class);
    }

    @Bean
    public IdWorker idWorker(){
        return new IdWorker(1,1);
    }
    /*@Bean
    public TokenDecode tokenDecode(){
        return new TokenDecode();
    }*/
    @Bean
    public FeignInterceptor feignInterceptor(){
        return new FeignInterceptor();
    }

    @Autowired
    private Environment env;

    //创建队列
    @Bean
    public Queue createQueue(){
        return new Queue(env.getProperty("mq.pay.queue.order"));
    }

    //创建交换机
    @Bean
    public DirectExchange basicExchange(){
        return new DirectExchange(env.getProperty("mq.pay.queue.order"));
    }

    //绑定
    @Bean
    public Binding basicBinding(){
        return BindingBuilder.bind(createQueue()).to(basicExchange()).with(env.getProperty("mq.pay.queue.order"));
    }

    //创建秒杀队列
    @Bean(name = "seckillQueue")
    public Queue createSeckillQueue(){
        return new Queue(env.getProperty("mq.pay.queue.seckillorder"));
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
