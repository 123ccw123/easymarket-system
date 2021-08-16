package com.ccw.sellersgoods;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
@MapperScan("com.ccw.sellersgoods.dao")
public class SellersgoodsServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(SellersgoodsServiceApplication.class,args);
    }
}
