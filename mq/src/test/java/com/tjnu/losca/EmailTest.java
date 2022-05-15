package com.tjnu.losca;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author Losca
 * @date 2022/2/5 12:11
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = MqApp.class)
public class EmailTest {
    @Autowired
    JavaMailSender mailSender;
    @Test
    public void test(){
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom("783840358@qq.com");
        simpleMailMessage.setTo("783840358@qq.com");
        simpleMailMessage.setText("测试");
        simpleMailMessage.setSubject("系统消息");
        mailSender.send(simpleMailMessage);
    }
}
