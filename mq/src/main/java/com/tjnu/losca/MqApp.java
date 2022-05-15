package com.tjnu.losca;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author Losca
 * @date 2022/2/4 21:20
 */
@SpringBootApplication
@EnableDiscoveryClient
public class MqApp {
    public static void main(String[] args) {
        SpringApplication.run(MqApp.class);
    }
}
