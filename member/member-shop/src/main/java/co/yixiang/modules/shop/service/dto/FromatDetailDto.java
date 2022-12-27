/**
 * Copyright (C) 2018-2022
 * All rights reserved, Designed By www.member
 * 注意：
 * 本软件为www.member开发研制
 */
package co.yixiang.modules.shop.service.dto;

import lombok.Data;

import java.util.List;

/**
 * @ClassName FromatDetailDTO
 * @author member <610796224@qq.com>
 * @Date 2019/10/12
 **/

@Data
public class FromatDetailDto {
    private boolean attrHidden;

    private String detailValue;

    private List<String> detail;

    private String value;

}
