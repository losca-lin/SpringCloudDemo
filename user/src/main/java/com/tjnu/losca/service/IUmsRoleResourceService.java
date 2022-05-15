package com.tjnu.losca.service;

import com.tjnu.losca.pojo.UmsRoleResource;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.core.metadata.IPage;
/**
 * <p>
 * 角色权限列表 服务类
 * </p>
 *
 * @author losca
 * @since 2022-02-05
 */
public interface IUmsRoleResourceService extends IService<UmsRoleResource> {
    boolean check(Integer id, String value);
    IPage<UmsRoleResource> list(int pageNo, int pageSize, String value);
}
