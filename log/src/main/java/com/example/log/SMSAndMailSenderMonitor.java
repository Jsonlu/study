package com.example.log;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;

import java.lang.reflect.Method;

/**
 * Author:JsonLu
 * DateTime:2019/12/20 17:58
 * Email:jsonlu@qq.com
 * Desc:
 **/

@Aspect
public class SMSAndMailSenderMonitor {


    /**
     * 在所有标记了@SMSAndMailSender的方法中切入
     *
     * @param joinPoint
     * @param result
     */
    @AfterReturning(value = "@annotation(com.example.log.SMSAndMailSender)", returning = "result")
    //有注解标记的方法，执行该后置返回
    public void afterReturning(JoinPoint joinPoint, Object result) {
        MethodSignature ms = (MethodSignature) joinPoint.getSignature();
        Method method = ms.getMethod();
        boolean active = method.getAnnotation(SMSAndMailSender.class).isActive();
        if (!active) {
            return;
        }
        String smsContent = method.getAnnotation(SMSAndMailSender.class).smsContent();
        String mailContent = method.getAnnotation(SMSAndMailSender.class).mailContent();
        String subject = method.getAnnotation(SMSAndMailSender.class).subject();
    }


    /**
     * 在抛出异常时使用
     *
     * @param joinPoint
     * @param ex
     */
    @AfterThrowing(value = "@annotation(com.example.log.SMSAndMailSender)", throwing = "ex")
    public void afterThrowing(JoinPoint joinPoint, Throwable ex) {
        MethodSignature ms = (MethodSignature) joinPoint.getSignature();
        Method method = ms.getMethod();
        String subject = method.getAnnotation(SMSAndMailSender.class).subject();

    }
}