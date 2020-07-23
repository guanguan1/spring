package com.guan.spring.di;

import org.springframework.stereotype.Service;

/**
 * 使用@Service注解声明当前FunctionService类是Spring管理的一个Bean。
 * 使用@Component(组件，没有明确的角色)、
 * @Service(在业务逻辑层使用)、
 * @Repository(在数据访问层使用)、
 * @Controller(在展现层使用)是等效的。
 * @Author : guantenghua
 * @create 2020/7/5 10:52
 */
@Service
public class FunctionService {
    public String sayHello(String word){
        return "Hello " + word + "!";
    }
}
