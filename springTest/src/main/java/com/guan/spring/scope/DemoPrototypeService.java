package com.guan.spring.scope;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

/**
 * 每次调用新建一个Bean的实例
 * @Author : guantenghua
 * @create 2020/7/5 13:34
 */
@Service
@Scope("prototype")
public class DemoPrototypeService {
}
