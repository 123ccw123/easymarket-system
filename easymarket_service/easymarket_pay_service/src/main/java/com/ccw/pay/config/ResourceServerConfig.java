package com.ccw.pay.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

/**
 * @Auther: lhq
 * @Date: 2021/8/3 9:24
 * @Description:   资源服务的配置类（使用公钥验证令牌）
 */
@Configuration
@EnableResourceServer  //开启资源认证
@EnableGlobalMethodSecurity(prePostEnabled = true,securedEnabled = true)//开启方法权限
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {
    //1.声明公钥的路径
    public static final String PUBLIC_KEY="public.key";

    //2.使用IO流读取公钥的内容
    private String getPublicKey(){
        //加载公钥文件
        ClassPathResource classPathResource = new ClassPathResource(PUBLIC_KEY);
        try {
            InputStreamReader inputStreamReader = new InputStreamReader(classPathResource.getInputStream());
            BufferedReader br = new BufferedReader(inputStreamReader);
            return br.lines().collect(Collectors.joining("\n"));

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
    //3.声明令牌解析的对象
    @Bean
    public JwtAccessTokenConverter jwtAccessTokenConverter(){
        JwtAccessTokenConverter jwtAccessTokenConverter = new JwtAccessTokenConverter();
        jwtAccessTokenConverter.setVerifierKey(this.getPublicKey());   //读取公钥内容
        return jwtAccessTokenConverter;
    }

    //4.声明令牌库的对象
    @Bean
    public TokenStore tokenStore(JwtAccessTokenConverter jwtAccessTokenConverter){
        return new JwtTokenStore(jwtAccessTokenConverter);
    }

    //5.配置过滤路径（配置直接放行的请求）
    public void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/alipay/notify/url")    //放行请求
                .permitAll()
                .anyRequest()
                .authenticated();   //其它地址需要认证授权
    }


}
