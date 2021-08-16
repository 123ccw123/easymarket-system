package com.ccw;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableRabbit
public class DelayApplication {
    public static void main(String[] args) {
        SpringApplication.run(DelayApplication.class);
    }
}
