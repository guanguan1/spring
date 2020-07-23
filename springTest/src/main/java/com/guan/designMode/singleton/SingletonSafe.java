package com.guan.designMode.singleton;


/**
 * @Author : guantenghua
 * @create 2020/4/2 16:14
 */
public class SingletonSafe {

    /**
     * 定义一个变量来存储创建好的类实例
     */
    private static SingletonSafe uniqueInstance = null;

    /**
     * 私有化构造方法，好在内部控制创建实例的数目
     */
    private SingletonSafe() {
    }

    /**
     * 定义一个方法来为客户端提供类实例
     *
     * @return 一个singleton的实例
     */
    public static synchronized SingletonSafe getInstance() {
        // 判断存储实例的变量是否有值
        if (uniqueInstance == null) {
            uniqueInstance = new SingletonSafe();
        }
        return uniqueInstance;
    }

}
