package com.guan.data;

/**
 * 循环队列会浪费一个数组的存储空间
 * @Author : guantenghua
 * @create 2020/5/15 13:17
 */
public class CricilarQueue {
    // 数组：items
    private String[] items;
    // 数组大小：n
    private int n;

    public int getHead() {
        return head;
    }

    public int getTail() {
        return tail;
    }

    // head表示队头下标，tail表示队尾下标
    public int head = 0;
    public int tail = 0;
    // 申请一个大小为capacity的数组
    public CricilarQueue(int capacity){
        items = new String[capacity];
        n = capacity;
    }
    // 入队
    public boolean enqueue(String item){
        // 队满 :当队列满时， tail 指向的位置实际上是没有存储数据的。所以，循环队列会浪费一个数组的存储空间。
        if((tail + 1) % n == head) return false;
        items[tail] = item;
        tail = (tail + 1) % n;
        return true;
    }
    // 出队
    public String dequeue(){
        if(head == tail) return null;
        String ret = items[head];
        head = (head + 1) % n;
        return ret;
    }

    public static void main(String[] args){
        CricilarQueue queue = new CricilarQueue(3);

        System.out.println(queue.enqueue("1"));
        System.out.println(queue.enqueue("2"));
        System.out.println(queue.enqueue("3"));
        System.out.println("头部位置：" + queue.getHead());
        System.out.println("尾部位置：" + queue.getTail());
        System.out.println(queue.dequeue());
        System.out.println(queue.dequeue());
        System.out.println(queue.dequeue());
        System.out.println("取出3个值后-头部位置：" + queue.getHead());
        System.out.println("取出3个值后-尾部位置：" + queue.getTail());
        System.out.println(queue.enqueue("4"));
        System.out.println("取出3个值后，在添加一个数据后-头部位置：" + queue.getHead());
        System.out.println("取出3个值后，在添加一个数据后-尾部位置：" + queue.getTail());
    }
}
