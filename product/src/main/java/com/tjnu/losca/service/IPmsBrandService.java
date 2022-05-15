package com.tjnu.losca.service;

import com.tjnu.losca.pojo.PmsBrand;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.core.metadata.IPage;
/**
 * <p>
 * 品牌表 服务类
 * </p>
 *
 * @author losca
 * @since 2022-01-28
 */
public interface IPmsBrandService extends IService<PmsBrand> {
    boolean check(Integer id, String value);
    IPage<PmsBrand> list(int pageNo, int pageSize, String value);
}
