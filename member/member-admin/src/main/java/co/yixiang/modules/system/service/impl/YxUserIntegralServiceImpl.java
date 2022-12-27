/**
* Copyright (C) 2018-2022
* All rights reserved, Designed By www.member
* 注意：
* 本软件为www.member开发研制
*/
package co.yixiang.modules.system.service.impl;

import co.yixiang.modules.shop.service.dto.ChartDataDto;
import co.yixiang.modules.system.domain.YxUserIntegral;
import co.yixiang.common.service.impl.BaseServiceImpl;
import co.yixiang.modules.system.domain.radioPie;
import lombok.AllArgsConstructor;
import co.yixiang.dozer.service.IGenerator;
import com.github.pagehelper.PageInfo;
import co.yixiang.common.utils.QueryHelpPlus;
import co.yixiang.utils.FileUtil;
import co.yixiang.modules.system.service.YxUserIntegralService;
import co.yixiang.modules.system.service.dto.YxUserIntegralDto;
import co.yixiang.modules.system.service.dto.YxUserIntegralQueryCriteria;
import co.yixiang.modules.system.service.mapper.YxUserIntegralMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
// 默认不使用缓存
//import org.springframework.cache.annotation.CacheConfig;
//import org.springframework.cache.annotation.CacheEvict;
//import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Pageable;
import java.util.List;
import java.util.Map;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.LinkedHashMap;

/**
* @author jack
* @date 2022-10-18
*/
@Service
@AllArgsConstructor
//@CacheConfig(cacheNames = "yxUserIntegral")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class YxUserIntegralServiceImpl extends BaseServiceImpl
<YxUserIntegralMapper, YxUserIntegral> implements YxUserIntegralService {
private final YxUserIntegralMapper yxUserIntegralMapper;
private final IGenerator generator;

@Override
//@Cacheable
public Map
<String, Object> queryAll(YxUserIntegralQueryCriteria criteria, Pageable pageable) {
getPage(pageable);
PageInfo<YxUserIntegral> page = new PageInfo<>(queryAll(criteria));
Map
<String, Object> map = new LinkedHashMap<>(2);
map.put("content", generator.convert(page.getList(), YxUserIntegralDto.class));
map.put("totalElements", page.getTotal());
return map;
}


@Override
//@Cacheable
public List<YxUserIntegral> queryAll(YxUserIntegralQueryCriteria criteria){
return baseMapper.selectList(QueryHelpPlus.getPredicate(YxUserIntegral.class, criteria));
}


@Override
public void download(List
<YxUserIntegralDto> all, HttpServletResponse response) throws IOException {
    List
    <Map
    <String
    , Object>> list = new ArrayList<>();
    for (YxUserIntegralDto yxUserIntegral : all) {
    Map
    <String
    ,Object> map = new LinkedHashMap<>();
                map.put("积分标题", yxUserIntegral.getTitle());
                map.put("积分数", yxUserIntegral.getIntegralNum());
                map.put("备注", yxUserIntegral.getRemark());
                map.put("创建日期", yxUserIntegral.getCreateDate());
                map.put("赠与用户的id", yxUserIntegral.getUserid());
                map.put("赠与用户的名称", yxUserIntegral.getUsername());
    list.add(map);
    }
    FileUtil.downloadExcel(list, response);
    }

    @Override
    public List<ChartDataDto> getChartList() {
        return yxUserIntegralMapper.getChartList();
    }
    @Override
    public radioPie getPieList() {
        radioPie radioPie=new radioPie();
        List<radioPie.RadioData> list= yxUserIntegralMapper.getPieList();
        radioPie.setRadioList(list);
        List<String> strs=new ArrayList<>();
        for (int i=0;i<list.size();i++){
            strs.add(list.get(i).getName());
        }
        radioPie.setColumn(strs);
        list.forEach(item->item.getName());
        return radioPie;
    }
}
