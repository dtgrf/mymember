/**
 * Copyright (C) 2018-2022
 * All rights reserved, Designed By www.member
 * 注意：
 * 本软件为www.member开发研制
 */
package co.yixiang.modules.tools.service;

import co.yixiang.common.service.BaseService;
import co.yixiang.modules.tools.domain.QiniuContent;
import co.yixiang.modules.tools.service.dto.QiniuContentDto;
import co.yixiang.modules.tools.service.dto.QiniuQueryCriteria;
import org.springframework.data.domain.Pageable;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @author member
 * @date 2022-10-13
 */
public interface QiniuContentService extends BaseService<QiniuContent> {

    /**
     * 查询数据分页
     * @param criteria 条件
     * @param pageable 分页参数
     * @return Map<String, Object>
     */
    Map<String, Object> queryAll(QiniuQueryCriteria criteria, Pageable pageable);

    /**
     * 查询所有数据不分页
     * @param criteria 条件参数
     * @return List<QiniuContentDto>
     */
    List<QiniuContent> queryAll(QiniuQueryCriteria criteria);

    /**
     * 导出数据
     * @param all 待导出的数据
     * @param response /
     * @throws IOException /
     */
    void download(List<QiniuContentDto> all, HttpServletResponse response) throws IOException;
}
