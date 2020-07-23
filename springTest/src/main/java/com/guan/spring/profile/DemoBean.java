package com.guan.spring.profile;

/**
 * 演示bean
 * @Author : guantenghua
 * @create 2020/7/5 17:42
 */
public class DemoBean {
    private String content;

    public DemoBean(String content) {
        super();
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
