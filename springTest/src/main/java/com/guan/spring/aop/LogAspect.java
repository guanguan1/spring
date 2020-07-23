package com.guan.spring.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

/**
 * 切面
 * @Author : guantenghua
 * @create 2020/3/25 16:00
 */
@Aspect  // 声明是一个切面
@Component // 声明一个bean
@Order(0) // 设置优先级，值越低优先级越高
public class LogAspect {
    @Pointcut("@annotation(com.guan.spring.aop.Action) @args()")  // 声明切点
    public void annotationPointCut(){
    }

    @AfterReturning(value = "annotationPointCut()", returning = "result")
    public void  after(JoinPoint joinPoint, int result){
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        // 获取连接点参数-客户端传入参数
        Object[] object = joinPoint.getArgs();
        Action action = method.getDeclaredAnnotation(Action.class);
        System.out.println("注解式拦截" + action.type());
    }

    @Before("execution(* com.guan.spring.aop.DemoAnnotationService.*(..))")
    public void befor(JoinPoint joinPoint){
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        System.out.println("方法规则式拦截，" + method.getName());
    }

    @Around(value = "annotationPointCut()")
    public Object around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        System.out.println("ANNOTATION welcome");
        System.out.println("ANNOTATION 调用类：" + proceedingJoinPoint.getSignature().getDeclaringTypeName());
        System.out.println("ANNOTATION 调用类名" + proceedingJoinPoint.getSignature().getDeclaringType().getSimpleName());
        Object result = proceedingJoinPoint.proceed(); //调用目标方法
        System.out.println("ANNOTATION login success");
        return result;
    }
}
