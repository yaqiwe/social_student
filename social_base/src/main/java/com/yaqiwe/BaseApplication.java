package com.yaqiwe;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import util.SnowflakeUtil;

/**
 * @Author yaqiwe
 * @Date 2020/3/15 16:04
 * @Version 1.0
 */
@SpringBootApplication
public class BaseApplication {

    public static void main(String[] args) {
        SpringApplication.run(BaseApplication.class,args);
    }

    /**
     * 将雪花算法加到容器中
     */
    @Bean
    public SnowflakeUtil snowflakeUtil(){
        return new SnowflakeUtil(1,1);
    }
}
