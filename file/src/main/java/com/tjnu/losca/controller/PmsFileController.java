package com.tjnu.losca.controller;

import com.tjnu.losca.core.ResultJson;
import com.tjnu.losca.service.IPmsFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * <p>
 * 文件表 前端控制器
 * </p>
 *
 * @author losca
 * @since 2022-01-29
 */
@RestController
@RequestMapping("/pms-file")
public class PmsFileController {
    @Autowired
    IPmsFileService service;
    @PostMapping("/upload")
    ResultJson<String> upload(@RequestPart("file") MultipartFile file, @RequestParam("bucket") String bucket) throws Exception {
        return ResultJson.success(service.upload(file,bucket));
    }

}
