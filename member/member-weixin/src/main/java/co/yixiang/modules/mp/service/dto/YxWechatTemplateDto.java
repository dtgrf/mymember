/**
 * Copyright (C) 2018-2022
 * All rights reserved, Designed By www.member
 * 注意：
 * 本软件为www.member开发研制
 */
package co.yixiang.modules.mp.service.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @author member
 * @date 2022-10-16
 */
@Data
public class YxWechatTemplateDto implements Serializable {

    /** 模板id */
    private Integer id;

    /** 模板编号 */
    private String tempkey;

    /** 模板名 */
    private String name;

    /** 回复内容 */
    private String content;

    /** 模板ID */
    private String tempid;

    /** 添加时间 */
    private String addTime;

    /** 状态 */
    private Integer status;
}
