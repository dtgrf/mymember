/**
 * Copyright (C) 2018-2022
 * All rights reserved, Designed By www.member
 * 注意：
 * 本软件为www.member开发研制
 */
package co.yixiang.modules.shop.service.dto;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author member
 * @date 2022-10-16
 */
@Data
public class YxUserRechargeDto implements Serializable {

    private Long id;

    /** 充值用户UID */
    private Long uid;

    /** 订单号 */
    private String orderId;

    /** 充值金额 */
    private BigDecimal price;

    /** 充值类型 */
    private String rechargeType;

    /** 是否充值 */
    private Integer paid;

    /** 充值支付时间 */
    private Date payTime;

    /** 充值时间 */
    private Date createTime;

    /** 退款金额 */
    private BigDecimal refundPrice;

    /** 昵称 */
    private String nickname;
}
