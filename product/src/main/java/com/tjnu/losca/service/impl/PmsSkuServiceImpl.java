package com.tjnu.losca.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tjnu.losca.mapper.PmsSkuMapper;
import com.tjnu.losca.pojo.PmsSku;
import com.tjnu.losca.service.IPmsSkuService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * sku属性 服务实现类
 * </p>
 *
 * @author losca
 * @since 2022-02-10
 */
@Service
public class PmsSkuServiceImpl extends ServiceImpl<PmsSkuMapper, PmsSku> implements IPmsSkuService {

   public boolean check(Integer id, String value) {
      QueryWrapper<PmsSku> wrapper = new QueryWrapper<PmsSku>();
      wrapper.eq("name",value);
      if(id != null) {
         wrapper.ne("id", id);
      }
      return this.count(wrapper) == 0;
 }


   public List<PmsSku> list(Long categoryId) {
       QueryWrapper<PmsSku> wrapper = new QueryWrapper<PmsSku>();
       wrapper.eq("category_id", categoryId);
       return this.list(wrapper);
   }
}
