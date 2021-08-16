package com.ccw.sellersgoods.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
    //配置文档属性
    private ApiInfo getApiInfo(){
        return new ApiInfoBuilder().title("商品微服务接口文档")
                .description("给前端人员查看")
                .version("1.0")
                .contact("ccw")
                .termsOfServiceUrl("www.baidu.com")
                .build();
    }
    @Bean
    public Docket docket(){
        return new Docket(DocumentationType.SWAGGER_2).apiInfo(getApiInfo())
                .groupName("group1")
                .select().build();
    }
}
