/**
 * Copyright (C) 2018-2022
 * All rights reserved, Designed By www.member
 * 注意：
 * 本软件为www.member开发研制
 */
package co.yixiang.modules.activity.service;

import co.yixiang.common.service.BaseService;
import co.yixiang.modules.activity.domain.YxUserExtract;
import co.yixiang.modules.activity.service.dto.YxUserExtractDto;
import co.yixiang.modules.activity.service.dto.YxUserExtractQueryCriteria;
import org.springframework.data.domain.Pageable;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @author member
 * @date 2022-10-13
 */
public interface YxUserExtractService extends BaseService<YxUserExtract> {

    /**
     * 查询数据分页
     * @param criteria 条件
     * @param pageable 分页参数
     * @return Map<String, Object>
     */
    Map<String, Object> queryAll(YxUserExtractQueryCriteria criteria, Pageable pageable);

    /**
     * 查询所有数据不分页
     * @param criteria 条件参数
     * @return List<YxUserExtractDto>
     */
    List<YxUserExtract> queryAll(YxUserExtractQueryCriteria criteria);

    /**
     * 导出数据
     * @param all 待导出的数据
     * @param response /
     * @throws IOException /
     */
    void download(List<YxUserExtractDto> all, HttpServletResponse response) throws IOException;

    /**
     * 操作提现
     * @param resources YxUserExtract
     */
    void doExtract(YxUserExtract resources);
}
