package com.guan.spring.aop;

import java.lang.annotation.*;

/**
 * 方法注解
 * @Author : guantenghua
 * @create 2020/3/25 15:55
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Action {
    int type();
}
