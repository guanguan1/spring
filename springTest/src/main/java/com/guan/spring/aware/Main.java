package com.guan.spring.aware;

import com.guan.spring.conf.AwareConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @Author : guantenghua
 * @create 2020/7/7 10:19
 */
public class Main {
    public static void main(String[] args){
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AwareConfig.class);
        AwareService awareService = context.getBean(AwareService.class);
        awareService.outputResult();
        context.close();
    }
}
