package com.guan.spring.eventasync;

import com.guan.spring.conf.EventConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @Author : guantenghua
 * @create 2020/5/9 9:38
 */
public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(EventConfig.class);
        DemoPublisher publisher = context.getBean(DemoPublisher.class);
        publisher.pushListener("测试消息监听");
        publisher.pushListener("测试消息监听1");

        context.close();
    }
}
