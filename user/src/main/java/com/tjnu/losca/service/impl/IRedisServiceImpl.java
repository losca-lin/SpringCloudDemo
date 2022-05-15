package com.tjnu.losca.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.tjnu.losca.pojo.UmsResource;
import com.tjnu.losca.service.IRedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @author Losca
 * @date 2022/2/8 17:11
 */
@Service
public class IRedisServiceImpl implements IRedisService {
    @Autowired
    RedisTemplate<String, String> redisTemplate;
    @Override
    public void saveResources(List<UmsResource> resources, Long userId) {
    //    获取后端权限地址
        Set<String> backUrls = getBackUrls(resources);
    //    保存到缓存服务器中 每一级用:分隔 30分钟后失效
        redisTemplate.opsForValue().set("umsresource:"+userId+":user", JSONObject.toJSONString(backUrls),30, TimeUnit.MINUTES);
    }

    /**
     * set 数据不能重复
     * @param resources
     * @return
     */
    private Set<String> getBackUrls(List<UmsResource> resources){
        Set<String> backUrls = new HashSet<>();
        for (UmsResource resource : resources) {
            if (resource.getType() == 1){
                backUrls.add(resource.getBackUrl());
            }
        }
        return backUrls;
    }
}
