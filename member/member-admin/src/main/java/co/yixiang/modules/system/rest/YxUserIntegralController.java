/**
* Copyright (C) 2018-2022
* All rights reserved, Designed By www.member.co
* 注意：
* 本软件为www.member.co开发研制
*/
package co.yixiang.modules.system.rest;
import java.util.Arrays;
import co.yixiang.dozer.service.IGenerator;
import co.yixiang.modules.shop.domain.YxSystemConfig;
import co.yixiang.modules.shop.domain.YxUser;
import co.yixiang.modules.shop.service.dto.ChartDataDto;
import co.yixiang.modules.system.domain.User;
import co.yixiang.modules.shop.service.impl.YxUserServiceImpl;
import co.yixiang.modules.system.domain.radioPie;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import lombok.AllArgsConstructor;
import co.yixiang.modules.logging.aop.log.Log;
import co.yixiang.modules.system.domain.YxUserIntegral;
import co.yixiang.modules.system.service.YxUserIntegralService;
import co.yixiang.modules.system.service.dto.YxUserIntegralQueryCriteria;
import co.yixiang.modules.system.service.dto.YxUserIntegralDto;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.*;
import java.io.IOException;
import java.util.List;
import javax.servlet.http.HttpServletResponse;

/**
* @author jack
* @date 2022-10-18
*/
@AllArgsConstructor
@Api(tags = "Integral管理")
@RestController
@RequestMapping("/api/yxUserIntegral")
public class YxUserIntegralController {

private final YxUserIntegralService yxUserIntegralService;
private final IGenerator generator;
private final YxUserServiceImpl userService;
@Log("导出数据")
@ApiOperation("导出数据")
@GetMapping(value = "/download")
@PreAuthorize("@el.check('admin','yxUserIntegral:list')")
public void download(HttpServletResponse response, YxUserIntegralQueryCriteria criteria) throws IOException {
yxUserIntegralService.download(generator.convert(yxUserIntegralService.queryAll(criteria), YxUserIntegralDto.class), response);
}

@GetMapping
@Log("查询Integral")
@ApiOperation("查询Integral")
@PreAuthorize("@el.check('admin','yxUserIntegral:list')")
public ResponseEntity
<Object> getYxUserIntegrals(YxUserIntegralQueryCriteria criteria, Pageable pageable){
    return new ResponseEntity<>(yxUserIntegralService.queryAll(criteria,pageable),HttpStatus.OK);
    }

    @PostMapping
    @Log("新增Integral")
    @ApiOperation("新增Integral")
    @PreAuthorize("@el.check('admin','yxUserIntegral:add')")
    public ResponseEntity
    <Object> create(@Validated @RequestBody YxUserIntegral resources){
        YxUser user = userService.getOne(new LambdaQueryWrapper<YxUser>()
                .eq(YxUser::getUid, resources.getUserid()));
        resources.setUsername(user.getUsername());
        LambdaUpdateWrapper<YxUser> lambdaUpdateWrapper = new LambdaUpdateWrapper<>();
        int n=user.getIntegral()+resources.getIntegralNum();
        lambdaUpdateWrapper.eq(YxUser::getUid, resources.getUserid())
                .set(YxUser::getIntegral,n);
        userService.update(null, lambdaUpdateWrapper);
        return new ResponseEntity<>(yxUserIntegralService.save(resources),HttpStatus.CREATED);
        }

        @PutMapping
        @Log("修改积分")
        @ApiOperation("修改积分")
        @PreAuthorize("@el.check('admin','yxUserIntegral:edit')")
        public ResponseEntity
        <Object> update(@Validated @RequestBody YxUserIntegral resources){
        yxUserIntegralService.updateById(resources);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        @Log("删除Integral")
        @ApiOperation("删除Integral")
        @PreAuthorize("@el.check('admin','yxUserIntegral:del')")
        @DeleteMapping
        public ResponseEntity
        <Object> deleteAll(@RequestBody Long[] ids) {
        Arrays.asList(ids).forEach(id->{
        yxUserIntegralService.removeById(id);
        });
        return new ResponseEntity<>(HttpStatus.OK);
        }

    @ApiOperation("获取chart")
    @GetMapping(value = "/getChartList")
    @PreAuthorize("@el.check('roles:list')")
    public List<ChartDataDto> getChartList() {

        return  yxUserIntegralService.getChartList();
    }
    @ApiOperation("获取pie")
    @GetMapping(value = "/getPietList")
    @PreAuthorize("@el.check('roles:list')")
    public radioPie getPieList() {
        return  yxUserIntegralService.getPieList();
    }

}
