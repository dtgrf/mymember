/**
 * Copyright (C) 2018-2022
 * All rights reserved, Designed By www.member
 * 注意：
 * 本软件为www.member开发研制
 */
package co.yixiang.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.stream.Stream;

/**
 * @author member
 * 订单相关枚举
 */
@Getter
@AllArgsConstructor
public enum OrderStatusEnum {

    STATUS_0(0, "未支付"),
    STATUS_1(1, "待发货"),
    STATUS_2(2, "待收货"),
    STATUS_3(3, "待评价"),
    STATUS_4(4, "已完成"),
    STATUS_MINUS_1(-1, "退款中"),
    STATUS_MINUS_2(-2, "已退款"),
    STATUS_MINUS_3(-3, "退款"),
    STATUS_MINUS_4(-4, "已删除");


    private Integer value;
    private String desc;

    public static OrderStatusEnum toType(int value) {
        return Stream.of(OrderStatusEnum.values())
                .filter(p -> p.value == value)
                .findAny()
                .orElse(null);
    }


}
