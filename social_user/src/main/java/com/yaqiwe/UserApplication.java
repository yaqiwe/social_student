package com.yaqiwe;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import util.JwtUtil;
import util.SnowflakeUtil;

/**
 * @Author yaqiwe
 * @Date 2020/3/19 16:43
 * @Version 1.0
 * 用户微服务
 */
@SpringBootApplication
public class UserApplication {
    public static void main(String[] args) {
        SpringApplication.run(UserApplication.class,args);
    }

    @Bean
    public SnowflakeUtil snowflakeUtil(){
        return new SnowflakeUtil(1,1);
    }

    /**
     * 加密算法
     * @return
     */
    @Bean
    public BCryptPasswordEncoder encoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public JwtUtil jwtUtil(){
        return new JwtUtil();
    }
}
