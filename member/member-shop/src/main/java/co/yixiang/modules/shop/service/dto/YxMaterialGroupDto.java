/**
 * Copyright (C) 2018-2022
 * All rights reserved, Designed By www.member
 * 注意：
 * 本软件为www.member开发研制
 */
package co.yixiang.modules.shop.service.dto;

import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @author member
 * @date 2022-10-16
 */
@Data
public class YxMaterialGroupDto implements Serializable {
    /** PK */
    private String id;


    /** 创建者ID */
    private String createId;

    /** 分组名 */
    private String name;
}
