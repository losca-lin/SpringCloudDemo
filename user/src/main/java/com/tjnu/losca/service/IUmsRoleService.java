package com.tjnu.losca.service;

import com.tjnu.losca.pojo.UmsRole;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.core.metadata.IPage;
/**
 * <p>
 * 角色表 服务类
 * </p>
 *
 * @author losca
 * @since 2022-02-01
 */
public interface IUmsRoleService extends IService<UmsRole> {
    boolean check(Integer id, String value);
    IPage<UmsRole> list(int pageNo, int pageSize, String value);
}
