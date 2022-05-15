package com.tjnu.losca.config;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.extension.plugins.pagination.optimize.JsqlParserCountOptimize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @author Losca
 * @date 2022/1/13 16:35
 */
@Configuration
public class UserConfig {
    //@Bean
    ////从注册中心拿
    //@LoadBalanced
    //RestTemplate getRestTemplate(){
    //    return new RestTemplate();
    //}
    @Autowired
    RedisTemplate<String,String> redisTemplate;
    @Bean
    BCryptPasswordEncoder getBCryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }
    //开启分页
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        PaginationInterceptor paginationInterceptor = new PaginationInterceptor();
        paginationInterceptor.setCountSqlParser(new JsqlParserCountOptimize(true));
        return paginationInterceptor;
    }
    //防止redis乱码
    @Bean
    RedisTemplate getRedisTemplate(){
        StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();
        redisTemplate.setKeySerializer(stringRedisSerializer);
        redisTemplate.setValueSerializer(stringRedisSerializer);
        return redisTemplate;
    }
    //配置跨域 后期gateway统一管控
    //@Bean
    //public CorsFilter getCorsFilter(){
    //    CorsConfiguration configuration = new CorsConfiguration();
    //    //允许任何域名
    //    configuration.addAllowedOrigin("*");
    //    //允许任何方法
    //    configuration.addAllowedMethod("*");
    //    //允许任何请求头
    //    configuration.addAllowedHeader("*");
    //    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    //    source.registerCorsConfiguration("/**",configuration);
    //    return new CorsFilter(source);
    //}
}
