package com.tjnu.losca.pojo;

import com.tjnu.losca.pojo.BasePojo;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * spu属性
 * </p>
 *
 * @author losca
 * @since 2022-02-10
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class PmsSpu extends BasePojo {

    private static final long serialVersionUID = 1L;

    /**
     * 属性名称
     */
    private String name;

    /**
     * 关联类别id
     */
    private Long categoryId;


}
