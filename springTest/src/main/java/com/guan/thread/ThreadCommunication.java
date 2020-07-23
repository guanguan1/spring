package com.guan.thread;

import java.util.concurrent.locks.LockSupport;

/**
 * 线程通讯
 * @Author : guantenghua
 * @create 2020/5/28 16:57
 */
public class ThreadCommunication {
    // 庆丰包子铺
    public static Object qifengbaozi = null;

    /** 正常suspend/resume */
    public void suspendResumeTest() throws InterruptedException {
        Thread consumerThread = new Thread(() -> {
            // 如果没包子，则进入等待
            if(qifengbaozi == null){
                System.out.println("1、进入等待");
                Thread.currentThread().suspend();
            }
            System.out.println("2、买到包子，回家");
        });
        consumerThread.start();
        // 3秒之后，生产一个包子
        Thread.sleep(3000L);
        qifengbaozi = new Object();
        consumerThread.resume();
        System.out.println("3、通知消费者");
    }

    /** 死锁的suspend/resume。 suspend并不会像wait一样释放锁，故此容易写出死锁代码 */
    public void suspendResumeDeadLockTest() throws Exception {
        Thread consumerThread = new Thread(() -> {
            System.out.println("1、进入等待");
            // 当前线程拿到锁，然后挂起
            synchronized (this) {
                System.out.println("当前" + Thread.currentThread().getName() + "状态：" + Thread.currentThread().getState().toString());
                Thread.currentThread().suspend();
            }
            System.out.println("2、买到包子，回家");
        });
        consumerThread.start();
        // 3秒之后，生产一个包子
        Thread.sleep(3000L);
        qifengbaozi = new Object();
        // 争取到锁以后，再恢复consumerThread
        synchronized (this) {
            consumerThread.resume();
        }
        System.out.println("3、通知消费者");
    }

    /** 导致程序永久挂起的suspend/resume */
    public void suspendResumeDeadLockTest2() throws Exception {
        Thread consumerThread = new Thread(() -> {
            if(qifengbaozi == null){
                System.out.println("1、没包子，进入等待");
                try {
                    Thread.sleep(5000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                // 这里的挂起执行在resume后面
                Thread.currentThread().suspend();
            }
        });
        consumerThread.start();
        // 3秒之后，生产一个包子
        Thread.sleep(3000L);
        qifengbaozi = new Object();
        consumerThread.resume();
        System.out.println("3、通知消费者");
        consumerThread.join();
    }

    /** 正常的wait/notify */
    public void waitNotifyTest() throws InterruptedException {
        new Thread(() -> {
           if(qifengbaozi == null){
               synchronized (this){
                   try {
                       System.out.println("1、进入等待");
                       System.out.println(Thread.currentThread().getName() + "线程状态：" + Thread.currentThread().getState());
                       this.wait();
                   } catch (InterruptedException e) {
                       e.printStackTrace();
                   }
               }
           }
            System.out.println("2、买到包子，回家");
        }).start();
        Thread.sleep(3000L);
        qifengbaozi = new Object();
        synchronized (this){
            System.out.println(Thread.currentThread().getName() + "线程状态：" + Thread.currentThread().getState());
            this.notifyAll();
            System.out.println("3、通知消费者");
        }
    }

    /** 会导致程序永久等待的wait/notify */
    public void waitNotifyDeadLockTest() throws Exception {
        // 启动线程
        new Thread(() -> {
            if (qifengbaozi == null) { // 如果没包子，则进入等待
                try {
                    Thread.sleep(5000L);
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }
                synchronized (this) {
                    try {
                        System.out.println("1、进入等待");
                        this.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
            System.out.println("2、买到包子，回家");
        }).start();
        // 3秒之后，生产一个包子
        Thread.sleep(3000L);
        qifengbaozi = new Object();
        synchronized (this) {
            this.notifyAll();
            System.out.println("3、通知消费者");
        }
    }

    /** 正常的park/unpark */
    public void parkUnparkTest() throws InterruptedException {
        Thread consumerThread = new Thread(() -> {
            if(qifengbaozi == null){
                System.out.println("1、进入等待");
                LockSupport.park();
            }
            System.out.println("2、买到包子，回家");
        });
        consumerThread.start();
        // 3秒之后，生产一个包子
        Thread.sleep(3000L);
        qifengbaozi = new Object();
        LockSupport.unpark(consumerThread);
        System.out.println("3、通知消费者");
    }
    /** 死锁的park/unpark */
    public void parkUnparkDeadLockTest() throws Exception {
        // 启动线程
        Thread consumerThread = new Thread(() -> {
            if (qifengbaozi == null) { // 如果没包子，则进入等待
                System.out.println("1、进入等待");
                // 当前线程拿到锁，然后挂起
                synchronized (this) {
                    LockSupport.park();
                }
            }
            System.out.println("2、买到包子，回家");
        });
        consumerThread.start();
        // 3秒之后，生产一个包子
        Thread.sleep(3000L);
        qifengbaozi = new Object();
        // 争取到锁以后，再恢复consumerThread
        synchronized (this) {
            LockSupport.unpark(consumerThread);
        }
        System.out.println("3、通知消费者");
    }

    public static void main(String[] args) throws Exception {
        // 对调用顺序有要求，也要开发自己注意锁的释放。这个被弃用的API， 容易死锁，也容易导致永久挂起。
//         new ThreadCommunication().suspendResumeTest();
//         new ThreadCommunication().suspendResumeDeadLockTest();
//         new ThreadCommunication().suspendResumeDeadLockTest2();

        // wait/notify要求再同步关键字里面使用，免去了死锁的困扰，但是一定要先调用wait，再调用notify，否则永久等待了
         new ThreadCommunication().waitNotifyTest();
//         new ThreadCommunication().waitNotifyDeadLockTest();

        // park/unpark没有顺序要求，但是park并不会释放锁，所有再同步代码中使用要注意
//         new ThreadCommunication().parkUnparkTest();
//         new ThreadCommunication().parkUnparkDeadLockTest();
    }
}
