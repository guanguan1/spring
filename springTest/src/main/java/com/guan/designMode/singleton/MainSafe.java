package com.guan.designMode.singleton;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author : guantenghua
 * @create 2020/4/2 15:54
 */
public class MainSafe {
    public static void main(String[] args){
        final Runnable taskTemp = getTaskTemp();
        final CountDownLatch start = new CountDownLatch(1);
        final CountDownLatch end = new CountDownLatch(5);
        for (int i = 0; i < 5; i++) {
            Thread t = new Thread(){
                public void run(){
                    try {
                        // 使线程在此等待，当开始门打开时，一起涌入门中
                        start.await();
                        taskTemp.run();
                        end.countDown();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            };
            t.start();
        }
        long startTime = System.nanoTime();
        System.out.println(startTime + " [" + Thread.currentThread() + "] All thread is ready, concurrent going...");
        start.countDown();
        try {
            end.await();
            long endTime = System.nanoTime();
            System.out.println(endTime + " [" + Thread.currentThread() + "] All thread is completed.");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static Runnable getTaskTemp() {
        return new Runnable() {

            private AtomicInteger iCounter = new AtomicInteger();

            public void run() {
                for (int i = 0; i < 10; i++) {
                    iCounter.incrementAndGet();
                    System.out.println(System.nanoTime() + "[" + Thread.currentThread().getName() + "]iCounter = " + iCounter.get());
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
    }
}
