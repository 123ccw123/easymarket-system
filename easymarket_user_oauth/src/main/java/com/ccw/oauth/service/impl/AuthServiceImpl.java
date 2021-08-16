package com.ccw.oauth.service.impl;

import com.ccw.oauth.service.AuthService;
import com.ccw.oauth.util.AuthToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Service;
import org.springframework.util.Base64Utils;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.DefaultResponseErrorHandler;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private LoadBalancerClient loadBalancerClient;
    @Autowired
    private RestTemplate restTemplate;

    @Override
    public AuthToken login(String username, String password, String clientId, String clientSecret) {
        return this.applyToken(username,password,clientId,clientSecret);
    }

    public AuthToken applyToken(String username, String password, String clientId, String clientSecret){
        //1、获取认证服务的信息（远程服务调用）
        ServiceInstance choose = loadBalancerClient.choose("USER-AUTH");
        System.out.println(choose);
        if (choose==null){
            throw new RuntimeException("找不到认证服务");
        }
        //2、获取得到令牌的url
        String path = choose.getUri().toString() + "/oauth/token";
        //3、设置获得的令牌的参数 授权模式 用户名 密码
        MultiValueMap formData = new LinkedMultiValueMap<>();
        formData.add("grant_type","password");
        formData.add("username",username);
        formData.add("password",password);
        //4、设置客户端的用户名和密码
        MultiValueMap headers = new LinkedMultiValueMap<>();
        headers.add("Authorization",this.httpBasic(clientId,clientSecret));
        restTemplate.setErrorHandler(new DefaultResponseErrorHandler(){
            @Override
            public void handleError(ClientHttpResponse response) throws IOException {
                if (response.getRawStatusCode()!=400 && response.getRawStatusCode()!=401){
                    super.handleError(response);
                }
            }

        });
        //5、发送请求获取令牌
        Map resultMap = new HashMap();
        try {
            ResponseEntity<Map> responseEntity = restTemplate.exchange(path, HttpMethod.POST, new HttpEntity<MultiValueMap>(formData, headers), Map.class);
            resultMap = responseEntity.getBody();
        } catch (RestClientException e) {
            e.printStackTrace();
        }
        //6、判断令牌内容是否为空
        System.out.println(resultMap);
        if (resultMap==null||resultMap.get("access_token")==null||resultMap.get("refresh_token")==null||resultMap.get("jti")==null){
            throw new RuntimeException("生成令牌失败");
        }
        //7、如果不为空，那么将令牌信息封装到AuthToken中并返回结果

        AuthToken authToken = new AuthToken();
        authToken.setAccessToken((String)resultMap.get("access_token"));
        authToken.setRefreshToken((String)resultMap.get("refresh_token"));
        authToken.setJwt((String)resultMap.get("jti"));
        return authToken;
    }
    public String httpBasic(String clientId, String clientSecret){
        String str = clientId+":"+clientSecret;
        byte[] encode = Base64Utils.encode(str.getBytes());
        return "Basic "+new String(encode);
    }
}
