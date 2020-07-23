package com.guan.spring.conf;

import com.guan.spring.lifecycle.BeanWayService;
import com.guan.spring.lifecycle.JSR250WayService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * 生命周期配置类
 * @Author : guantenghua
 * @create 2020/7/5 16:46
 */
@Configuration
@ComponentScan("com.guan.spring.lifecycle")
public class LifeCycleConfig {
    // initMethod 和 destroyMethod 指定BeanWayService类的init和destory方法在构造之后、bean销毁之前执行
    @Bean(initMethod = "init", destroyMethod = "destory")
    BeanWayService beanWayService(){
        return new BeanWayService();
    }

    @Bean
    JSR250WayService jsr250WayService(){
        return new JSR250WayService();
    }
}
