package com.tjnu.losca;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author Losca
 * @date 2022/1/1 21:19
 */
@SpringBootApplication
@EnableDiscoveryClient
//开启openfeign的客户端
@EnableFeignClients
@MapperScan("com.tjnu.losca.mapper")
public class UserApp {
    public static void main(String[] args) {
        SpringApplication.run(UserApp.class);
    }
}
