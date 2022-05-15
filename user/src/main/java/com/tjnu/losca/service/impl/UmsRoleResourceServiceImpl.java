package com.tjnu.losca.service.impl;

import com.tjnu.losca.pojo.UmsRoleResource;
import com.tjnu.losca.mapper.UmsRoleResourceMapper;
import com.tjnu.losca.service.IUmsRoleResourceService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.apache.commons.lang3.StringUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
/**
 * <p>
 * 角色权限列表 服务实现类
 * </p>
 *
 * @author losca
 * @since 2022-02-05
 */
@Service
public class UmsRoleResourceServiceImpl extends ServiceImpl<UmsRoleResourceMapper, UmsRoleResource> implements IUmsRoleResourceService {

   public boolean check(Integer id, String value) {
      QueryWrapper<UmsRoleResource> wrapper = new QueryWrapper<UmsRoleResource>();
      wrapper.eq("name",value);
      if(id != null) {
         wrapper.ne("id", id);
      }
      return this.count(wrapper) == 0;
 }


   public IPage<UmsRoleResource> list(int pageNo, int pageSize, String value) {
      QueryWrapper<UmsRoleResource> wrapper = new QueryWrapper<UmsRoleResource>();
      if(StringUtils.isNotBlank(value)) {
         wrapper.like("name",value);
      }
      return this.page(new Page<UmsRoleResource>(pageNo,pageSize),wrapper);
 }
}
