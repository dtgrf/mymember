/**
* Copyright (C) 2018-2022
* All rights reserved, Designed By www.member
* 注意：
* 本软件为www.member开发研制
*/
package co.yixiang.modules.system.service.mapper;

import co.yixiang.common.mapper.CoreMapper;
import co.yixiang.modules.shop.service.dto.ChartDataDto;
import co.yixiang.modules.system.domain.YxUserIntegral;
import co.yixiang.modules.system.domain.radioPie;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
* @author jack
* @date 2022-10-18
*/
@Repository
@Mapper
public interface YxUserIntegralMapper extends CoreMapper<YxUserIntegral> {
    @Select("SELECT DATE_FORMAT(create_time,'%Y-%m-%d') time , count(*) as num FROM yx_user GROUP BY  time order by time ASC")
    List<ChartDataDto> getChartList();
    @Select("SELECT CASE WHEN TIMESTAMPDIFF(YEAR,birthday,NOW())>18 THEN '成年人' ELSE '未成年人' END name,COUNT(*) value from yx_user GROUP BY name")
    List<radioPie.RadioData> getPieList();
}
