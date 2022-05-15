package com.tjnu.losca.service.impl;

import com.tjnu.losca.pojo.PmsProduct;
import com.tjnu.losca.mapper.PmsProductMapper;
import com.tjnu.losca.service.IPmsProductService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.apache.commons.lang3.StringUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
/**
 * <p>
 * 商品表 服务实现类
 * </p>
 *
 * @author losca
 * @since 2022-02-10
 */
@Service
public class PmsProductServiceImpl extends ServiceImpl<PmsProductMapper, PmsProduct> implements IPmsProductService {

   public boolean check(Integer id, String value) {
      QueryWrapper<PmsProduct> wrapper = new QueryWrapper<PmsProduct>();
      wrapper.eq("name",value);
      if(id != null) {
         wrapper.ne("id", id);
      }
      return this.count(wrapper) == 0;
 }


   public IPage<PmsProduct> list(int pageNo, int pageSize, String value) {
      QueryWrapper<PmsProduct> wrapper = new QueryWrapper<PmsProduct>();
      if(StringUtils.isNotBlank(value)) {
         wrapper.like("name",value);
      }
      return this.page(new Page<PmsProduct>(pageNo,pageSize),wrapper);
 }
}
