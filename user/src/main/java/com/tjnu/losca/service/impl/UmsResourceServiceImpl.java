package com.tjnu.losca.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tjnu.losca.mapper.UmsResourceMapper;
import com.tjnu.losca.pojo.UmsResource;
import com.tjnu.losca.pojo.UmsRole;
import com.tjnu.losca.pojo.UmsRoleResource;
import com.tjnu.losca.pojo.UmsRoleUser;
import com.tjnu.losca.service.IUmsResourceService;
import com.tjnu.losca.service.IUmsRoleResourceService;
import com.tjnu.losca.service.IUmsRoleService;
import com.tjnu.losca.service.IUmsRoleUserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 权限列表 服务实现类
 * </p>
 *
 * @author losca
 * @since 2022-02-05
 */
@Service
public class UmsResourceServiceImpl extends ServiceImpl<UmsResourceMapper, UmsResource> implements IUmsResourceService {

    @Autowired
    IUmsRoleUserService roleUserService;
    @Autowired
    IUmsRoleService roleService;
    @Autowired
    IUmsRoleResourceService roleResourceService;
   public boolean check(Integer id, String value) {
      QueryWrapper<UmsResource> wrapper = new QueryWrapper<UmsResource>();
      wrapper.eq("name",value);
      if(id != null) {
         wrapper.ne("id", id);
      }
      return this.count(wrapper) == 0;
 }


   public IPage<UmsResource> list(int pageNo, int pageSize, String value) {
      QueryWrapper<UmsResource> wrapper = new QueryWrapper<UmsResource>();
      if(StringUtils.isNotBlank(value)) {
         wrapper.like("name",value);
      }
      return this.page(new Page<UmsResource>(pageNo,pageSize),wrapper);
 }

    public List<UmsResource> getByParentId(Long parentId) {
        QueryWrapper<UmsResource> wrapper = new QueryWrapper<UmsResource>();
        wrapper.eq("parent_id", parentId);
        List<UmsResource> list = this.list(wrapper);
        for (UmsResource umsResource : list) {
            umsResource.setChildren(getByParentId(umsResource.getId()));
        }
        return list;
    }

    public List<UmsResource> getByUserId(Long userId) {
        //查询用户关联的角色id
        List<Long> roleIds = new ArrayList<Long>();
        QueryWrapper<UmsRoleUser> roleUserQueryWrapper = new QueryWrapper<UmsRoleUser>();
        roleUserQueryWrapper.eq("user_id", userId);
        List<UmsRoleUser> roleUsers = roleUserService.list(roleUserQueryWrapper);
        for (UmsRoleUser roleUser : roleUsers) {
            roleIds.add(roleUser.getRoleId());
        }
        //查询关联角色id中active=1的角色
        List<Long> activeRoleIds = new ArrayList<>();
        QueryWrapper<UmsRole> umsRoleQueryWrapper = new QueryWrapper<>();
        umsRoleQueryWrapper.eq("active", 1)
                .in("id", roleIds);
        List<UmsRole> roles = roleService.list(umsRoleQueryWrapper);
        for (UmsRole role : roles) {
            activeRoleIds.add(role.getId());
        }
        //通过角色查询资源id
        List<Long> resourceIds = new ArrayList<>();
        QueryWrapper<UmsRoleResource> umsRoleResourceQueryWrapper = new QueryWrapper<>();
        umsRoleResourceQueryWrapper.in("role_id", activeRoleIds);
        List<UmsRoleResource> roleResource = roleResourceService.list(umsRoleResourceQueryWrapper);
        for (UmsRoleResource umsRoleResource : roleResource) {
            resourceIds.add(umsRoleResource.getResourceId());
        }
        //通过资源id获取响应的资源
        QueryWrapper<UmsResource> umsResourceQueryWrapper = new QueryWrapper<>();
        umsResourceQueryWrapper.in("id", resourceIds);
        return this.list(umsResourceQueryWrapper);
    }

    public List<UmsResource> getMenu(List<UmsResource> resources) {
        List<UmsResource> menu = new ArrayList<>();
        for (UmsResource resource : resources) {
            if (resource.getParentId() == 0){
                menu.add(resource);
                continue;
            }
            //上级不为0但是type为0 找上级菜单
            if(resource.getType() == 0){
                for (UmsResource parent : resources) {
                    if (resource.getParentId() == parent.getId()) {
                        parent.getChildren().add(resource);
                        break;
                    }
                }
            }
        //    如果type = 1再看
        }
        return menu;
    }
}
