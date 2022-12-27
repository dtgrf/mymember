/**
* Copyright (C) 2018-2022
* All rights reserved, Designed By www.member
* 注意：
* 本软件为www.member开发研制
*/
package co.yixiang.modules.system.service.dto;

import lombok.Data;
    import java.util.List;
    import co.yixiang.annotation.Query;

/**
* @author jack
* @date 2022-10-18
*/
@Data
public class YxUserIntegralQueryCriteria{

            /** 精确 */
            @Query
            private Long id;

            /** 模糊 */
            @Query(type = Query.Type.INNER_LIKE)
            private String title;

            /** 精确 */
            @Query
            private Integer integralNum;

            /** 模糊 */
            @Query(type = Query.Type.INNER_LIKE)
            private String remark;

            /** 精确 */
            @Query
            private Long userid;
}
