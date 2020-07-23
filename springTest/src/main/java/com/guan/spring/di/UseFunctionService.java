package com.guan.spring.di;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @Author : guantenghua
 * @create 2020/7/5 10:56
 */
@Service
public class UseFunctionService {

    @Autowired // 注入bean的注解 @Autowired(Spring提供注解)、@Inject(JSR-330提供注解)、@Resource(JSR-250提供注解)
    private FunctionService functionService;

    public String sayHello(String word){
        return functionService.sayHello(word);
    }
}
