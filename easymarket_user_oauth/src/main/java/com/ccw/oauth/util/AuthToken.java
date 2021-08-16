package com.ccw.oauth.util;

import java.io.Serializable;

public class AuthToken implements Serializable {

    //令牌
    private String accessToken;
    //刷新令牌
    private String refreshToken;
    //短令牌
    private String jwt;

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public String getJwt() {
        return jwt;
    }

    public void setJwt(String jwt) {
        this.jwt = jwt;
    }
}
