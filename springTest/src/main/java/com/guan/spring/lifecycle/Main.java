package com.guan.spring.lifecycle;

import com.guan.spring.conf.LifeCycleConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @Author : guantenghua
 * @create 2020/7/5 16:50
 */
public class Main {
    public static void main(String[] args){
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(LifeCycleConfig.class);
        BeanWayService beanWayService = context.getBean(BeanWayService.class);
        JSR250WayService jsr250WayService = context.getBean(JSR250WayService.class);
        context.close();
    }
}
