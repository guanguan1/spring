package com.guan.designMode.singleton;

/**
 * @Author : guantenghua
 * @create 2020/4/2 15:54
 */
public class MainUnSafe {
    public static void main(String[] args){
        SingletonUnSafe s1 = SingletonUnSafe.getInstance();
        SingletonUnSafe s2 = SingletonUnSafe.getInstance();
        if(s1 == s2){
            System.out.println("两个对象是相同的实例");
        }
    }
}
