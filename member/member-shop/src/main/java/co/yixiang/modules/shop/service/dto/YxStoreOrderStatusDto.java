/**
 * Copyright (C) 2018-2022
 * All rights reserved, Designed By www.member
 * 注意：
 * 本软件为www.member开发研制
 */
package co.yixiang.modules.shop.service.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author member
 * @date 2022-10-16
 */
@Data
public class YxStoreOrderStatusDto implements Serializable {

    private Long id;

    /** 订单id */
    private Long oid;

    /** 操作类型 */
    private String changeType;

    /** 操作备注 */
    private String changeMessage;

    /** 操作时间 */
    private Date changeTime;
}
