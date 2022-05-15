package com.tjnu.losca.controller;

import org.springframework.beans.factory.annotation.Autowired;
import com.tjnu.losca.core.ResultJson;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import com.tjnu.losca.pojo.PmsSku;
import com.tjnu.losca.service.IPmsSkuService;


import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * sku属性 前端控制器
 * </p>
 *
 * @author losca
 * @since 2022-02-10
 */
@RestController
@RequestMapping("/pms-sku")
public class PmsSkuController {
    @Autowired
    IPmsSkuService service;
    @GetMapping("/list")
    ResultJson<List<PmsSku>> list(Long categoryId) {
        return ResultJson.success(service.list(categoryId));
 }
    @GetMapping("/check")
    ResultJson<Boolean> check(Integer id, String value) {
        return ResultJson.success(service.check(id,value));
  }
    @PostMapping("/add")
    ResultJson<Boolean> add(PmsSku entry) {
        return ResultJson.success(service.save(entry),"添加成功");
   }
    @GetMapping("/getone")
    ResultJson<PmsSku> getone(Integer id) {
        return ResultJson.success(service.getById(id));
   }
    @PostMapping("/update")
    ResultJson<Boolean> update(PmsSku entry) {
        return ResultJson.success(service.updateById(entry),"修改成功");
    }


}
