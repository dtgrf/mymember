/**
 * Copyright (C) 2018-2022
 * All rights reserved, Designed By www.member
 * 注意：
 * 本软件为www.member开发研制
 */
package co.yixiang.modules.system.service.dto;

import co.yixiang.annotation.Query;
import lombok.Data;

import java.sql.Timestamp;
import java.util.List;
import java.util.Set;

/**
 * @author member
 * @date 2020-05-14
 */
@Data
public class UserQueryCriteria {

    @Query
    private Long id;

    @Query(propName = "deptId", type = Query.Type.IN)
    private Set<Long> deptIds;

    @Query(blurry = "email,username,nickName")
    private String blurry;

    @Query
    private Boolean enabled;

    private Long deptId;

    @Query(type = Query.Type.BETWEEN)
    private List<Timestamp> createTime;
}
