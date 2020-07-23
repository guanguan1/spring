package com.guan.spring.di;

import org.junit.Test;

/**
 * @Author : guantenghua
 * @create 2020/5/19 10:02
 */
public class RecorsionTest {

    @Test
    public void fib() {
        Long start = System.currentTimeMillis();
        fib(400);
        Long end = System.currentTimeMillis();
        System.out.println("耗时为：" + (end - start) + "ms");
    }

    public static long fib(int n) {
        if (n <= 1) {
            return 1;
        } else {
            return fib(n - 1) + fib(n - 2);
        }
    }
}
