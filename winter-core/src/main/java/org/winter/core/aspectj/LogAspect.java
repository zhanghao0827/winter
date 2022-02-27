package org.winter.core.aspectj;

import cn.hutool.core.util.StrUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.winter.common.annotation.LogOperation;
import org.winter.common.domain.NetInfo;
import org.winter.common.util.NetUtils;
import org.winter.common.util.SecurityUtils;
import org.winter.common.util.ServletUtils;
import org.winter.monitor.domain.Log;
import org.winter.monitor.service.LogService;

import java.lang.reflect.Method;

@Aspect
@Component
public class LogAspect {

    private static final Logger log = LoggerFactory.getLogger(LogAspect.class);


    @Autowired
    private LogService logService;

    /**
     * 配置切入点
     */
    @Pointcut("@annotation(org.winter.common.annotation.LogOperation)")
    public void logPointcut() {
        // 该方法无方法体,主要为了让同类中其他方法使用此切入点
    }

    /**
     * 处理完请求后执行
     *
     * @param joinPoint 切点
     */
    @AfterReturning(pointcut = "logPointcut()", returning = "result")
    public void doAfterReturning(JoinPoint joinPoint, Object result) throws Exception {
        handleLog(joinPoint, result, null);
    }

    /**
     * 拦截异常操作
     *
     * @param joinPoint 切点
     * @param e         异常
     */
    @AfterThrowing(value = "logPointcut()", throwing = "e")
    public void doAfterThrowing(JoinPoint joinPoint, Exception e) throws Exception {
        handleLog(joinPoint, null, e);
    }

    /**
     * 是否存在注解，如果存在就获取
     */
    private LogOperation getAnnotationLog(JoinPoint joinPoint) {
        Signature signature = joinPoint.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        Method method = methodSignature.getMethod();
        if (method != null) {
            return method.getAnnotation(LogOperation.class);
        }
        return null;
    }

    protected void handleLog(final JoinPoint joinPoint, Object result, final Exception e) throws JsonProcessingException {
        LogOperation log = getAnnotationLog(joinPoint);
        if (log == null) {
            return;
        }
        Log monLog = new Log();
        monLog.setContent(log.value());
        // 设置方法名称
        String className = joinPoint.getTarget().getClass().getName();
        String methodName = joinPoint.getSignature().getName();
        monLog.setMethod(className + "." + methodName + "()");
        monLog.setRequestMethod(ServletUtils.getRequest().getMethod());
        monLog.setUrl(ServletUtils.getRequest().getRequestURI());
        monLog.setUsername(SecurityUtils.getLoginUsername());
        NetInfo netInfo = NetUtils.getNetInfo();
        monLog.setIp(netInfo.getIp());
        monLog.setAddress(netInfo.getPro() + netInfo.getCity());
        monLog.setResult(new ObjectMapper().writeValueAsString(result));
        monLog.setStatus(true);
        if (e != null) {
            monLog.setStatus(false);
            String exception = e.getClass().getName() + ":" + e.getMessage();
            if (exception.length() > 1000) {
                monLog.setException(StrUtil.sub(exception, 0, 1000) + "...");
            } else {
                monLog.setException(exception);
            }
        }
        // monLog存储到数据库
        logService.save(monLog);
    }


}
