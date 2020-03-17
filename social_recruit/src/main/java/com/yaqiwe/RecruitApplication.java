package com.yaqiwe;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import util.SnowflakeUtil;

/**
 * @Author yaqiwe
 * @Date 2020/3/16 19:30
 * @Version 1.0
 */
@SpringBootApplication
public class RecruitApplication {

    public static void main(String[] args) {
        SpringApplication.run(RecruitApplication.class, args);
    }

    @Bean
    public SnowflakeUtil snowflakeUtil(){
        return new SnowflakeUtil(2,1);
    }
}
