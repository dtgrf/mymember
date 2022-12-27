/**
 * Copyright (C) 2018-2022
 * All rights reserved, Designed By www.member
 * 注意：
 * 本软件为www.member开发研制
 */
package co.yixiang.modules.shop.domain;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * @author member
 * @date 2022-10-16
 */

@Data
@TableName("yx_system_config")
public class YxSystemConfig implements Serializable {

    /** 配置id */
    @TableId
    private Integer id;


    /** 字段名称 */
    private String menuName;


    /** 默认值 */
    private String value;


    /** 排序 */
    private Integer sort;


    /** 是否隐藏 */
    private Integer status;


    public void copy(YxSystemConfig source) {
        BeanUtil.copyProperties(source, this, CopyOptions.create().setIgnoreNullValue(true));
    }
}
