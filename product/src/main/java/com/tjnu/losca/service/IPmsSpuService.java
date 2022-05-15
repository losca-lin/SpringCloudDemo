package com.tjnu.losca.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.tjnu.losca.pojo.PmsSpu;

import java.util.List;

/**
 * <p>
 * spu属性 服务类
 * </p>
 *
 * @author losca
 * @since 2022-02-10
 */
public interface IPmsSpuService extends IService<PmsSpu> {
    boolean check(Integer id, String value);
    List<PmsSpu> list(Long categoryId);
}
