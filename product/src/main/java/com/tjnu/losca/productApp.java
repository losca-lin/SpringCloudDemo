package com.tjnu.losca;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author Losca
 * @date 2022/1/1 21:16
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableCaching
@MapperScan("com.tjnu.losca.mapper")
public class productApp {
    public static void main(String[] args) {
       SpringApplication.run(productApp.class);
    }
}
