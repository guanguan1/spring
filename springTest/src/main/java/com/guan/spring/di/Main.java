package com.guan.spring.di;

import com.guan.spring.conf.di.DiConfig;
import com.guan.spring.conf.di.Di_NOUseAnnotation;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @Author : guantenghua
 * @create 2020/7/5 11:05
 */
public class Main {
    public static void main(String[] args){
        // 使用 AnnotationConfigApplicationContext 作为Spring容器，接受输入一个配置类作为参数
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(DiConfig.class);
        UseFunctionService useFunctionService = context.getBean(UseFunctionService.class);
        System.out.println(useFunctionService.sayHello("world"));
        context.close();
        AnnotationConfigApplicationContext contextT = new AnnotationConfigApplicationContext(Di_NOUseAnnotation.class);
        UseFunctionService_NOUseAnnotation useFunctionService_noUseAnnotation = contextT.getBean(UseFunctionService_NOUseAnnotation.class);
        System.out.println(useFunctionService_noUseAnnotation.sayHello("word_noUseAnnotation"));
        contextT.close();
    }
}
