/**
 * Copyright (C) 2018-2022
 * All rights reserved, Designed By www.member
 * 注意：
 * 本软件为www.member开发研制
 */
package co.yixiang.modules.system.domain;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * @author member
 * @date 2020-05-16
 */
@Data
@TableName("users_roles")
public class UsersRoles implements Serializable {

    /** 用户ID */
    private Long userId;


    /** 角色ID */
    private Long roleId;

    public void copy(UsersRoles source) {
        BeanUtil.copyProperties(source, this, CopyOptions.create().setIgnoreNullValue(true));
    }
}
