package com.tjnu.losca.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tjnu.losca.mapper.PmsCategoryMapper;
import com.tjnu.losca.pojo.PmsCategory;
import com.tjnu.losca.service.IPmsCategoryService;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 分类表 服务实现类
 * </p>
 *
 * @author losca
 * @since 2022-02-10
 */
@Service
public class PmsCategoryServiceImpl extends ServiceImpl<PmsCategoryMapper, PmsCategory> implements IPmsCategoryService {

   public boolean check(Integer id, String value) {
      QueryWrapper<PmsCategory> wrapper = new QueryWrapper<PmsCategory>();
      wrapper.eq("name",value);
      if(id != null) {
         wrapper.ne("id", id);
      }
      return this.count(wrapper) == 0;
 }
    //key用单引号包裹
    @Cacheable(value = "product",key = "'categorys'")
    public List<PmsCategory> getAll() {
        return getByParentId(0L);
    }

    private List<PmsCategory> getByParentId(Long parentId){
        QueryWrapper<PmsCategory> wrapper = new QueryWrapper<PmsCategory>();
        wrapper.eq("parent_id", parentId);
        List<PmsCategory> list = this.list(wrapper);
        for (PmsCategory entry : list) {
            if (entry.getActive() == 1){
            entry.setChildren(getByParentId(entry.getId()));
            }
        }
        return list;
    }


}
