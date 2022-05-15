package com.tjnu.losca.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tjnu.losca.mapper.PmsFileMapper;
import com.tjnu.losca.pojo.PmsFile;
import com.tjnu.losca.service.IPmsFileService;
import io.minio.MinioClient;
import io.minio.PutObjectOptions;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * <p>
 * 文件表 服务实现类
 * </p>
 *
 * @author losca
 * @since 2022-01-29
 */
@Service
public class PmsFileServiceImpl extends ServiceImpl<PmsFileMapper, PmsFile> implements IPmsFileService {
    @Value("${minio.url}")
    private String minioUrl;
    @Value("${minio.username}")
    private String minioUsername;
    @Value("${minio.password}")
    private String minioPassword;

    public String upload(MultipartFile file, String bucket) throws Exception {
        // 获取文件 md5 大小 后缀
        String md5 = DigestUtils.md5Hex(file.getInputStream());
        long size = file.getSize();
        String suffix = FilenameUtils.getExtension(file.getOriginalFilename());
        // 到数据库中查看 是否存在 md5 大小 后缀相同的文件
        PmsFile pmsFile = getPmsFile(md5, size, suffix);
        // 如果能查到 说明上传过 直接返回曾经上传的路径
        if (pmsFile != null) {
            return pmsFile.getPath();
        }
        // 如果查不到说明没上传过 所以需要上传
        return uploadToMinio(file,bucket,size,suffix,md5);
    }
    private PmsFile getPmsFile(String md5, long size, String suffix) {
        QueryWrapper<PmsFile> wrapper = new QueryWrapper<PmsFile>();
        wrapper.eq("md5",md5)
                .eq("size",size)
                .eq("suffix",suffix);
        return this.getOne(wrapper);
    }
    private String uploadToMinio(MultipartFile file, String bucket, long size, String suffix, String md5) throws Exception{
        StringBuilder builder = new StringBuilder(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS")));
        // 拼接随机数
        builder.append(RandomStringUtils.random(6,false,true));
        // 拼接后缀
        builder.append(".").append(suffix);
        MinioClient minioClient = new MinioClient(minioUrl, minioUsername, minioPassword);
        //图片是否分块
        PutObjectOptions options = new PutObjectOptions(size,0);
        //设置文件格式，图片能够被在线预览
        options.setContentType(file.getContentType());
        // 使用putObject上传一个文件到存储桶中。
        minioClient.putObject("img",builder.toString(),file.getInputStream(),options);
        // 上传后保存到数据库中
        PmsFile pmsFile = new PmsFile(md5
                ,size
                ,suffix
                ,bucket + "/" + builder.toString()
        );
        this.save(pmsFile);
        //返回图片地址
        return bucket + "/" + builder.toString();
    }
}
