package com.guan.spring.profile;

import com.guan.spring.conf.ProfileConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @Author : guantenghua
 * @create 2020/7/5 17:49
 */
public class Main {
    public static void main(String[] args){
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.getEnvironment().setActiveProfiles("prod");
        context.register(ProfileConfig.class);
        context.refresh();
        DemoBean demoBean = context.getBean(DemoBean.class);
        System.out.println(demoBean.getContent());
        context.close();
    }
}
