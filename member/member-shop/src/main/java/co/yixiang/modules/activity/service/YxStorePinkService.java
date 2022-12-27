/**
 * Copyright (C) 2018-2022
 * All rights reserved, Designed By www.member
 * 注意：
 * 本软件为www.member开发研制
 */
package co.yixiang.modules.activity.service;

import co.yixiang.common.service.BaseService;
import co.yixiang.modules.activity.domain.YxStorePink;
import co.yixiang.modules.activity.service.dto.YxStorePinkDto;
import co.yixiang.modules.activity.service.dto.YxStorePinkQueryCriteria;
import org.springframework.data.domain.Pageable;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @author member
 * @date 2022-10-16
 */
public interface YxStorePinkService extends BaseService<YxStorePink> {

    /**
     * 查询数据分页
     * @param criteria 条件
     * @param pageable 分页参数
     * @return Map<String, Object>
     */
    Map<String, Object> queryAll(YxStorePinkQueryCriteria criteria, Pageable pageable);

    /**
     * 查询所有数据不分页
     * @param criteria 条件参数
     * @return List<YxStorePinkDto>
     */
    List<YxStorePink> queryAll(YxStorePinkQueryCriteria criteria);

    /**
     * 导出数据
     * @param all 待导出的数据
     * @param response /
     * @throws IOException /
     */
    void download(List<YxStorePinkDto> all, HttpServletResponse response) throws IOException;
}
