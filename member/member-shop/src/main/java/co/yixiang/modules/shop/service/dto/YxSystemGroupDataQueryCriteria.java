/**
 * Copyright (C) 2018-2022
 * All rights reserved, Designed By www.member
 * 注意：
 * 本软件为www.member开发研制
 */
package co.yixiang.modules.shop.service.dto;

import co.yixiang.annotation.Query;
import lombok.Data;

/**
 * @author member
 * @date 2022-10-16
 */
@Data
public class YxSystemGroupDataQueryCriteria {

    // 精确
    @Query
    private String groupName;
}
