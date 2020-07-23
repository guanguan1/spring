package com.guan.thread;

import javafx.scene.control.TableRow;

/**
 * 线程中止
 * @Author : guantenghua
 * @create 2020/5/28 16:39
 */
public class ThreadStop extends Thread{

    private int i, j = 0;
    // 通过状态位来判断线程是否执行完成
    public volatile static boolean flag = true;

    /**
     * If this thread was constructed using a separate
     * <code>Runnable</code> run object, then that
     * <code>Runnable</code> object's <code>run</code> method is called;
     * otherwise, this method does nothing and returns.
     * <p>
     * Subclasses of <code>Thread</code> should override this method.
     *
     * @see #start()
     * @see #stop()
     */
    @Override
    public void run() {
        synchronized (this){
            // 增加同步锁，确保线程安全
            ++i;
            try {
                // 休眠10秒，模拟耗时操作
                Thread.sleep(10000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            ++j;
        }
    }

    public void print(){
        System.out.println("i=" + i + " j=" + j);
    }

    public static void main(String[] args) throws InterruptedException {
        /*// 线程stop强制性中止，破坏线程安全的
        ThreadStop threadStop = new ThreadStop();
        threadStop.start();
        // 休眠1秒，确保i变量自增成功
        Thread.sleep(1000);
        // 暂停线程
//        threadStop.stop(); // 错误的终止
        threadStop.interrupt(); // 正确终止
        while (threadStop.isAlive()) {
            // 确保线程已经终止
        } // 输出结果
        threadStop.print();*/

        /*new Thread(() -> {
            try {
                while (flag) { // 判断是否运行
                    System.out.println("运行中");
                    Thread.sleep(1000L);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        // 3秒之后，将状态标志改为False，代表不继续运行
        Thread.sleep(3000L);
        flag = false;
        System.out.println("程序运行结束");*/
    }
}
