/**
 * Copyright (C) 2018-2022
 * All rights reserved, Designed By www.member
 * 注意：
 * 本软件为www.member开发研制
 */
package co.yixiang.modules.shop.domain;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * @author member
 * @date 2022-10-16
 */

@Data
@TableName("yx_store_product_attr")
public class YxStoreProductAttr implements Serializable {

    @TableId
    private Long id;


    /** 商品ID */
    private Long productId;


    /** 属性名 */
    private String attrName;


    /** 属性值 */
    private String attrValues;


    public void copy(YxStoreProductAttr source) {
        BeanUtil.copyProperties(source, this, CopyOptions.create().setIgnoreNullValue(true));
    }
}
