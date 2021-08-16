package com.ccw.order.config;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;

public class FeignInterceptor implements RequestInterceptor {
    @Override
    public void apply(RequestTemplate requestTemplate) {
        //使用RequestContextHolder获取相关request变量
        try {
            ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            if (requestAttributes!=null) {
                //取出request
                HttpServletRequest request = requestAttributes.getRequest();
                //获取所有头文件的key
                Enumeration<String> headerNames = request.getHeaderNames();
                if (headerNames !=null) {
                    while (headerNames.hasMoreElements()) {
                        //头文件的key
                        String key = headerNames.nextElement();
                        //头文件的vlue
                        String values = request.getHeader(key);
                        //将令牌数据添加到头文件中
                        requestTemplate.header(key,values);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
