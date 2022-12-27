/**
 * Copyright (C) 2018-2022
 * All rights reserved, Designed By www.member
 * 注意：
 * 本软件为www.member开发研制
 */
package co.yixiang.modules.system.service.mapper;

import co.yixiang.common.mapper.CoreMapper;
import co.yixiang.modules.system.domain.Job;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author member
 * @date 2020-05-14
 */
@Repository
@Mapper
public interface JobMapper extends CoreMapper<Job> {

}
