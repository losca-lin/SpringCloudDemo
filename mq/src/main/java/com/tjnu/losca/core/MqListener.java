package com.tjnu.losca.core;

import com.alibaba.fastjson.JSONObject;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import java.nio.charset.Charset;

/**
 * @author Losca
 * @date 2022/2/4 21:37
 */
@Component
public class MqListener {
    @Value("${spring.mail.username}")
    private String from;
    @Autowired
    JavaMailSender mailSender;
    @RabbitListener(queues = "email")
    public void getMessage(Message message){
        try {
            String str = new String(message.getBody(), Charset.forName("UTF-8"));
            EmailMessage emailMessage = JSONObject.parseObject(str, EmailMessage.class);
            SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
            simpleMailMessage.setFrom(from);
            simpleMailMessage.setTo(emailMessage.getTo());
            simpleMailMessage.setText(emailMessage.getText());
            simpleMailMessage.setSubject(emailMessage.getSubject());
            mailSender.send(simpleMailMessage);
        } catch (MailException e) {
            e.printStackTrace();
            System.out.println(message);
        }
    }
}
