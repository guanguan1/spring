package com.guan.spring.conf.di;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * di配置类
 * @Author : guantenghua
 * @create 2020/7/5 11:02
 */
@Configuration  // 声明当前是一个配置类,相当于一个spring配置类的xml文件
@ComponentScan("com.guan.spring.di") // 自动扫描包名下所有使用@Service、@Component、@Respository、@Controller的类，并注册Bean
public class DiConfig {
}
