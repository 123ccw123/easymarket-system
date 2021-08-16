package com.ccw.oauth.service;

import com.ccw.oauth.util.AuthToken;

public interface AuthService {

    //用户身份认证返回一个令牌  参数都有用户名用户密码，客户端id客户端密码
    public AuthToken login(String username, String password, String clientId, String clientSecret);
}
