/**
 * Copyright (C) 2018-2022
 * All rights reserved, Designed By www.member
 * 注意：
 * 本软件为www.member开发研制
 */
package co.yixiang.modules.activity.service.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author member
 * @date 2022-10-13
 */
@Data
public class YxStoreCouponIssueUserDto implements Serializable {

    private Long id;

    /** 领取优惠券用户ID */
    private Long uid;

    /** 优惠券前台领取ID */
    private Integer issueCouponId;

    /** 领取时间 */
    private Date createTime;
}
