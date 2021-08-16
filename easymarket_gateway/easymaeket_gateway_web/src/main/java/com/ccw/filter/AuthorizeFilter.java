package com.ccw.filter;

import com.ccw.utils.JwtUtil;
import io.jsonwebtoken.Claims;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpCookie;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
public class AuthorizeFilter implements GlobalFilter, Ordered {

    //认证服务登录页
    public static final String LOGIN_URL="http://localhost:9100/oauth/login";

    //声明令牌头的信息
    public static final String AUTHORIZE_TOKEN = "Authorization";
    //全局过滤的方法
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        //1、获取请求和响应对象
        ServerHttpRequest request = exchange.getRequest();
        ServerHttpResponse response = exchange.getResponse();

        //判断请求路径不需要令牌
        String path = request.getURI().getPath();
        if (UrlFilter.hasAuthorize(path)){
            //放行
            Mono<Void> filter = chain.filter(exchange);
            return filter;
        }

        //2、从请求头中获取令牌信息
        String token = request.getHeaders().getFirst(AUTHORIZE_TOKEN);
        //3、从所带属性中获取令牌信息
        if (StringUtils.isEmpty(token)) {
            token = request.getQueryParams().getFirst(AUTHORIZE_TOKEN);
        }
        //从cookies中获取令牌
        HttpCookie first = request.getCookies().getFirst(AUTHORIZE_TOKEN);
        if (first!=null){
            token = first.getValue();
        }

        //4、如果没有获取到令牌信息直接返回错误
        if (StringUtils.isEmpty(token)) {
            //response.setStatusCode(HttpStatus.METHOD_NOT_ALLOWED);
            //return response.setComplete();
            //跳转到登录页面
            return this.needAuthorization(LOGIN_URL+"?FROM="+request.getURI().toString(),exchange);
        }
        //5、如果令牌信息错误直接返回错误
        try {
            //Claims claims = JwtUtil.parseJWT(token);
            //将解析后的令牌放回请求头
            //request.mutate().header(AUTHORIZE_TOKEN,claims.toString());
            if (token.startsWith("bearer")||token.startsWith("BEARER")){
                request.mutate().header(AUTHORIZE_TOKEN,token); //直接将令牌放到请求的头文件中
            }else{
                request.mutate().header(AUTHORIZE_TOKEN,"bearer "+token);
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.setStatusCode(HttpStatus.UNAUTHORIZED);
        }
        return chain.filter(exchange);
    }

    //没有令牌时需要强制登录
    public Mono<Void> needAuthorization(String url,ServerWebExchange exchange){

        //获取响应对象
        ServerHttpResponse response = exchange.getResponse();
        //封装响应码
        response.setStatusCode(HttpStatus.SEE_OTHER);
        //封装响应路径
        response.getHeaders().set("Location",url);

        return response.setComplete();
    }
    //过滤器的执行顺序
    @Override
    public int getOrder() {
        return 0;
    }
}
