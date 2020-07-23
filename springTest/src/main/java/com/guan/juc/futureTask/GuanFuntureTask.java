package com.guan.juc.futureTask;

import java.util.concurrent.Callable;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.locks.LockSupport;

/**
 * 自己实现futureTask
 * @Author : guantenghua
 * @create 2020/6/15 13:49
 */
public class GuanFuntureTask<T> implements Runnable {

    Callable<T> callable;
    // callable执行结果
    T result;
    // task 执行状态
    String state = "new";

    /**
     * 存储正在等待的消费者
     */
    LinkedBlockingQueue<Thread> waiters = new LinkedBlockingQueue<>();

    public GuanFuntureTask(Callable<T> callable) {
        this.callable = callable;
    }

    @Override
    public void run() {
        try {
            result = callable.call();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            state = "end";
        }
        System.out.println(Thread.currentThread().getName() + "生产者执行结束，通知消费者");

        while(true){
            Thread waiter = waiters.poll();
            if(waiter == null){
                break;
            }
            LockSupport.unpark(waiter);
        }
    }

    // park / unpark
    public T get() {
        Thread mainThread = Thread.currentThread();
        waiters.add(mainThread);
        // 判断状态
        System.out.println(Thread.currentThread().getName() + "消费者进入等待");
        while(!"end".equals(state)){
            LockSupport.park(mainThread);
        }
        return result;
    }
}
