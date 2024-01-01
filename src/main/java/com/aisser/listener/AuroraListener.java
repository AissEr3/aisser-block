package com.aisser.listener;

import com.aisser.model.entity.ExceptionLog;
import com.aisser.model.entity.OperationLog;
import com.aisser.event.ExceptionLogEvent;
import com.aisser.event.OperationLogEvent;
import com.aisser.mapper.ExceptionLogMapper;
import com.aisser.mapper.OperationLogMapper;
import com.aisser.model.dto.EmailDTO;
import com.aisser.model.dto.UserInfoDTO;
import com.aisser.service.OperationLogService;
import com.aisser.service.RedisService;
import com.aisser.service.UserInfoService;
import com.aisser.util.EmailUtil;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

import static com.aisser.constant.CommonConstant.BLOGGER_ID;

@Component
@Log4j2
public class AuroraListener {

    @Autowired
    private OperationLogMapper operationLogMapper;

    @Autowired
    private ExceptionLogMapper exceptionLogMapper;

    @Autowired
    OperationLogService operationLogService;

    @Autowired
    RedisService redisService;

    @Resource
    EmailUtil emailUtil;

    @Resource
    UserInfoService userInfoService;

    @Async
    @EventListener(OperationLogEvent.class)
    public void saveOperationLog(OperationLogEvent operationLogEvent) {
        operationLogMapper.insert((OperationLog) operationLogEvent.getSource());
    }

    @Async
    @EventListener(ExceptionLogEvent.class)
    public void saveExceptionLog(ExceptionLogEvent exceptionLogEvent) {
        ExceptionLog source = (ExceptionLog) exceptionLogEvent.getSource();
        exceptionLogMapper.insert(source);
        if (source.getUnknownException()) {
            //未知异常 发送邮件 即使处理
            sendExceptionEmail(source);
        }
    }

    private void sendExceptionEmail(ExceptionLog source) {
        EmailDTO emailDTO = new EmailDTO();
        Map<String, Object> map = new HashMap<>();
        UserInfoDTO userInfo = userInfoService.getUserInfoById(BLOGGER_ID);
        emailDTO.setEmail(userInfo.getEmail());
        emailDTO.setSubject("未知异常提醒");
        emailDTO.setTemplate("common.html");
        String url = "https://www.blog.jonk.top/admin/#/login";
        map.put("content", "AissEr@博客【" + source.getOptDesc() + "】发生未知异常，"
                + "<a style=\"text-decoration:none;color:#12addb\" href=\"" + url + "  \">点击处理</a> <br/><br/>"
                + source.getExceptionInfo().substring(0, source.getExceptionInfo().indexOf("at"))
        );
        emailDTO.setCommentMap(map);
        emailUtil.sendHtmlMail(emailDTO);
    }

}
