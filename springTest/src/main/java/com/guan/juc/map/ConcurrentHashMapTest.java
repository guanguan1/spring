package com.guan.juc.map;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author : guantenghua
 * @create 2020/6/12 11:05
 */
public class ConcurrentHashMapTest {
    public static void main(String[] args) {
        ConcurrentHashMap concurrentHashMap = new ConcurrentHashMap();
        for (int i = 0; i < 10; i++) {
            concurrentHashMap.put("a" + i, "1");
        }
        concurrentHashMap.put("b", "1");
        concurrentHashMap.put("c", "1");
        concurrentHashMap.put("d", "1");
        concurrentHashMap.put("e", "1");
        System.out.println(concurrentHashMap);
    }
}
