package com.guan.juc.query;

import java.sql.SQLOutput;
import java.util.concurrent.SynchronousQueue;

/**
 * 这是一个神奇的队列，因为他不存数据，手把手的交互数据
 * @Author : guantenghua
 * @create 2020/6/15 13:35
 */
public class SynchronousQuereDemo {

    public static void main(String[] args) throws InterruptedException {
        final SynchronousQueue<String> synchronousQueue = new SynchronousQueue<>();
        System.out.println(synchronousQueue.poll()); // 非阻塞
        // 阻塞式用法
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println(Thread.currentThread().getName() + "插入一条数据");
                    synchronousQueue.put("a");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(3000L);
                    System.out.println("等数据。。。。");
                    System.out.println(synchronousQueue.take());
                    System.out.println("执行完毕。。。。");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        Thread.sleep(1000L);
        System.out.println(Thread.currentThread().getName() + "：准备塞数据了");
        synchronousQueue.put("a");
        System.out.println(Thread.currentThread().getName() + ":数据塞完了");
    }

}
