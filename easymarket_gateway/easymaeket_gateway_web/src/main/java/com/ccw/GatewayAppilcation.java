package com.ccw;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@SpringBootApplication
@EnableDiscoveryClient
public class GatewayAppilcation {
    public static void main(String[] args) {
        SpringApplication.run(GatewayAppilcation.class);
    }

    //创建限流对象 根据ip地址进行操作
    @Bean(name="ipKeyResolver")
    public KeyResolver userKeyResolver(){
        return new KeyResolver(){
            @Override
            public Mono<String> resolve(ServerWebExchange exchange){
            //获取远程客户端IP
                String hostName =exchange.getRequest().getRemoteAddress().getAddress().getHostAddress();
                System.out.println("hostName:"+hostName);
                return Mono.just(hostName);
            }
        };
    }

}
