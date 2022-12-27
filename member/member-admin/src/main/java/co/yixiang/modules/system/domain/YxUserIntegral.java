/**
* Copyright (C) 2018-2022
* All rights reserved, Designed By www.member
* 注意：
* 本软件为www.member开发研制
*/
package co.yixiang.modules.system.domain;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import com.baomidou.mybatisplus.annotation.TableName;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;

import java.sql.Timestamp;
import java.io.Serializable;

/**
* @author jack
* @date 2022-10-18
*/
@Data
@TableName("yx_user_integral")
public class YxUserIntegral implements Serializable {

            /** 记录表id */
            @TableId
            private Long id;


            /** 积分标题 */
            private String title;


            /** 积分数 */
            private Integer integralNum;


            /** 备注 */
            private String remark;


            /** 创建日期 */
            //@CreationTimestamp
            @TableField(fill= FieldFill.INSERT)
            private Timestamp createDate;


            /** 赠与用户的id */
            private Long userid;


            /** 赠与用户的名称 */
            private String username;


public void copy(YxUserIntegral source){
BeanUtil.copyProperties(source,this, CopyOptions.create().setIgnoreNullValue(true));
}
}
