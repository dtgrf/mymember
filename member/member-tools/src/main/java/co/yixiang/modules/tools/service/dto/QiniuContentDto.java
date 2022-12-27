/**
 * Copyright (C) 2018-2022
 * All rights reserved, Designed By www.member
 * 注意：
 * 本软件为www.member开发研制
 */
package co.yixiang.modules.tools.service.dto;

import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @author member
 * @date 2022-10-13
 */
@Data
public class QiniuContentDto implements Serializable {

    /** ID */
    private Long id;

    /** Bucket 识别符 */
    private String bucket;

    /** 文件名称 */
    private String name;

    /** 文件大小 */
    private String size;

    /** 文件类型：私有或公开 */
    private String type;

    /** 上传或同步的时间 */
    private Timestamp updateTime;

    /** 文件url */
    private String url;

    private String suffix;

    public String getKey() {
        return this.name;
    }
}
