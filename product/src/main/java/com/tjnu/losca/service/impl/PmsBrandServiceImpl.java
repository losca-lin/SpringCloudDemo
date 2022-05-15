package com.tjnu.losca.service.impl;

import com.tjnu.losca.pojo.PmsBrand;
import com.tjnu.losca.mapper.PmsBrandMapper;
import com.tjnu.losca.service.IPmsBrandService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.apache.commons.lang3.StringUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
/**
 * <p>
 * 品牌表 服务实现类
 * </p>
 *
 * @author losca
 * @since 2022-01-28
 */
@Service
public class PmsBrandServiceImpl extends ServiceImpl<PmsBrandMapper, PmsBrand> implements IPmsBrandService {

   public boolean check(Integer id, String value) {
      QueryWrapper<PmsBrand> wrapper = new QueryWrapper<PmsBrand>();
      wrapper.eq("name",value);
      if(id != null) {
         wrapper.ne("id", id);
      }
      return this.count(wrapper) == 0;
 }


   public IPage<PmsBrand> list(int pageNo, int pageSize, String value) {
      QueryWrapper<PmsBrand> wrapper = new QueryWrapper<PmsBrand>();
      if(StringUtils.isNotBlank(value)) {
         wrapper.like("name",value);
      }
      return this.page(new Page<PmsBrand>(pageNo,pageSize),wrapper);
 }
}
