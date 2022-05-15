package com.tjnu.losca.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.tjnu.losca.pojo.UmsUser;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author losca
 * @since 2022-01-22
 */
public interface IUmsUserService extends IService<UmsUser> {
    boolean check(Integer id, String value);
    IPage<UmsUser> list(int pageNo, int pageSize, String value);
    //查询有效用户信息
    List<UmsUser> list(Integer active);

    UmsUser login(String username, String password) throws Exception;
    boolean add(UmsUser entry, MultipartFile file, String rawPassword);
    boolean update(UmsUser entry,MultipartFile file);
}
