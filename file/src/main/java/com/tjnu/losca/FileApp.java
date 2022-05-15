package com.tjnu.losca;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author Losca
 * @date 2022/1/13 17:04
 */
@SpringBootApplication
@EnableDiscoveryClient
@MapperScan("com.tjnu.losca.mapper")
public class FileApp {
    public static void main(String[] args) {
        SpringApplication.run(FileApp.class);
    }
}
