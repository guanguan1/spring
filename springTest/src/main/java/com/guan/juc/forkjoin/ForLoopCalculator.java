package com.guan.juc.forkjoin;

import java.time.Duration;
import java.time.Instant;
import java.util.stream.LongStream;

/**
 * @Author : guantenghua
 * @create 2020/6/15 15:55
 */
public class ForLoopCalculator implements Calculator {
    /**
     * 求和方法
     *
     * @param numbers 数据
     * @return 总和
     */
    @Override
    public long sumUp(long[] numbers) {
        long total = 0;
        for (long i : numbers) {
            total += i;
        }
        return total;
    }

    public static void main(String[] args) {
        long[] numbers = LongStream.rangeClosed(1, 10000000).toArray();
        Instant start = Instant.now();
        Calculator calculator = new ForLoopCalculator();
        long result = calculator.sumUp(numbers);
        Instant end = Instant.now();
        System.out.println("耗时：" + Duration.between(start, end).toMillis() + "ms");

        System.out.println("结果为：" + result);

        // 耗时：12ms
        // 结果为：50000005000000
    }
}
