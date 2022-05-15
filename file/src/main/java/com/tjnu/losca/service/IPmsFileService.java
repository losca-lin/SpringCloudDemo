package com.tjnu.losca.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.tjnu.losca.pojo.PmsFile;
import org.springframework.web.multipart.MultipartFile;

/**
 * <p>
 * 文件表 服务类
 * </p>
 *
 * @author losca
 * @since 2022-01-29
 */
public interface IPmsFileService extends IService<PmsFile> {
    String upload(MultipartFile file, String bucket) throws Exception;
}
