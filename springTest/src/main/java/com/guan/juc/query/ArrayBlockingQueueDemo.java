package com.guan.juc.query;

import java.util.concurrent.ArrayBlockingQueue;

/**
 * 基于数组的阻塞循环队列，队列按FIFO（先进先出）原则对元素进行排序
 *
 * @Author : guantenghua
 * @create 2020/6/12 11:10
 */
public class ArrayBlockingQueueDemo {
    public static void main(String[] args) throws InterruptedException {
        // 3（指定容量），false（是否需要公平，false不需要）
        final ArrayBlockingQueue<String> queue = new ArrayBlockingQueue<>(3, false);
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        System.out.println("取到数据：" + queue.take()); // poll非阻塞
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();

        Thread.sleep(3000);

        for (int i = 0; i < 6; i++) {
            new Thread(() -> {
                try {
                    // put阻塞(如果当前的队列已经塞满了数据，线程不会继续往下执行，等待其他线程
                    queue.put(Thread.currentThread().getName());
                    // 队列的数据拿出去
//                    queue.offer(Thread.currentThread().getName()); // offer非阻塞，满了返回false
                    System.out.println(Thread.currentThread().getName() + "塞入完成");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }
}
