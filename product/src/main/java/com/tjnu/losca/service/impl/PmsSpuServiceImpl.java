package com.tjnu.losca.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tjnu.losca.mapper.PmsSpuMapper;
import com.tjnu.losca.pojo.PmsSpu;
import com.tjnu.losca.service.IPmsSpuService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * spu属性 服务实现类
 * </p>
 *
 * @author losca
 * @since 2022-02-10
 */
@Service
public class PmsSpuServiceImpl extends ServiceImpl<PmsSpuMapper, PmsSpu> implements IPmsSpuService {

   public boolean check(Integer id, String value) {
      QueryWrapper<PmsSpu> wrapper = new QueryWrapper<PmsSpu>();
      wrapper.eq("name",value);
      if(id != null) {
         wrapper.ne("id", id);
      }
      return this.count(wrapper) == 0;
 }


   public List<PmsSpu> list(Long categoryId) {
      QueryWrapper<PmsSpu> wrapper = new QueryWrapper<PmsSpu>();
       wrapper.eq("category_id", categoryId);
      return this.list(wrapper);
 }
}
