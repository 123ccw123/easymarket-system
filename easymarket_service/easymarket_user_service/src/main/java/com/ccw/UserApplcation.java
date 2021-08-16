package com.ccw;

import com.ccw.utils.TokenDecode;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableDiscoveryClient
@FeignClient
@MapperScan("com.ccw.dao")
public class UserApplcation {
    public static void main(String[] args) {
        SpringApplication.run(UserApplcation.class);
    }
    @Bean
    public TokenDecode tokenDecode(){
        return new TokenDecode();
    }
}
