package com.tjnu.losca.controller;

import com.tjnu.losca.core.ResultJson;
import com.tjnu.losca.pojo.PmsCategory;
import com.tjnu.losca.service.IPmsCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 分类表 前端控制器
 * </p>
 *
 * @author losca
 * @since 2022-02-10
 */
@RestController
@RequestMapping("/pms-category")
public class PmsCategoryController {
    @Autowired
    IPmsCategoryService service;
    @GetMapping("/list")
    ResultJson<List<PmsCategory>> list(int pageNo, int pageSize, String value) {
        return ResultJson.success(service.getAll());
 }
    @GetMapping("/check")
    ResultJson<Boolean> check(Integer id, String value) {
        return ResultJson.success(service.check(id,value));
  }
    @PostMapping("/add")
    @CacheEvict(value = "product",key = "'categorys'")
    public ResultJson<Boolean> add(PmsCategory entry) {
        return ResultJson.success(service.save(entry),"添加成功");
   }
    @GetMapping("/getone")
    ResultJson<PmsCategory> getone(Integer id) {
        return ResultJson.success(service.getById(id));
   }
    @PostMapping("/update")
    @CacheEvict(value = "product",key = "'categorys'")
    public ResultJson<Boolean> update(PmsCategory entry) {
        return ResultJson.success(service.updateById(entry),"修改成功");
    }
    @PostMapping("/del")
    @CacheEvict(value = "product",key = "'categorys'")
    public ResultJson<Boolean> del(PmsCategory entry) {
        return ResultJson.success(service.updateById(entry),entry.getActive() == 0 ? "删除成功" : "恢复成功");
     }

}
