package com.tjnu.losca.config;

import com.alibaba.fastjson.JSONObject;
import com.tjnu.losca.core.EmailMessage;
import com.tjnu.losca.core.JWTUtil;
import com.tjnu.losca.core.ResultCode;
import com.tjnu.losca.core.ResultJson;
import com.tjnu.losca.pojo.UmsResource;
import com.tjnu.losca.pojo.UmsUser;
import com.tjnu.losca.service.IRedisService;
import com.tjnu.losca.service.IUmsResourceService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;

/**
 * @author Losca
 * @date 2022/2/5 11:04
 */
@Component
@Aspect
public class UserAop {
    @Autowired
    RabbitTemplate rabbitTemplate;
    @Autowired
    IUmsResourceService resourceService;
    @Autowired
    IRedisService redisService;
    @AfterReturning(value = "execution(* com.tjnu.losca.controller.UmsUserController.add(..))")
    public void sendMail(JoinPoint joinPoint){
        Object[] args = joinPoint.getArgs();
        String password = args[2].toString();
        UmsUser umsUser = (UmsUser) args[0];
        EmailMessage emailMessage = new EmailMessage(umsUser.getEmail()
                ,"系统为您创建了用户，登陆名为手机号或者邮箱，密码为："+password
        ,"系统消息");
        rabbitTemplate.convertAndSend("email", JSONObject.toJSONString(emailMessage));


    }
    @Around(value = "execution(* com.tjnu.losca.controller.UmsUserController.login(..))")
    public ResultJson login(ProceedingJoinPoint joinPoint) throws Throwable {
        HashMap<String, Object> map = new HashMap<>();
        //拿到方法的返回值
        ResultJson obj = (ResultJson)joinPoint.proceed();
        //拿到返回对象
        UmsUser umsUser = (UmsUser)obj.getObj();
        String token = JWTUtil.create(umsUser);
        //通过id查询所有权限
        List<UmsResource> source = resourceService.getByUserId(umsUser.getId());
        //获取菜单
        List<UmsResource> menu = resourceService.getMenu(source);
        map.put("token", token);
        map.put("menu", menu);
        //保存到缓存服务器中
        redisService.saveResources(source,umsUser.getId());
        ResultJson result = ResultJson.getInstance(ResultCode.SUCCESS, map, obj.getMessage());
        return result;

    }

}
