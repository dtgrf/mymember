/**
 * Copyright (C) 2018-2022
 * All rights reserved, Designed By www.member
 * 注意：
 * 本软件为www.member开发研制
 */
package co.yixiang.modules.shop.service.dto;

import lombok.Data;

import java.io.Serializable;


/**
 * @author member
 * @date 2019-10-04
 */
@Data
public class YxStoreProductSmallDto implements Serializable {

    // 商品id
    private Integer id;

    // 商品图片
    private String image;


    // 商品名称
    private String storeName;


}
