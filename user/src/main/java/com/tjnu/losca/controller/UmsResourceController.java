package com.tjnu.losca.controller;

import org.springframework.beans.factory.annotation.Autowired;
import com.tjnu.losca.core.ResultJson;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import com.tjnu.losca.pojo.UmsResource;
import com.tjnu.losca.service.IUmsResourceService;
import com.baomidou.mybatisplus.core.metadata.IPage;


import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 权限列表 前端控制器
 * </p>
 *
 * @author losca
 * @since 2022-02-05
 */
@RestController
@RequestMapping("/ums-resource")
public class UmsResourceController {
    @Autowired
    IUmsResourceService service;
    @GetMapping("/list")
    ResultJson<IPage<UmsResource>> list(int pageNo, int pageSize, String value) {
        return ResultJson.success(service.list(pageNo,pageSize,value));
 }
    @GetMapping("/check")
    ResultJson<Boolean> check(Integer id, String value) {
        return ResultJson.success(service.check(id,value));
  }
    @PostMapping("/add")
    ResultJson<Boolean> add(UmsResource entry) {
        return ResultJson.success(service.save(entry),"添加成功");
   }
    @GetMapping("/getone")
    ResultJson<UmsResource> getone(Integer id) {
        return ResultJson.success(service.getById(id));
   }
    @PostMapping("/update")
    ResultJson<Boolean> update(UmsResource entry) {
        return ResultJson.success(service.updateById(entry),"修改成功");
    }


}
