package com.guan.spring.conf;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * spring事件配置
 *
 * @Author : guantenghua
 * @create 2020/5/9 9:36
 */
@Configuration
@ComponentScan("com.guan.spring.eventasync")
@EnableAsync // 开启异步处理
public class EventConfig {
}
