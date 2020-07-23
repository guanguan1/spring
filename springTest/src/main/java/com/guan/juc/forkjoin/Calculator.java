package com.guan.juc.forkjoin;

/**
 * @Author : guantenghua
 * @create 2020/6/15 15:54
 */
public interface Calculator {

    /**
     * 求和方法
     * @param numbers 数据
     * @return 总和
     */
    long sumUp(long[] numbers);
}
