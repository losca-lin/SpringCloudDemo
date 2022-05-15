package com.tjnu.losca.service;

import com.tjnu.losca.core.ResultJson;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author Losca
 * @date 2022/2/1 14:39
 */
@FeignClient("file")
public interface IFileService {
    @RequestMapping(value = "/pms-file/upload",method = RequestMethod.POST,consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    ResultJson<String> upload(@RequestPart("file") MultipartFile file, @RequestParam("bucket") String bucket);
}
