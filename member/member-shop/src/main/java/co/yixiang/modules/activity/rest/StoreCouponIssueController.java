/**
 * Copyright (C) 2018-2022
 * All rights reserved, Designed By www.member
 * 注意：
 * 本软件为www.member开发研制
 */
package co.yixiang.modules.activity.rest;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.lang.Dict;
import cn.hutool.core.util.IdUtil;
import cn.hutool.extra.template.Template;
import cn.hutool.extra.template.TemplateConfig;
import cn.hutool.extra.template.TemplateEngine;
import cn.hutool.extra.template.TemplateUtil;
import co.yixiang.modules.activity.domain.*;
import co.yixiang.modules.activity.service.YxStoreCouponIssueUserService;
import co.yixiang.modules.activity.service.YxStoreCouponService;
import co.yixiang.modules.activity.service.YxStoreCouponUserService;
import co.yixiang.modules.logging.aop.log.Log;
import co.yixiang.modules.activity.service.YxStoreCouponIssueService;
import co.yixiang.modules.activity.service.dto.YxStoreCouponIssueQueryCriteria;
import co.yixiang.modules.aop.NoRepeatSubmit;
import co.yixiang.modules.shop.domain.YxStoreCart;
import co.yixiang.modules.shop.domain.YxStoreOrder;
import co.yixiang.modules.shop.domain.YxStoreOrderCartInfo;
import co.yixiang.modules.shop.domain.YxStoreProduct;
import co.yixiang.modules.shop.service.dto.VisualOrder;
import co.yixiang.modules.tools.domain.vo.EmailVo;
import co.yixiang.modules.tools.service.EmailConfigService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;

/**
 * @author member
 * @date 2019-11-09
 */
@Api(tags = "商城:优惠券发布管理")
@RestController
@RequestMapping("api")
public class StoreCouponIssueController {

    private final YxStoreCouponIssueService yxStoreCouponIssueService;
    private final YxStoreCouponService yxStoreCouponService;
    private final YxStoreCouponUserService yxStoreCouponUserService;
    private final YxStoreCouponIssueUserService yxStoreCouponIssueUserService;
    private final EmailConfigService emailService;
    public StoreCouponIssueController(YxStoreCouponIssueService yxStoreCouponIssueService,
                                      YxStoreCouponUserService yxStoreCouponUserService,
                                      YxStoreCouponIssueUserService yxStoreCouponIssueUserService,
                                      EmailConfigService emailService,
                                      YxStoreCouponService yxStoreCouponService) {
        this.yxStoreCouponIssueService = yxStoreCouponIssueService;
        this.yxStoreCouponService=yxStoreCouponService;
        this.yxStoreCouponUserService=yxStoreCouponUserService;
        this.yxStoreCouponIssueUserService=yxStoreCouponIssueUserService;
        this.emailService=emailService;
    }

    @Log("查询已发布")
    @ApiOperation(value = "查询已发布")
    @GetMapping(value = "/yxStoreCouponIssue")
    @PreAuthorize("hasAnyRole('admin','YXSTORECOUPONISSUE_ALL','YXSTORECOUPONISSUE_SELECT')")
    public ResponseEntity getYxStoreCouponIssues(YxStoreCouponIssueQueryCriteria criteria, Pageable pageable) {
        criteria.setIsDel(0);
        return new ResponseEntity(yxStoreCouponIssueService.queryAll(criteria, pageable), HttpStatus.OK);
    }

    @Log("发布")
    @ApiOperation(value = "发布")
    @PostMapping(value = "/yxStoreCouponIssue")
    @PreAuthorize("hasAnyRole('admin','YXSTORECOUPONISSUE_ALL','YXSTORECOUPONISSUE_CREATE')")
    public ResponseEntity create(@Validated @RequestBody YxStoreCouponIssue resources) {
        if (resources.getTotalCount() > 0) {
            resources.setRemainCount(resources.getTotalCount());
        }
        return new ResponseEntity(yxStoreCouponIssueService.save(resources), HttpStatus.CREATED);
    }

    @Log("修改状态")
    @ApiOperation(value = "修改状态")
    @PutMapping(value = "/yxStoreCouponIssue")
    @PreAuthorize("hasAnyRole('admin','YXSTORECOUPONISSUE_ALL','YXSTORECOUPONISSUE_EDIT')")
    public ResponseEntity update(@Validated @RequestBody YxStoreCouponIssue resources) {
        yxStoreCouponIssueService.saveOrUpdate(resources);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @NoRepeatSubmit
    @Log("删除")
    @ApiOperation(value = "删除")
    @DeleteMapping(value = "/yxStoreCouponIssue/{id}")
    @PreAuthorize("hasAnyRole('admin','YXSTORECOUPONISSUE_ALL','YXSTORECOUPONISSUE_DELETE')")
    public ResponseEntity delete(@PathVariable Integer id) {
        yxStoreCouponIssueService.removeById(id);
        return new ResponseEntity(HttpStatus.OK);
    }


    @NoRepeatSubmit
    @Log("发送优惠券")
    @ApiOperation(value = "发送优惠券")
    @PostMapping(value = "/yxStoreCouponIssue/sendcoupon")
    @PreAuthorize("hasAnyRole('admin','YXSTOREORDER_ALL','YXSTOREORDER_EDIT')")
    public ResponseEntity createVisualOrder(@RequestBody VisualCoupon resources) {
        try {

            Integer id = resources.getId();
            YxStoreCouponIssue yxStoreCouponIssue = yxStoreCouponIssueService
                    .getOne(new LambdaQueryWrapper<YxStoreCouponIssue>().eq(YxStoreCouponIssue::getId, id));
            YxStoreCoupon yxStoreCoupon = yxStoreCouponService
                    .getOne(new LambdaQueryWrapper<YxStoreCoupon>().eq(YxStoreCoupon::getId, yxStoreCouponIssue.getCid()));
            resources.getUserlist().forEach(item -> {
                yxStoreCouponIssue.setRemainCount(yxStoreCouponIssue.getRemainCount() - 1);
                YxStoreCouponUser couponUser = new YxStoreCouponUser();
                couponUser.setCid(yxStoreCouponIssue.getCid());
                couponUser.setUid(item.getUid());
                couponUser.setCouponTitle(yxStoreCouponIssue.getCname());
                couponUser.setCouponPrice((yxStoreCoupon.getCouponPrice()));
                couponUser.setUseMinPrice(yxStoreCoupon.getUseMinPrice());
                couponUser.setCreateTime(DateTime.now());
                couponUser.setUseTime(DateTime.now());
                couponUser.setEndTime(yxStoreCouponIssue.getEndTime());
                couponUser.setType("send");
                couponUser.setStatus(0);
                couponUser.setIsFail(0);
                couponUser.setIsDel(0);
                yxStoreCouponUserService.save(couponUser);
                YxStoreCouponIssueUser yxStoreCouponIssueUser = new YxStoreCouponIssueUser();
                yxStoreCouponIssueUser.setUid(item.getUid());
                yxStoreCouponIssueUser.setCreateTime(DateTime.now());
                yxStoreCouponIssueUser.setIsDel(0);
                yxStoreCouponIssueUser.setIssueCouponId(yxStoreCouponIssue.getId());
                yxStoreCouponIssueUserService.save(yxStoreCouponIssueUser);


                String content;
                TemplateEngine engine = TemplateUtil.createEngine(new TemplateConfig("template", TemplateConfig.ResourceMode.CLASSPATH));
                Template template = engine.getTemplate("email/couponsend.ftl");
                Dict dict = Dict.create();
                dict.set("price", yxStoreCoupon.getCouponPrice());
                content = template.render(dict);
                EmailVo emailVo = new EmailVo(Collections.singletonList(item.getEmail()), "Member后台管理", content);
                try {
                    emailService.send(emailVo, emailService.find());
                } catch (Exception e) {
                    e.printStackTrace();
                }

            });
            yxStoreCouponIssueService.saveOrUpdate(yxStoreCouponIssue);
        }
        catch (Exception e){
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(HttpStatus.OK);
    }


}
