package com.guan.spring.schedul;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @Author : guantenghua
 * @create 2020/6/3 11:26
 */
public class Main {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(DefaultSchedulingConfigurer.class);
        DefaultSchedulingConfigurer configurer = context.getBean(DefaultSchedulingConfigurer.class);
        TestJob job = new TestJob();
        job.getDefaultSchedulingConfigurer(configurer);
        job.afterPropertiesSet();
    }
}
