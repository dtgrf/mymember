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
 * 账单相关枚举
 */
@Getter
@AllArgsConstructor
public enum BillEnum {

    PM_0(0, "支出"),
    PM_1(1, "获得"),

    STATUS_0(0, "默认"),
    STATUS_1(1, "有效"),
    STATUS_2(2, "无效");


    private Integer value;
    private String desc;

    public static BillEnum toType(int value) {
        return Stream.of(BillEnum.values())
                .filter(p -> p.value == value)
                .findAny()
                .orElse(null);
    }


}
