package co.yixiang.modules.quartz.task;

import cn.hutool.core.lang.Dict;
import cn.hutool.extra.template.Template;
import cn.hutool.extra.template.TemplateConfig;
import cn.hutool.extra.template.TemplateEngine;
import cn.hutool.extra.template.TemplateUtil;
import co.yixiang.modules.shop.domain.YxUser;
import co.yixiang.modules.shop.service.YxUserService;
import co.yixiang.modules.shop.service.dto.YxUserQueryCriteria;
import co.yixiang.modules.system.domain.YxUserIntegral;
import co.yixiang.modules.system.service.YxUserIntegralService;
import co.yixiang.modules.tools.domain.vo.EmailVo;
import co.yixiang.modules.tools.service.EmailConfigService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;

@Slf4j
@Component
public class SendTask {


    public static YxUserService yxUserService;

    @Autowired
    public void setYxUserService(YxUserService yxUserService) {
        SendTask.yxUserService = yxUserService;
    }
    //
    public static YxUserIntegralService yxUserIntegralService;
    @Autowired
    public void setYxUserService(YxUserIntegralService yxUserIntegralService) {
        SendTask.yxUserIntegralService = yxUserIntegralService;
    }

    public static EmailConfigService emailService;
    @Autowired
    public void setYxUserService(EmailConfigService emailService) {
        SendTask.emailService = emailService;
    }

    @Transactional(rollbackFor = Exception.class,propagation = Propagation.REQUIRED)
    public void run() {
        List<YxUser> birthlist=yxUserService.queryAll(new YxUserQueryCriteria());
        if(birthlist!=null){
            birthlist.forEach(item->
            {
                int intel=item.getIntegral()+20;
                item.setIntegral(intel);
                YxUserIntegral integral=new YxUserIntegral();
                integral.setIntegralNum(20);
                integral.setUsername(item.getUsername());
                integral.setRemark("test");
                integral.setTitle("每月赠送一次：20积分");
                integral.setUserid(item.getUid());
                yxUserIntegralService.save(integral);
                yxUserService.saveOrUpdate(item);
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
        Template template = engine.getTemplate("email/receive.ftl");
        Dict dict=Dict.create();
        dict.set("username", user.getUsername());
        content = template.render(dict);
        emailVo = new EmailVo(Collections.singletonList(user.getEmail()), "Member后台管理", content);
        return emailVo;
    }

}

