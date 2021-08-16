package com.ccw.utils;

import com.alibaba.fastjson.JSON;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.jwt.Jwt;
import org.springframework.security.jwt.JwtHelper;
import org.springframework.security.jwt.crypto.sign.RsaVerifier;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import springfox.documentation.spi.service.contexts.SecurityContext;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.stream.Collectors;

public class TokenDecode {
    private static final String PUBLIC_KEY="public.key";
    private static String publicKey="";

    /*读取公钥*/
    private String getPublicKey(){
        ClassPathResource classPathResource = new ClassPathResource(PUBLIC_KEY);
        try {
            InputStreamReader inputStreamReader = new InputStreamReader(classPathResource.getInputStream());
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            publicKey = bufferedReader.lines().collect(Collectors.joining("\n"));
            return publicKey;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /*根据公钥解析令牌*/
    public Map<String,String> parseToken(String token){
        Jwt jwt = JwtHelper.decodeAndVerify(token, new RsaVerifier(this.getPublicKey()));
        String tokenStr = jwt.getClaims();
        return JSON.parseObject(tokenStr,Map.class);
    }

    /*得到用户信息*/
    public Map<String,String> getUserInfo(){
        //在容器中获取令牌
        OAuth2AuthenticationDetails oAuth2AuthenticationDetails = (OAuth2AuthenticationDetails) SecurityContextHolder.getContext().getAuthentication().getDetails();
        return this.parseToken(oAuth2AuthenticationDetails.getTokenValue());
    }
}
