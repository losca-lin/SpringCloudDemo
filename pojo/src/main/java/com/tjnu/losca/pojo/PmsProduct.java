package com.tjnu.losca.pojo;

import java.math.BigDecimal;
import com.tjnu.losca.pojo.BasePojo;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 商品表
 * </p>
 *
 * @author losca
 * @since 2022-02-10
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class PmsProduct extends BasePojo {

    private static final long serialVersionUID = 1L;

    /**
     * 商品名称
     */
    private String name;

    /**
     * 标准价格
     */
    private BigDecimal price;

    /**
     * 品牌id
     */
    private Long brandId;

    /**
     * 品牌名称
     */
    private String brandName;

    /**
     * 关键词
     */
    private String keywords;

    /**
     * 商品图片
     */
    private String img;

    /**
     * 商品相册
     */
    private String pics;

    /**
     * 商品spu
     */
    private String spu;

    /**
     * 商品sku
     */
    private String sku;

    /**
     * 商品详情
     */
    private String details;

    /**
     * 是否热卖品
     */
    private Integer isHot;

    /**
     * 是否上架
     */
    private Integer isPublish;

    /**
     * 是否新品
     */
    private Integer isNew;

    /**
     * 状态
     */
    private Integer active;


}
