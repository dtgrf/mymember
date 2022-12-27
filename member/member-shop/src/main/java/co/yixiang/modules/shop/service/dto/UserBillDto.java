/**
 * Copyright (C) 2018-2022
 * All rights reserved, Designed By www.member
 * 注意：
 * 本软件为www.member开发研制
 */
package co.yixiang.modules.shop.service.dto;

import lombok.Data;

/**
 * @ClassName UserBillDTO
 * @author member <610796224@qq.com>
 * @Date 2019/12/11
 **/
@Data
public class UserBillDto {
    private Integer pm;
    private String gtitle;
    private String category;
    private String type;
    private Double number;
    private String nickname;
}
