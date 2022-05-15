package com.tjnu.losca.controller;

import org.springframework.beans.factory.annotation.Autowired;
import com.tjnu.losca.core.ResultJson;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import com.tjnu.losca.pojo.UmsRole;
import com.tjnu.losca.service.IUmsRoleService;
import com.baomidou.mybatisplus.core.metadata.IPage;


import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 角色表 前端控制器
 * </p>
 *
 * @author losca
 * @since 2022-02-01
 */
@RestController
@RequestMapping("/ums-role")
public class UmsRoleController {
    @Autowired
    IUmsRoleService service;
    @GetMapping("/list")
    ResultJson<IPage<UmsRole>> list(int pageNo, int pageSize, String value) {
        return ResultJson.success(service.list(pageNo,pageSize,value));
 }
    @GetMapping("/check")
    ResultJson<Boolean> check(Integer id, String value) {
        return ResultJson.success(service.check(id,value));
  }
    @PostMapping("/add")
    ResultJson<Boolean> add(UmsRole entry) {
        return ResultJson.success(service.save(entry),"添加成功");
   }
    @GetMapping("/getone")
    ResultJson<UmsRole> getone(Integer id) {
        return ResultJson.success(service.getById(id));
   }
    @PostMapping("/update")
    ResultJson<Boolean> update(UmsRole entry) {
        return ResultJson.success(service.updateById(entry),"修改成功");
    }
    @PostMapping("/del")
    ResultJson<Boolean> del(UmsRole entry) {
        return ResultJson.success(service.updateById(entry),entry.getActive() == 0 ? "删除成功" : "恢复成功");
     }

}
