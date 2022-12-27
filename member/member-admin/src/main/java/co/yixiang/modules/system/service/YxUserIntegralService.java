/**
* Copyright (C) 2018-2022
* All rights reserved, Designed By www.member
* 注意：
* 本软件为www.member开发研制
*/
package co.yixiang.modules.system.service;
import co.yixiang.common.service.BaseService;
import co.yixiang.modules.shop.service.dto.ChartDataDto;
import co.yixiang.modules.system.domain.YxUserIntegral;
import co.yixiang.modules.system.domain.radioPie;
import co.yixiang.modules.system.service.dto.YxUserIntegralDto;
import co.yixiang.modules.system.service.dto.YxUserIntegralQueryCriteria;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;
import java.util.Map;
import java.util.List;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;

/**
* @author jack
* @date 2022-10-18
*/
public interface YxUserIntegralService  extends BaseService<YxUserIntegral>{
    List<ChartDataDto> getChartList();
    radioPie getPieList();
/**
* 查询数据分页
* @param criteria 条件
* @param pageable 分页参数
* @return Map
<String,Object>
*/
Map
<String,Object> queryAll(YxUserIntegralQueryCriteria criteria, Pageable pageable);

/**
* 查询所有数据不分页
* @param criteria 条件参数
* @return List
<YxUserIntegralDto>
    */
    List<YxUserIntegral> queryAll(YxUserIntegralQueryCriteria criteria);

    /**
    * 导出数据
    * @param all 待导出的数据
    * @param response /
    * @throws IOException /
    */
    void download(List
    <YxUserIntegralDto> all, HttpServletResponse response) throws IOException;




        }
