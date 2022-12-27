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
public class YxSystemConfigDto implements Serializable {

    /** 配置id */
    private Integer id;

    /** 字段名称 */
    private String menuName;

    /** 默认值 */
    private String value;

    /** 排序 */
    private Integer sort;

    /** 是否隐藏 */
    private Integer status;
}
