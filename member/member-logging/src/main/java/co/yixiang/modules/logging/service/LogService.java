/**
 * Copyright (C) 2018-2022
 * All rights reserved, Designed By www.member
 * 注意：
 * 本软件为www.member开发研制
 */
package co.yixiang.modules.logging.service;

import co.yixiang.common.service.BaseService;
import co.yixiang.modules.logging.domain.Log;
import co.yixiang.modules.logging.service.dto.LogQueryCriteria;
import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Async;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author member
 * @date 2018-11-24
 */
public interface LogService extends BaseService<Log> {


    /**
     * 分页查询日志
     *
     * @param criteria 标准
     * @param pageable 可分页
     * @return {@link Object}
     */
    Object findAllByPageable(LogQueryCriteria criteria, Pageable pageable);

    /**
     * 分页查询
     * @param criteria 查询条件
     * @param pageable 分页参数
     * @return /
     */
    Object queryAll(LogQueryCriteria criteria, Pageable pageable);

    /**
     * 查询全部数据
     * @param criteria 查询条件
     * @return /
     */
    List<Log> queryAll(LogQueryCriteria criteria);

    /**
     * 查询用户日志
     * @param criteria 查询条件
     * @param pageable 分页参数
     * @return -
     */
    Object queryAllByUser(LogQueryCriteria criteria, Pageable pageable);

    /**
     * 保存日志数据
     *
     * @param username  用户
     * @param ip        请求IP
     * @param joinPoint /
     * @param log       日志实体
     * @param uid       uid
     */
    @Async
    void save(String username, String ip, ProceedingJoinPoint joinPoint, Log log, Long uid);

    /**
     * 查询异常详情
     * @param id 日志ID
     * @return Object
     */
    Object findByErrDetail(Long id);

    /**
     * 导出日志
     * @param logs 待导出的数据
     * @param response /
     * @throws IOException /
     */
    void download(List<Log> logs, HttpServletResponse response) throws IOException;

    /**
     * 删除所有错误日志
     */
    void delAllByError();

    /**
     * 删除所有INFO日志
     */
    void delAllByInfo();
}
