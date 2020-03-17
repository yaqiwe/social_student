package com.yaqiwe;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import util.SnowflakeUtil;

/**
 * @Author yaqiwe
 * @Date 2020/3/17 15:48
 * @Version 1.0
 */
@SpringBootApplication
public class QaApplication {

    public static void main(String[] args) {
        SpringApplication.run(QaApplication.class,args);
    }

    @Bean
    public SnowflakeUtil snowflakeUtil(){
        return new SnowflakeUtil(3,1);
    }
}
