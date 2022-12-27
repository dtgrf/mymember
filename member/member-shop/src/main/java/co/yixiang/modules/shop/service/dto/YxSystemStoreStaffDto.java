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
public class YxSystemStoreStaffDto implements Serializable {

    private Integer id;

    /** 微信用户id */
    private Long uid;

    /** 店员头像 */
    private String avatar;

    /** 门店id */
    private Integer storeId;

    /** 店员名称 */
    private String staffName;

    /** 手机号码 */
    private String phone;

    /** 核销开关 */
    private Integer verifyStatus;

    /** 状态 */
    private Integer status;

    /** 添加时间 */
    private Date createTime;

    /** 微信昵称 */
    private String nickname;

    /** 所属门店 */
    private String storeName;
}
