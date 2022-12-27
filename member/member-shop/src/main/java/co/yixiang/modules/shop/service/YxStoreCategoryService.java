/**
 * Copyright (C) 2018-2022
 * All rights reserved, Designed By www.member
 * 注意：
 * 本软件为www.member开发研制
 */
package co.yixiang.modules.shop.service;

import co.yixiang.common.service.BaseService;
import co.yixiang.modules.shop.domain.YxStoreCategory;
import co.yixiang.modules.shop.service.dto.YxStoreCategoryDto;
import co.yixiang.modules.shop.service.dto.YxStoreCategoryQueryCriteria;
import org.springframework.data.domain.Pageable;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @author member
 * @date 2022-10-16
 */
public interface YxStoreCategoryService extends BaseService<YxStoreCategory> {

    /**
     * 查询数据分页
     * @param criteria 条件
     * @param pageable 分页参数
     * @return Map<String, Object>
     */
    Map<String, Object> queryAll(YxStoreCategoryQueryCriteria criteria, Pageable pageable);

    /**
     * 查询所有数据不分页
     * @param criteria 条件参数
     * @return List<YxStoreCategoryDto>
     */
    List<YxStoreCategoryDto> queryAll(YxStoreCategoryQueryCriteria criteria);

    /**
     * 导出数据
     * @param all 待导出的数据
     * @param response /
     * @throws IOException /
     */
    void download(List<YxStoreCategoryDto> all, HttpServletResponse response) throws IOException;

    Object buildTree(List<YxStoreCategoryDto> categoryDTOList);


    boolean checkCategory(int pid);

    boolean checkProductCategory(int id);
}
