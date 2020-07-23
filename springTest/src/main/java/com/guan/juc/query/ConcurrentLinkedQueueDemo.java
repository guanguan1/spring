package com.guan.juc.query;

import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * 无锁
 * 批量操作不提供原子保证 addAll, removeAll, retainAll, containsAll, equals, toArray
 * size() 方法每次都是遍历整个链表，最好不要频繁调用
 * @Author : guantenghua
 * @create 2020/6/12 11:26
 */
public class ConcurrentLinkedQueueDemo {
    public static void main(String[] args) throws InterruptedException {
        final ConcurrentLinkedQueue<String> queue = new ConcurrentLinkedQueue<>();
        new Thread(new Runnable() {
            @Override
            public void run() {
                while(true){
                    try {
                        System.out.println("取到数据：" + queue.poll()); // poll非阻塞
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
                    System.out.println(Thread.currentThread().getName() + "塞入完成");
                }
            }).start();
        }
    }
}
