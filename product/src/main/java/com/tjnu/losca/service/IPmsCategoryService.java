package com.tjnu.losca.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.tjnu.losca.pojo.PmsCategory;

import java.util.List;

/**
 * <p>
 * 分类表 服务类
 * </p>
 *
 * @author losca
 * @since 2022-02-10
 */
public interface IPmsCategoryService extends IService<PmsCategory> {
    boolean check(Integer id, String value);

    List<PmsCategory> getAll();
}
