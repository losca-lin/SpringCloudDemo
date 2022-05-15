package com.tjnu.losca.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * <p>
 * 品牌表
 * </p>
 *
 * @author losca
 * @since 2022-01-28
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class PmsBrand extends BasePojo {

    private static final long serialVersionUID = 1L;

    /**
     * 品牌名
     */
    private String name;

    /**
     * 品牌说明
     */
    private String description;

    /**
     * 状态
     */
    private Integer active;


}
