package com.guan.spring.aop;

import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 注解的被拦截类
 * @Author : guantenghua
 * @create 2020/3/25 15:57
 */
@Service
public class DemoAnnotationService {
    @Action(type = 1)
    public int add(Map<String, Object> params, String name){
        System.out.println("基于注解的add方法");
        return 100;
    }

    public void addMethod(){
        System.out.println("基于方法规则的add方法");
    }
}
