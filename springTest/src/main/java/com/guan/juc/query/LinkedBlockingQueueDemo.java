package com.guan.juc.query;

import java.util.concurrent.LinkedBlockingQueue;

/**
 * 基于链表队列，队列按FIFO（先进先出）排序元素
 * 如果有阻塞需求，用这个。类似生产者消费者场景
 *
 * @Author : guantenghua
 * @create 2020/6/12 17:32
 */
public class LinkedBlockingQueueDemo {
    public static void main(String[] args) throws InterruptedException {
        final LinkedBlockingQueue<String> queue = new LinkedBlockingQueue(3);
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        System.out.println("取到数据：" + queue.poll());
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();

        Thread.sleep(3000);

        for (int i = 0; i < 6; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    queue.offer(Thread.currentThread().getName()); // offer非阻塞，满了返回false
                    System.out.println(Thread.currentThread() + "塞入完成");
                }
            }).start();
        }
    }
}
