package com.tjnu.losca.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Component;

/**
 * @author Losca
 * @date 2022/2/8 21:57
 */
@Component
public class GatewayConfig {
    @Autowired
    RedisTemplate<String, String> redisTemplate;
    @Bean
    RedisTemplate getRedisTemplate(){
        StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();
        redisTemplate.setKeySerializer(stringRedisSerializer);
        redisTemplate.setValueSerializer(stringRedisSerializer);
        return redisTemplate;
    }
}
