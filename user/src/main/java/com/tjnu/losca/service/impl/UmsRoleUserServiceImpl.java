package com.tjnu.losca.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tjnu.losca.mapper.UmsRoleUserMapper;
import com.tjnu.losca.pojo.UmsRoleUser;
import com.tjnu.losca.service.IUmsRoleUserService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 角色用户关联表 服务实现类
 * </p>
 *
 * @author losca
 * @since 2022-02-04
 */
@Service
public class UmsRoleUserServiceImpl extends ServiceImpl<UmsRoleUserMapper, UmsRoleUser> implements IUmsRoleUserService {


    /**
     * 保存前先删除
     * @param roleId
     * @param userIds
     * @return
     */
    public Boolean save(Long roleId, Long[] userIds) {
        QueryWrapper<UmsRoleUser> umsRoleUserQueryWrapper = new QueryWrapper<UmsRoleUser>();
        umsRoleUserQueryWrapper.eq("role_id",roleId);
        this.remove(umsRoleUserQueryWrapper);
        Boolean flag = false;
        if (userIds != null) {
            List<UmsRoleUser> list = new ArrayList<UmsRoleUser>();
            for (Long userId : userIds) {
                UmsRoleUser umsRoleUser = new UmsRoleUser(roleId, userId);
                list.add(umsRoleUser);
            }
            flag = this.saveBatch(list);
        }
        return flag;
    }

    public List<UmsRoleUser> getByRoleId(Long roleId) {
        QueryWrapper<UmsRoleUser> umsRoleUserQueryWrapper = new QueryWrapper<UmsRoleUser>();
        umsRoleUserQueryWrapper.eq("role_id", roleId);
        return this.list(umsRoleUserQueryWrapper);
    }
}
