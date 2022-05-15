package com.tjnu.losca.service;

import com.tjnu.losca.pojo.UmsResource;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.core.metadata.IPage;

import java.util.List;

/**
 * <p>
 * 权限列表 服务类
 * </p>
 *
 * @author losca
 * @since 2022-02-05
 */
public interface IUmsResourceService extends IService<UmsResource> {
    boolean check(Integer id, String value);
    IPage<UmsResource> list(int pageNo, int pageSize, String value);
    List<UmsResource> getByParentId(Long parentId);

    List<UmsResource> getByUserId(Long userId);

    List<UmsResource> getMenu(List<UmsResource> resources);
}
