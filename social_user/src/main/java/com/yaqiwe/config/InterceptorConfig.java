package com.yaqiwe.config;

import com.yaqiwe.exception.UserException;
import com.yaqiwe.interceptor.JwtInterceptor;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author yaqiwe
 * @Date 2020/3/20 16:19
 * @Version 1.0
 */
@Configuration
public class InterceptorConfig extends WebMvcConfigurationSupport {

    @Autowired
    JwtInterceptor jwtInterceptor;

    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        List<String> PathList=new ArrayList<>();
        PathList.add("/user/login/**");  //登录
        PathList.add("/admin/login/**");  //登录
        PathList.add("/user/register/**");    //注册
        PathList.add("/user/sendsms/**");    //发送短信
        registry.addInterceptor(jwtInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns(PathList);
    }

}
