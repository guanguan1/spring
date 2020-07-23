package com.guan.juc.forkjoin;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.LongStream;

/**
 * @Author : guantenghua
 * @create 2020/6/15 16:07
 */
public class ExecutorServiceCalculator implements Calculator {

    private int parallism;
    private ExecutorService pool;

    public ExecutorServiceCalculator() {
        // CPU的核心数
        this.parallism = Runtime.getRuntime().availableProcessors();
        pool = Executors.newFixedThreadPool(parallism);
    }

    /**
     * 求和方法
     *
     * @param numbers 数据
     * @return 总和
     */
    @Override
    public long sumUp(long[] numbers) {
        List<Future<Long>> results = new ArrayList<>();
        // 把任务分解为n份，交给n个线程处理
        // 然后把每一份都人一个SumTask线程 进行处理
        int part = numbers.length / parallism;
        for (int i = 0; i < parallism; i++) {
            int from = i * part; // 开始位置
            int to = (i == parallism - 1) ? numbers.length - 1 : (i + 1) * part - 1;// 结束位置
            // 扔给线程池计算
            results.add(pool.submit(new SumTask(numbers, from, to)));
        }
        // 把每个线程的结果相加，得到最终结果get()方法 是阻塞的
        // 优化方案，可以采用CompleteFuture来优化
        long total = 0L;
        for (Future<Long> f : results) {
            try {
                total += f.get();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return total;
    }

    private class SumTask implements Callable {
        private long[] numbers;
        private int from;
        private int to;

        public SumTask(long[] numbers, int from, int to) {
            this.numbers = numbers;
            this.from = from;
            this.to = to;
        }

        @Override
        public Object call() throws Exception {
            long tatal = 0;
            for (int i = from; i <= to; i++) {
                tatal += numbers[i];
            }
            return tatal;
        }
    }

    public static void main(String[] args) {
        long[] numbers = LongStream.rangeClosed(1, 10000000).toArray();
        Instant start = Instant.now();
        Calculator calculator = new ExecutorServiceCalculator();
        long result = calculator.sumUp(numbers);
        Instant end = Instant.now();
        System.out.println("耗时:" + Duration.between(start, end).toMillis() + "ms");
        System.out.println("结果为：" + result);
        // 耗时:24ms
        // 结果为：50000005000000
    }
}
