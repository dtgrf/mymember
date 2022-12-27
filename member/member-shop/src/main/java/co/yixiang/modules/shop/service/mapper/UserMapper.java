/**
 * Copyright (C) 2018-2022
 * All rights reserved, Designed By www.member
 * 注意：
 * 本软件为www.member开发研制
 */
package co.yixiang.modules.shop.service.mapper;

import co.yixiang.common.mapper.CoreMapper;
import co.yixiang.modules.shop.domain.YxUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author member
 * @date 2022-10-16
 */
@Repository
@Mapper
public interface UserMapper extends CoreMapper<YxUser> {

    @Update("update yx_user set status = #{status} where uid = #{id}")
    void updateOnstatus(@Param("status") int status, @Param("id") int id);

    @Update("update yx_user set now_money = now_money + ${money} where uid = #{id}")
    void updateMoney(@Param("money") double money, @Param("id") Long id);

    @Update("update yx_user set brokerage_price = brokerage_price+ ${price} where uid = #{id}")
    void incBrokeragePrice(@Param("price") double price, @Param("id") Long id);
    @Select("SELECT *\n" +
            "        from yx_user\n" +
            "        WHERE\n" +
            "        birthday is not null and\n" +
            "        MONTH(birthday) = MONTH(NOW()) and DAY(birthday) = DAY(NOW())")
    List<YxUser> queryBirthAll();
}
