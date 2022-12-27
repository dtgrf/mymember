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
import java.util.Date;

/**
 * @author member
 * @date 2022-10-16
 */

@Data
@TableName("yx_store_order_status")
public class YxStoreOrderStatus implements Serializable {

    @TableId
    private Long id;


    /** 订单id */
    private Long oid;


    /** 操作类型 */
    private String changeType;


    /** 操作备注 */
    private String changeMessage;


    /** 操作时间 */
    private Date changeTime;


    public void copy(YxStoreOrderStatus source) {
        BeanUtil.copyProperties(source, this, CopyOptions.create().setIgnoreNullValue(true));
    }
}
