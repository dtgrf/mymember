/**
 * Copyright (C) 2018-2022
 * All rights reserved, Designed By www.member
 * 注意：
 * 本软件为www.member开发研制
 */
package co.yixiang.modules.quartz.service.dto;

import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @author member
 * @date 2022-10-13
 */
@Data
public class QuartzJobDto implements Serializable {

    /** 定时任务ID */
    private Long id;

    /** Bean名称 */
    private String beanName;

    /** cron 表达式 */
    private String cronExpression;

    /** 状态：1暂停、0启用 */
    private Boolean isPause;

    /** 任务名称 */
    private String jobName;

    /** 方法名称 */
    private String methodName;

    /** 参数 */
    private String params;

    /** 备注 */
    private String remark;

    /** 创建日期 */
    private Timestamp createTime;

}
