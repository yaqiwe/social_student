package com.yaqiwe;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import util.SnowflakeUtil;

/**
 * @Author yaqiwe
 * @Date 2020/3/19 14:35
 * @Version 1.0
 * 活动微服务
 */
@SpringBootApplication
@EnableCaching
public class GatheringApplication {

    public static void main(String[] args) {
        SpringApplication.run(GatheringApplication.class,args);
    }

    @Bean
    public SnowflakeUtil snowflakeUtil(){
        return new SnowflakeUtil(1,1);
    }
}
