/**
 * Copyright (C) 2018-2022
 * All rights reserved, Designed By www.member
 * 注意：
 * 本软件为www.member开发研制
 */
package co.yixiang.modules.activity.domain;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import co.yixiang.domain.BaseDomain;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * @author member
 * @date 2022-10-13
 */
@Data
@TableName("yx_store_coupon_issue_user")
public class YxStoreCouponIssueUser extends BaseDomain {

    @TableId
    private Long id;


    /** 领取优惠券用户ID */
    private Long uid;


    /** 优惠券前台领取ID */
    private Integer issueCouponId;



    public void copy(YxStoreCouponIssueUser source) {
        BeanUtil.copyProperties(source, this, CopyOptions.create().setIgnoreNullValue(true));
    }
}
