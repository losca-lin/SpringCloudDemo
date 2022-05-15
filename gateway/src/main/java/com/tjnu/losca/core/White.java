package com.tjnu.losca.core;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author Losca
 * @date 2022/2/8 22:48
 */
@ConfigurationProperties("lin.ignore")
@Component
public class White {
    private List<String> urls;

    public List<String> getUrls() {
        return urls;
    }

    public void setUrls(List<String> urls) {
        this.urls = urls;
    }
}
