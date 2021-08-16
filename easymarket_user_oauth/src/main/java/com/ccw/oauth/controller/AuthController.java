package com.ccw.oauth.controller;





import com.ccw.entity.Result;
import com.ccw.entity.StatusCode;
import com.ccw.oauth.service.AuthService;
import com.ccw.oauth.util.AuthToken;
import com.ccw.oauth.util.CookieUtil;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

/**
 * @Auther: lhq
 * @Date: 2021/8/2 15:08
 * @Description:
 */
@RestController
@RequestMapping("/user")
public class AuthController {

    @Value("${auth.clientId}")
    private String clientId;
    @Value("${auth.clientSecret}")
    private String clientSecret;


    @Value("${auth.cookieDomain}")
    private String cookieDomain;

    @Value("${auth.cookieMaxAge}")
    private int cookieMaxAge;

    @Autowired
    private AuthService authService;


    @PostMapping("/login")
    public Result login(String username, String password, HttpServletResponse response) {
        if (StringUtils.isEmpty(username)) {
            return new Result(false, StatusCode.LOGINERROR, "用户名不能为空");
        }
        if (StringUtils.isEmpty(password)) {
            return new Result(false, StatusCode.LOGINERROR, "密码不能为空");
        }
        //调用认证服务验证用户身份，确认身份后发放令牌，并将令牌放到Cookie中
        AuthToken authToken = authService.login(username, password, clientId, clientSecret);


        CookieUtil.addCookie(response, cookieDomain, "/", "Authorization", authToken.getAccessToken(), cookieMaxAge, false);

        return new Result();
    }
}
