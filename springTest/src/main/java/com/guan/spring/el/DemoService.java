package com.guan.spring.el;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * @Author : guantenghua
 * @create 2020/7/5 14:06
 */
@Service
public class DemoService {
    @Value("其他类的属性")
    private String another;

    public String getAnother(){
        return another;
    }

    public void setAnother(String another) {
        this.another = another;
    }
}
