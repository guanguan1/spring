package com.guan.juc.map;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 使用链表存储数据
 *
 * @Author : guantenghua
 * @create 2020/6/2 9:41
 */
public class HashMapDemo {
    // 队列头部
    Node head = null;

    // 添加节点
    public void add(Node node) {
        if (head == null) {
            head = node;
            return;
        }
        // 追加到队列尾部
        Node temp = head;
        // 找到一个next为空的节点，这个节点是最后一个
        while (temp.next != null) {
            temp = temp.next;
        }
        temp.next = node;
    }

    // 打印链表内容
    public void print() {
        Node temp = head;
        while (temp.next != null) {
            System.out.println(temp.key);
            temp = temp.next;
        }
        System.out.println(temp.next);
    }

    public static void main(String[] args) {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(5, 10, 60, TimeUnit.SECONDS, new LinkedBlockingQueue<>(50));
        threadPoolExecutor.execute(() -> {
            System.out.println("ooo");
        });
        HashMapDemo hashMapDemo = new HashMapDemo();
        Node node1 = new Node();
        node1.key = "1";
        hashMapDemo.add(node1);

        Node node2 = new Node();
        node2.key = "2";
        hashMapDemo.add(node2);

        Node node3 = new Node();
        node3.key = "3";
        hashMapDemo.add(node3);

        hashMapDemo.print();
    }
}
