package com.tjnu.losca.service;

import com.tjnu.losca.pojo.UmsResource;

import java.util.List;

/**
 * @author Losca
 * @date 2022/2/8 17:09
 */
public interface IRedisService {
    void saveResources(List<UmsResource> resources, Long userId);
}
