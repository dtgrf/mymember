package co.yixiang.modules.quartz.task;

import cn.hutool.core.lang.Dict;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.extra.template.Template;
import cn.hutool.extra.template.TemplateConfig;
import cn.hutool.extra.template.TemplateEngine;
import cn.hutool.extra.template.TemplateUtil;
import co.yixiang.dozer.service.IGenerator;
import co.yixiang.modules.shop.domain.YxUser;
import co.yixiang.modules.shop.service.YxUserBillService;
import co.yixiang.modules.shop.service.YxUserService;
import co.yixiang.modules.shop.service.dto.UserMoneyDto;
import co.yixiang.modules.shop.service.dto.YxUserDto;
import co.yixiang.modules.shop.service.dto.YxUserQueryCriteria;
import co.yixiang.modules.shop.service.impl.YxUserServiceImpl;
import co.yixiang.modules.shop.service.mapper.UserMapper;
import co.yixiang.modules.system.service.YxUserIntegralService;
import co.yixiang.modules.tools.domain.VerificationCode;
import co.yixiang.modules.tools.domain.vo.EmailVo;
import co.yixiang.modules.tools.service.EmailConfigService;
import co.yixiang.modules.tools.service.impl.EmailConfigServiceImpl;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

@Slf4j
@Component
public class GiftTask {


    public static YxUserService yxUserService;

    @Autowired
    public void setYxUserService(YxUserService yxUserService) {
        GiftTask.yxUserService = yxUserService;
    }
    //

    public static EmailConfigService emailService;
    @Autowired
    public void setYxUserService(EmailConfigService emailService) {
        GiftTask.emailService = emailService;
    }

    @Transactional(rollbackFor = Exception.class,propagation = Propagation.REQUIRED)
    public void run() {
        List<YxUser> birthlist=yxUserService.queryBirthAll();
        if(birthlist!=null){
            birthlist.forEach(item->
            {
                EmailVo emailVo= sethtml(item);
                try {
                    emailService.send(emailVo, emailService.find());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        }

    }
    public EmailVo sethtml(YxUser user){
        EmailVo emailVo;
        String content;
        TemplateEngine engine = TemplateUtil.createEngine(new TemplateConfig("template", TemplateConfig.ResourceMode.CLASSPATH));
        Template template = engine.getTemplate("email/birthtemplate.ftl");
        content = template.render(Dict.create().set("username", user.getUsername()));
        emailVo = new EmailVo(Collections.singletonList(user.getEmail()), "member后台管理系统", content);
        return emailVo;
    }

}
