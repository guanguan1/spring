package com.guan.spring.di;

/**
 * @Author : guantenghua
 * @create 2020/7/5 10:56
 */
public class UseFunctionService_NOUseAnnotation {

    private FunctionService functionService;

    public void setFunctionService(FunctionService functionService){
        this.functionService = functionService;
    }

    public String sayHello(String word) {
        return functionService.sayHello(word);
    }
}
