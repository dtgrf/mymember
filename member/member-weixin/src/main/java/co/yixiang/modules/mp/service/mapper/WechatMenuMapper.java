/**
 * Copyright (C) 2018-2022
 * All rights reserved, Designed By www.member
 * 注意：
 * 本软件为www.member开发研制
 */
package co.yixiang.modules.mp.service.mapper;

import co.yixiang.common.mapper.CoreMapper;
import co.yixiang.modules.mp.domain.YxWechatMenu;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author member
 * @date 2022-10-16
 */
@Repository
@Mapper
public interface WechatMenuMapper extends CoreMapper<YxWechatMenu> {

}
