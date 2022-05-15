package com.tjnu.losca.service;

import com.tjnu.losca.pojo.PmsProduct;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.core.metadata.IPage;
/**
 * <p>
 * 商品表 服务类
 * </p>
 *
 * @author losca
 * @since 2022-02-10
 */
public interface IPmsProductService extends IService<PmsProduct> {
    boolean check(Integer id, String value);
    IPage<PmsProduct> list(int pageNo, int pageSize, String value);
}
