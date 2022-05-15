package com.tjnu.losca.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * <p>
 * 分类表
 * </p>
 *
 * @author losca
 * @since 2022-02-10
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class PmsCategory extends BasePojo {

    private static final long serialVersionUID = 1L;

    /**
     * 分类名称
     */
    private String name;

    /**
     * 上级id
     */
    private Long parentId;

    /**
     * 分类状态
     */
    private Integer active;

    /**
     * 层级
     */
    private Integer level;

    @TableField(exist = false)
    private List<PmsCategory> children;


}
