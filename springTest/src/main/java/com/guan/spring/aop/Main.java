package com.guan.spring.aop;

import com.guan.spring.conf.AopConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.HashMap;
import java.util.Map;

/**
 * 测试类
 * @Author : guantenghua
 * @create 2020/3/25 16:22
 */
public class Main {
    public static void main(String[] args){
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AopConfig.class);
        DemoAnnotationService demoAnnotationService = context.getBean(DemoAnnotationService.class);
        // 基于注解的拦截
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("companyId", 1234);
        map.put("authorId", 2313);
        demoAnnotationService.add(map, "关关");
        // 给予方法规则得拦截
        demoAnnotationService.addMethod();
        context.close();
    }
}
