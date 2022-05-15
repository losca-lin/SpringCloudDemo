package com.tjnu.losca.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.tjnu.losca.pojo.PmsSku;

import java.util.List;

/**
 * <p>
 * sku属性 服务类
 * </p>
 *
 * @author losca
 * @since 2022-02-10
 */
public interface IPmsSkuService extends IService<PmsSku> {
    boolean check(Integer id, String value);
    List<PmsSku> list(Long categoryId);
}
