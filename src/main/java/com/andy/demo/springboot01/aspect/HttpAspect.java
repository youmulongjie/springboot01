/**
 * Copyright (C), 2015-2018
 * FileName: HttpAspect
 * Author:   59458
 * Date:     2018/5/25 21:38
 * Description: Http 切面
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.andy.demo.springboot01.aspect;

import com.andy.demo.springboot01.bean.ResultBean;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * 〈申明HTTP切面，用于记录 HttpRequest、HttpResponse日志信息〉<br>
 * 〈Http 切面〉
 *
 * @author 59458
 * @create 2018/5/25
 * @since 1.0.0
 */
@Aspect
@Component // 申明为组件，被扫描到
@Slf4j     // 依赖lombok插件，的Log注解
public class HttpAspect {
    /**
     * 定义切入点（controller 包下所有类 所有方法）
     */
    @Pointcut("execution(public * com.andy.demo.springboot01.controller..*.*(..))")
    public void print() {
    }

    /**
     * 前置方法,在目标方法执行前执行
     */
    @Before("print()")
    public void beforeMethod() {
        log.info("**********开始**********");
        // 从 持有上下文的Request容器中，获取 HttpServletRequest 对象
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();

        // 打印 HttpServletRequest 信息
        printRequest(request);
    }

    /**
     * 将在目标方法正常完成后被织入
     *
     * @param joinPoint
     * @param returning
     */
    @AfterReturning(pointcut = "print()", returning = "returning")
    public void AfterReturning(JoinPoint joinPoint, Object returning) {
        // 打印 访问的类方法信息
        printJoinPoint(joinPoint);

        // 打印 返回的信息
        String res = null;
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            res = objectMapper.writeValueAsString(returning);
            log.info("Response ：{}", res);
        } catch (JsonProcessingException e) {
            log.error("出错了", e);
        }

        log.info("***********结束*********");
    }

    /**
     * 打印 访问的类方法信息
     *
     * @param joinPoint
     */
    private void printJoinPoint(JoinPoint joinPoint) {
        Signature signature = joinPoint.getSignature();
        log.info("Class.Method : {}.{}()", signature.getDeclaringTypeName(), signature.getName());
        log.info("args : {}", joinPoint.getArgs());
    }

    /**
     * 打印 HttpServletRequest 信息
     *
     * @param request
     */
    private void printRequest(HttpServletRequest request) {
        // ip
        log.info("Request IP:{}", request.getRemoteAddr());
        // url
        log.info("Request URL:{}", request.getRequestURL());
        // method
        log.info("Request Method:{}", request.getMethod());
    }

}