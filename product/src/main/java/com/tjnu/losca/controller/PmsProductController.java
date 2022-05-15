package com.tjnu.losca.controller;

import org.springframework.beans.factory.annotation.Autowired;
import com.tjnu.losca.core.ResultJson;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import com.tjnu.losca.pojo.PmsProduct;
import javax.annotation.Resource;
import com.tjnu.losca.service.IPmsProductService;
import com.baomidou.mybatisplus.core.metadata.IPage;


import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 商品表 前端控制器
 * </p>
 *
 * @author losca
 * @since 2022-02-10
 */
@RestController
@RequestMapping("/pms-product")
public class PmsProductController {
    @Autowired
    IPmsProductService service;
    @GetMapping("/list")
    ResultJson<IPage> list(int pageNo, int pageSize, String value) {
        return ResultJson.success(service.list(pageNo,pageSize,value));
 }
    @GetMapping("/check")
    ResultJson<Boolean> check(Integer id, String value) {
        return ResultJson.success(service.check(id,value));
  }
    @PostMapping("/add")
    ResultJson<Boolean> add(PmsProduct entry) {
        return ResultJson.success(service.save(entry),"添加成功");
   }
    @GetMapping("/getone")
    ResultJson<PmsProduct> getone(Integer id) {
        return ResultJson.success(service.getById(id));
   }
    @PostMapping("/update")
    ResultJson<Boolean> update(PmsProduct entry) {
        return ResultJson.success(service.updateById(entry),"修改成功");
    }
    @PostMapping("/del")
    ResultJson<Boolean> del(PmsProduct entry) {
        return ResultJson.success(service.updateById(entry),entry.getActive() == 0 ? "删除成功" : "恢复成功");
     }

}
