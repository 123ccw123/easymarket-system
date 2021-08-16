package com.ccw;

import com.ccw.utils.IdWorker;
import com.ccw.utils.TokenDecode;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import com.ccw.order.config.FeignInterceptor;

@SpringBootApplication
@EnableDiscoveryClient
@MapperScan("com.ccw.order.dao")
@EnableFeignClients(basePackages = "com.ccw.sellersgoods.feign")
public class OrderApplication {
    public static void main(String[] args) {
        SpringApplication.run(OrderApplication.class);
    }

    @Bean
    public FeignInterceptor feignInterceptor(){
        return new FeignInterceptor();
    }

    @Bean
    public TokenDecode tokenDecode(){
        return new TokenDecode();
    }

    @Bean
    public IdWorker idWorker(){
        //0表示及其id，1表示序列号
        return new IdWorker(0,1);
    }
}
