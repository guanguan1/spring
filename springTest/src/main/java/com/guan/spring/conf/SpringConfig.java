package com.guan.spring.conf;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * @Author : guantenghua
 * @create 2020/2/23 9:12
 */
@Configuration  // 声明配置类
@ComponentScan(value = "*") // 扫描范围
@EnableAsync // 开启异步支持
public class SpringConfig {

}