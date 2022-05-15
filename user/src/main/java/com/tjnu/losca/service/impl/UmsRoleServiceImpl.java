package com.tjnu.losca.service.impl;

import com.tjnu.losca.pojo.UmsRole;
import com.tjnu.losca.mapper.UmsRoleMapper;
import com.tjnu.losca.service.IUmsRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.apache.commons.lang3.StringUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
/**
 * <p>
 * 角色表 服务实现类
 * </p>
 *
 * @author losca
 * @since 2022-02-01
 */
@Service
public class UmsRoleServiceImpl extends ServiceImpl<UmsRoleMapper, UmsRole> implements IUmsRoleService {

   public boolean check(Integer id, String value) {
      QueryWrapper<UmsRole> wrapper = new QueryWrapper<UmsRole>();
      wrapper.eq("phone",value).or()
              .eq("email",value);
      if(id != null) {
         wrapper.ne("id", id);
      }
      return this.count(wrapper) == 0;
 }


   public IPage<UmsRole> list(int pageNo, int pageSize, String value) {
      QueryWrapper<UmsRole> wrapper = new QueryWrapper<UmsRole>();
      if(StringUtils.isNotBlank(value)) {
         wrapper.like("name",value);
      }
      return this.page(new Page<UmsRole>(pageNo,pageSize),wrapper);
 }
}
