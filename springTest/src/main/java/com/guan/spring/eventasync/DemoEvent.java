package com.guan.spring.eventasync;

import org.springframework.context.ApplicationEvent;

/**
 * 定义事件
 * @Author : guantenghua
 * @create 2020/5/9 9:28
 */
public class DemoEvent extends ApplicationEvent {
    // 事件监听消息
    private String msg;

    public DemoEvent(Object source, String msg) {
        super(source);
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
