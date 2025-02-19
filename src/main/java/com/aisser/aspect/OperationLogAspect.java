package com.aisser.aspect;

import com.alibaba.fastjson.JSON;
import com.aisser.annotation.OptLog;
import com.aisser.model.entity.OperationLog;
import com.aisser.event.OperationLogEvent;
import com.aisser.model.dto.UserDetailsDTO;
import com.aisser.util.IpUtil;
import com.aisser.util.UserUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Objects;

@Aspect
@Component
public class OperationLogAspect {

    @Autowired
    private ApplicationContext applicationContext;

    @Pointcut("@annotation(com.aisser.annotation.OptLog)")
    public void operationLogPointCut() {
    }

    /**
     * 添加在Controller请求上，对于一些操作进行记录。
     * 通过反射等机制，获取这个请求的信息，将这心信息放在OperationLog中
     * 通过SpringBoot的事务处理方式，异步处理事务
     * 监听者将日志信息写入数据库中
     */
    @AfterReturning(value = "operationLogPointCut()", returning = "keys")
    @SuppressWarnings("unchecked")
    public void saveOperationLog(JoinPoint joinPoint, Object keys) {
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = (HttpServletRequest) Objects.requireNonNull(requestAttributes).resolveReference(RequestAttributes.REFERENCE_REQUEST);
        OperationLog operationLog = new OperationLog();
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        Api api = (Api) signature.getDeclaringType().getAnnotation(Api.class);
        ApiOperation apiOperation = method.getAnnotation(ApiOperation.class);
        OptLog optLog = method.getAnnotation(OptLog.class);
        operationLog.setOptModule(api.tags()[0]);
        operationLog.setOptType(optLog.optType());
        operationLog.setOptDesc(apiOperation.value());
        String className = joinPoint.getTarget().getClass().getName();
        String methodName = method.getName();
        methodName = className + "." + methodName;
        operationLog.setRequestMethod(Objects.requireNonNull(request).getMethod());
        operationLog.setOptMethod(methodName);
        if (joinPoint.getArgs().length > 0) {
            if (joinPoint.getArgs()[0] instanceof MultipartFile) {
                operationLog.setRequestParam("file");
            } else {
                operationLog.setRequestParam(JSON.toJSONString(joinPoint.getArgs()));
            }
        }
        operationLog.setResponseData(JSON.toJSONString(keys));
        UserDetailsDTO userDetailsDTO = UserUtil.getUserDetailsDTO();
        if(userDetailsDTO != null){
            operationLog.setUserId(userDetailsDTO.getId());
            operationLog.setNickname(userDetailsDTO.getNickname());
        }
        String ipAddress = IpUtil.getIpAddress(request);
        operationLog.setIpAddress(ipAddress);
        operationLog.setIpSource(IpUtil.getIpSource(ipAddress));
        operationLog.setOptUri(request.getRequestURI());
        applicationContext.publishEvent(new OperationLogEvent(operationLog));
    }

}
