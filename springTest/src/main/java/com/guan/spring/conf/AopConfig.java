package com.guan.spring.conf;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * @Author : guantenghua
 * @create 2020/3/25 16:21
 */
@Configuration
@ComponentScan("com.guan.spring.aop")
@EnableAspectJAutoProxy // 开启Spring对AspectJ的支持
public class AopConfig {

}
