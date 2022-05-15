package com.tjnu.losca.controller;

import com.tjnu.losca.core.ResultJson;
import com.tjnu.losca.service.IUmsRoleUserService;
import com.tjnu.losca.service.IUmsUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 角色用户关联表 前端控制器
 * </p>
 *
 * @author losca
 * @since 2022-02-04
 */
@RestController
@RequestMapping("/ums-role-user")
public class UmsRoleUserController {
    @Autowired
    IUmsRoleUserService service;
    @Autowired
    IUmsUserService userService;
    @GetMapping("/getData")
    ResultJson<Map<String, List>> list(Long roleId,Integer active) {
        Map<String,List> map = new HashMap();
        map.put("users", userService.list(active));
        map.put("values", service.getByRoleId(roleId));
        return ResultJson.success(map);
    }
    @PostMapping("/save")
    ResultJson<Boolean> save(Long roleId,Long[] userIds){
        return ResultJson.success(service.save(roleId, userIds),"用户与角色关联成功");
    }

}
