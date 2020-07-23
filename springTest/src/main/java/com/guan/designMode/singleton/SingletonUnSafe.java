package com.guan.designMode.singleton;

/**
 * 懒加载启动快，资源占用小，使用时才实例化，无锁。
 * @Author : guantenghua
 * @create 2020/4/2 15:51
 */
public class SingletonUnSafe {

    private static SingletonUnSafe instance;

    private SingletonUnSafe() {
    }
    // 此方法是获得本类实例的唯一全局访问点
    public static SingletonUnSafe getInstance() {
        if (instance == null) {
            instance = new SingletonUnSafe();
        }
        return instance;
    }
}
