package com.guan.spring.conf.di;

import com.guan.spring.di.FunctionService;
import com.guan.spring.di.UseFunctionService;
import com.guan.spring.di.UseFunctionService_NOUseAnnotation;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author : guantenghua
 * @create 2020/7/5 11:14
 */
@Configuration
public class Di_NOUseAnnotation {
    @Bean // 使用@Bean注解声明当前方法FunctionService的返回值是一个Bean，Bean的名称是方法名
    public FunctionService functionService(){
        return new FunctionService();
    }
    @Bean
    public UseFunctionService_NOUseAnnotation useFunctionService(){
        UseFunctionService_NOUseAnnotation useFunctionService = new UseFunctionService_NOUseAnnotation();
        useFunctionService.setFunctionService(functionService());
        return useFunctionService;
    }
}
