/**
 * Copyright (C) 2018-2022
 * All rights reserved, Designed By www.member
 * 注意：
 * 本软件为www.member开发研制
 */
package co.yixiang.modules.shop.domain;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import co.yixiang.domain.BaseDomain;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author member
 * @date 2022-10-16
 */

@Data
@TableName("yx_user_bill")
public class YxUserBill extends BaseDomain {

    /** 用户账单id */
    @TableId
    private Long id;


    /** 用户uid */
    private Long uid;


    /** 关联id */
    private String linkId;


    /** 0 = 支出 1 = 获得 */
    private Integer pm;


    /** 账单标题 */
    private String title;


    /** 明细种类 */
    private String category;


    /** 明细类型 */
    private String type;


    /** 明细数字 */
    private BigDecimal number;


    /** 剩余 */
    private BigDecimal balance;


    /** 备注 */
    private String mark;


    /** 0 = 带确定 1 = 有效 -1 = 无效 */
    private Integer status;


    public void copy(YxUserBill source) {
        BeanUtil.copyProperties(source, this, CopyOptions.create().setIgnoreNullValue(true));
    }
}
