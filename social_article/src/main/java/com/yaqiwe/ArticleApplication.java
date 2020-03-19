package com.yaqiwe;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import util.SnowflakeUtil;

/**
 * @Author yaqiwe
 * @Date 2020/3/18 16:01
 * @Version 1.0
 * 文章微服务
 */
@SpringBootApplication
public class ArticleApplication {
    public static void main(String[] args) {
        SpringApplication.run(ArticleApplication.class,args);
    }

    @Bean
    public SnowflakeUtil snowflakeUtil(){
        return new SnowflakeUtil(4,1);
    }
}
