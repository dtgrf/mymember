/**
 * Copyright (C) 2018-2022
 * All rights reserved, Designed By www.member
 * 注意：
 * 本软件为www.member开发研制
 */
package co.yixiang.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author member
 * 应用来源相关枚举
 */
@Getter
@AllArgsConstructor
public enum AppFromEnum {

    WEIXIN_H5("weixinh5", "weixinh5"),
    H5("h5", "H5"),
    WECHAT("wechat", "公众号"),
    APP("app", "APP"),
    ROUNTINE("routine", "小程序");


    private String value;
    private String desc;


}
