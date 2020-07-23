package com.guan.spring.el;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.Environment;
import org.springframework.core.io.Resource;

import java.io.IOException;


/**
 * @Author : guantenghua
 * @create 2020/7/5 14:08
 */
@Configuration
@ComponentScan("com.guan.spring.el")
// 注入配置配件使用@PropertySource指定文件地址，若使用@Value注入，则要配置一个PropertySourcesPlaceholderConfigurer的Bean。
// 注意。@Value("${book.name}")使用的是“$”而不是“#”
// 注入properties还可以从Environment中获得
@PropertySource("classpath:el/test.properties")
public class ElConfig {
    @Value("I Love You") // 注入普通字符串
    private String normal;

    @Value("#{systemProperties['os.name']}") // 注入操作系统属性
    private String osName;

    @Value("#{ T(java.lang.Math).random() * 100.0}") // 注入表达是结果
    private double randomNumber;

    @Value("#{demoService.another}") // 注入其他bean属性
    private String fromAnother;

    @Value("http://www.baidu.com") // 注入网址资源
    private Resource testUrl;

    @Value("${book.name}") // 注入配置文件
    private String bookName;

    @Value("classpath:el/test") // 注入文件资源
    private Resource testFile;

    @Autowired
    private Environment environment;

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertyConfigure(){
        return new PropertySourcesPlaceholderConfigurer();
    }

    public void outputResource(){
        try {
            System.out.println(normal);
            System.out.println(osName);
            System.out.println(randomNumber);
            System.out.println(fromAnother);
            System.out.println(IOUtils.toString(testUrl.getInputStream()));
            System.out.println(bookName);
            System.out.println(environment.getProperty("book.author"));
            System.out.println(IOUtils.toString(testFile.getInputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
