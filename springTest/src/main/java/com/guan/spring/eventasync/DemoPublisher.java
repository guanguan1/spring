package com.guan.spring.eventasync;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

/**
 * 事件发布者
 * @Author : guantenghua
 * @create 2020/5/9 9:30
 */
@Component
public class DemoPublisher {

    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    // 事件发布方法
    public void pushListener(String msg){
        applicationEventPublisher.publishEvent(new DemoEvent(this, msg));
    }
}
