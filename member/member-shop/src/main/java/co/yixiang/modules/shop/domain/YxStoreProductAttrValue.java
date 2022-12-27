/**
 * Copyright (C) 2018-2022
 * All rights reserved, Designed By www.member
 * 注意：
 * 本软件为www.member开发研制
 */
package co.yixiang.modules.shop.domain;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author member
 * @date 2022-10-16
 */

@Data
@TableName("yx_store_product_attr_value")
public class YxStoreProductAttrValue implements Serializable {

    @TableId
    private Long id;


    /** 商品ID */
    private Long productId;


    /** 商品属性索引值 (attr_value|attr_value[|....]) */
    private String sku;


    /** 属性对应的库存 */
    private Integer stock;


    /** 销量 */
    private Integer sales;


    /** 属性金额 */
    private BigDecimal price;


    /** 图片 */
    private String image;


    /** 唯一值 */
    @TableField(value = "`unique`")
    private String unique;


    /** 成本价 */
    private BigDecimal cost;


    public void copy(YxStoreProductAttrValue source) {
        BeanUtil.copyProperties(source, this, CopyOptions.create().setIgnoreNullValue(true));
    }
}
