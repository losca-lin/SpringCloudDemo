package com.tjnu.losca;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.AntPathMatcher;

import java.util.concurrent.TimeUnit;

/**
 * @author Losca
 * @date 2022/2/4 20:29
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = UserApp.class)
public class MyTest {
    @Autowired
    RabbitTemplate rabbitTemplate;
    @Autowired
    RedisTemplate<String, String> redisTemplate;

    @Test
    public void handler(){
        rabbitTemplate.convertAndSend("email","发送消息");
        System.out.println("发送成功");
    }
    @Test
    public void redisTest(){
        redisTemplate.opsForValue().set("a","123",60, TimeUnit.SECONDS);
        System.out.println(redisTemplate.opsForValue().get("a"));
    }
    @Test
    public void test(){
        AntPathMatcher pathMatcher = new AntPathMatcher();
        boolean match = pathMatcher.match("/ums-patter/**", "/ums-patter/");
        System.out.println(match);
    }

}
