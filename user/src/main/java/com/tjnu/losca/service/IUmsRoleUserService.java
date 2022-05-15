package com.tjnu.losca.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.tjnu.losca.pojo.UmsRoleUser;

import java.util.List;

/**
 * <p>
 * 角色用户关联表 服务类
 * </p>
 *
 * @author losca
 * @since 2022-02-04
 */
public interface IUmsRoleUserService extends IService<UmsRoleUser> {
  Boolean save(Long roleId,Long[] userIds);

  List<UmsRoleUser> getByRoleId(Long roleId);
}
