/**
 * Copyright (C) 2018-2022
 * All rights reserved, Designed By www.member
 * 注意：
 * 本软件为www.member开发研制
 */
package co.yixiang.modules.shop.service.dto;


import lombok.Data;

import java.util.List;

@Data
public class OrderCountDto {

    private List<String> column;

    private List<OrderCountData> orderCountDatas;

    @Data
    public static class OrderCountData {
        private String name;

        private Integer value;
    }
}
