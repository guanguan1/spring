package com.guan.data;

/**
 * @Author : guantenghua
 * @create 2020/5/15 11:23
 */
public class ArrayQueue {
    // 数组：items
    private String[] items;
    // 数组大小：n
    private int n = 0;

    public int getHead() {
        return head;
    }

    public int getTail() {
        return tail;
    }

    // head表示队列头部下标
    private int head = 0;
    // tail表示队尾下标
    private int tail = 0;
    // 申请一个大小为capacity
    public ArrayQueue(int capacity){
        items = new String[capacity];
        n = capacity;
    }

    // 入队列
    public boolean enqueue(String item){
        // 如果head == tail 表示队列为空
        if(head == n){
            if(head == 0) return false;
            // 数据搬移
            for (int i = head; i < tail; i++){
                items[i - head] = items[i];
            }
            tail -= head;
            head = 0;
        }
        items[tail] = item;
        ++tail;
        return true;
    }

    // 出队
    public String dequeue(){
        if(head == tail)
            return null;
        String ret = items[head];
        ++head;
        return ret;
    }

    public static void main(String[] args){
        ArrayQueue queue = new ArrayQueue(3);
        queue.enqueue("1");
        queue.enqueue("2");
        queue.enqueue("3");
        System.out.println("头部位置：" + queue.getHead());
        System.out.println("尾部位置：" + queue.getTail());
        queue.dequeue();
        queue.dequeue();
        queue.dequeue();
        System.out.println("取出3个值后-头部位置：" + queue.getHead());
        System.out.println("取出3个值后-尾部位置：" + queue.getTail());
        queue.enqueue("4");
        System.out.println("取出3个值后，在添加一个数据后-头部位置：" + queue.getHead());
        System.out.println("取出3个值后，在添加一个数据后-尾部位置：" + queue.getTail());
    }
}
