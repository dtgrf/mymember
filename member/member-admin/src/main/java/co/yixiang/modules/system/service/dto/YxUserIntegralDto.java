/**
* Copyright (C) 2018-2022
* All rights reserved, Designed By www.member
* 注意：
* 本软件为www.member开发研制
*/
package co.yixiang.modules.system.service.dto;

import lombok.Data;
    import java.sql.Timestamp;
import java.io.Serializable;
    import com.fasterxml.jackson.databind.annotation.JsonSerialize;
    import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

/**
* @author jack
* @date 2022-10-18
*/
@Data
public class YxUserIntegralDto implements Serializable {

            /** 记录表id */
                /** 防止精度丢失 */
                @JsonSerialize(using= ToStringSerializer.class)
            private Long id;

            /** 积分标题 */
            private String title;

            /** 积分数 */
            private Integer integralNum;

            /** 备注 */
            private String remark;

            /** 创建日期 */
            private Timestamp createDate;

            /** 赠与用户的id */
            private Long userid;

            /** 赠与用户的名称 */
            private String username;
}
