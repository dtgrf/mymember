/**
 * Copyright (C) 2018-2022
 * All rights reserved, Designed By www.member
 * 注意：
 * 本软件为www.member开发研制
 */
package co.yixiang.modules.shop.service.dto;

import lombok.Data;

/**
 * @ClassName ChartDataDTO
 * @author member <610796224@qq.com>
 * @Date 2019/11/25
 **/
@Data
public class ChartDataDto {

    // @Value("#{target.adminCount}")
    private Double num;
    private String time;
}
