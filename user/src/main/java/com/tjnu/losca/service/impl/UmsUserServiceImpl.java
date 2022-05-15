package com.tjnu.losca.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tjnu.losca.mapper.UmsUserMapper;
import com.tjnu.losca.pojo.UmsUser;
import com.tjnu.losca.service.IFileService;
import com.tjnu.losca.service.IUmsUserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author losca
 * @since 2022-01-22
 */
@Service
public class UmsUserServiceImpl extends ServiceImpl<UmsUserMapper, UmsUser> implements IUmsUserService {

    @Autowired
    BCryptPasswordEncoder passwordEncoder;
    @Autowired
    IFileService fileService;
   public boolean check(Integer id, String value) {
      QueryWrapper<UmsUser> wrapper = new QueryWrapper<UmsUser>();
      wrapper.eq("phone",value).or()
              .eq("email",value);
      if(id != null) {
         wrapper.ne("id", id);
      }
      return this.count(wrapper) == 0;
 }


    public List<UmsUser> list(Integer active) {
        QueryWrapper<UmsUser> wrapper = new QueryWrapper<UmsUser>();
        if (active == null) {
            wrapper.eq("active", active);
        }
        return this.list(wrapper);
    }

    public UmsUser login(String username, String password) throws Exception {
        QueryWrapper<UmsUser> wrapper = new QueryWrapper<UmsUser>();
        wrapper.eq("phone",username)
                .or().eq("email",username);
        UmsUser umsUser = this.getOne(wrapper);
        if (umsUser == null || !passwordEncoder.matches(password,umsUser.getPassword())){
            throw new Exception("用户名或密码错误");
        }
        if (umsUser.getActive() == 0){
            throw new Exception("失效用户,请联系管理员");
        }
        return umsUser;
    }

    public boolean add(UmsUser entry, MultipartFile file, String rawPassword) {
        entry.setIcon(fileService.upload(file,"img").getObj());
        entry.setPassword(passwordEncoder.encode(rawPassword));
        return this.save(entry);
    }

    public boolean update(UmsUser entry, MultipartFile file) {
        if (file != null && file.getSize() > 0){
            entry.setIcon(fileService.upload(file,"img").getObj());
        }
        return this.updateById(entry);
    }

    public IPage<UmsUser> list(int pageNo, int pageSize, String value) {
      QueryWrapper<UmsUser> wrapper = new QueryWrapper<UmsUser>();
      if(StringUtils.isNotBlank(value)) {
         wrapper.like("name",value);
      }
      return this.page(new Page<UmsUser>(pageNo,pageSize),wrapper);
 }
}
