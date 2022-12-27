/**
 * Copyright (C) 2018-2022
 * All rights reserved, Designed By www.member
 * 注意：
 * 本软件为www.member开发研制
 */
package co.yixiang.modules.shop.service;

import co.yixiang.common.service.BaseService;
import co.yixiang.modules.shop.domain.YxStoreOrderStatus;
import co.yixiang.modules.shop.service.dto.YxStoreOrderStatusDto;
import co.yixiang.modules.shop.service.dto.YxStoreOrderStatusQueryCriteria;
import org.springframework.data.domain.Pageable;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @author member
 * @date 2022-10-16
 */
public interface YxStoreOrderStatusService extends BaseService<YxStoreOrderStatus> {

    /**
     * 查询数据分页
     * @param criteria 条件
     * @param pageable 分页参数
     * @return Map<String, Object>
     */
    Map<String, Object> queryAll(YxStoreOrderStatusQueryCriteria criteria, Pageable pageable);

    /**
     * 查询所有数据不分页
     * @param criteria 条件参数
     * @return List<YxStoreOrderStatusDto>
     */
    List<YxStoreOrderStatus> queryAll(YxStoreOrderStatusQueryCriteria criteria);

    /**
     * 导出数据
     * @param all 待导出的数据
     * @param response /
     * @throws IOException /
     */
    void download(List<YxStoreOrderStatusDto> all, HttpServletResponse response) throws IOException;
}
