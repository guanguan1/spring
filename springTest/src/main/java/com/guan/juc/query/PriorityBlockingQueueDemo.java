package com.guan.juc.query;

import java.util.concurrent.PriorityBlockingQueue;

/**
 * 包装了ProporityQueue
 * 是一个带优先级的队列，而不是先进先出队列。
 * 元素按照优先级顺序移除，该队列没有上线
 * 没有容量限制，自动扩容
 * 虽然此队列逻辑上是无界的，但是由于资源被耗尽，所以试图执行添加操作可能会导致OutOfMemoryError
 * 但是如果队列为空，那么取元素的操作take就会阻塞，所以它的检索操作take是受阻的，另外入该队列中的元素要具有比较能力
 * @Author : guantenghua
 * @create 2020/6/15 11:55
 */
public class PriorityBlockingQueueDemo {
    public static void main(String[] args){
        PriorityBlockingQueue<String> prioityQueue = new PriorityBlockingQueue<>(2);
        prioityQueue.add("c");
        prioityQueue.add("a");
        prioityQueue.add("b");
        prioityQueue.add("f");
        prioityQueue.add("e");
        prioityQueue.add("p");
        prioityQueue.add("j");
        System.out.println(prioityQueue.poll());
        System.out.println(prioityQueue.poll());
        System.out.println(prioityQueue.poll());
        System.out.println(prioityQueue.poll());
        System.out.println(prioityQueue.poll());
        System.out.println(prioityQueue.poll());
        System.out.println(prioityQueue.poll());
    }
}
