package com.tjnu.losca.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 权限列表
 * </p>
 *
 * @author losca
 * @since 2022-02-05
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class UmsResource extends BasePojo {

    private static final long serialVersionUID = 1L;

    /**
     * 资源名称
     */
    private String name;

    /**
     * 前端地址
     */
    private String frontUrl;

    /**
     * 后端地址
     */
    private String backUrl;

    /**
     * 类别 0为导航 1后台按钮
     */
    private Integer type;

    /**
     * 父子目录
     */
    private Long parentId;

    //表示当前属性不是数据库的字段，但在项目中必须使用，这样在新增等使用bean的时候，mybatis-plus就会忽略这个属性且报错
    @TableField(exist = false)
    private List<UmsResource> children = new ArrayList<UmsResource>();


}
