package com.tjnu.losca.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.tjnu.losca.core.ResultJson;
import com.tjnu.losca.pojo.UmsUser;
import com.tjnu.losca.service.IUmsUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author losca
 * @since 2022-01-22
 */
@RestController
@RequestMapping("/ums-user")
public class UmsUserController {
    @Autowired
    IUmsUserService service;

    @GetMapping("/list")
    ResultJson<IPage<UmsUser>> list(int pageNo, int pageSize, String value, Long userId) {
        System.out.println(userId);
        return ResultJson.success(service.list(pageNo,pageSize,value));
 }
    @GetMapping("/check")
    ResultJson<Boolean> check(Integer id, String value) {
        return ResultJson.success(service.check(id,value));
  }
    @PostMapping("/add")
    ResultJson<Boolean> add(UmsUser entry, MultipartFile file,String rawPassword) {
        return ResultJson.success(service.add(entry,file,rawPassword),"添加成功");
   }
    @GetMapping("/getone")
    ResultJson<UmsUser> getone(Integer id) {
        return ResultJson.success(service.getById(id));
   }
    @PostMapping("/update")
    ResultJson<Boolean> update(UmsUser entry,MultipartFile file) {

        return ResultJson.success(service.update(entry,file),"修改成功");
    }
    @PostMapping("/del")
    ResultJson<Boolean> del(UmsUser entry) {
        return ResultJson.success(service.updateById(entry),entry.getActive() == 0 ? "删除成功" : "恢复成功");
     }
     @PostMapping("/login")
    ResultJson login(String username,String password) throws Exception {
         return ResultJson.success(service.login(username, password));
     }

}
