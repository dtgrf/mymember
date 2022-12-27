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
 * @date 2022-10-16
 */
@Data
public class YxStoreOrderCartInfoDto implements Serializable {

    private Long id;

    /** 订单id */
    private Long oid;

    /** 购物车id */
    private Long cartId;

    /** 商品ID */
    private Long productId;

    /** 购买东西的详细信息 */
    private String cartInfo;

    /** 唯一id */
    private String unique;
}
