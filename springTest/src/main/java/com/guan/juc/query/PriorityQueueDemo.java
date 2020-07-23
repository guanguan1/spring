package com.guan.juc.query;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 是一个带优先级的队列，而不是先进先出队列。
 * 元素按照优先级顺序移除，该队列没有上线
 * 没有容量限制，自动扩容
 * 虽然此队列逻辑上是无界的，但是由于资源被耗尽，所以试图执行添加操作可能会导致OutOfMemoryError
 * 但是如果队列为空，那么取元素的操作take就会阻塞，所以它的检索操作take是受阻的，另外入该队列中的元素要具有比较能力
 *
 * @Author : guantenghua
 * @create 2020/6/15 11:55
 */
public class PriorityQueueDemo {
    public static void main(String[] args) {
        // 可以设置比对方式
        PriorityQueue<String> priorityQueue = new PriorityQueue<>(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return 0;
            }
        });
        priorityQueue.add("c");
        priorityQueue.add("a");
        priorityQueue.add("b");
        priorityQueue.add("f");
        priorityQueue.add("e");
        priorityQueue.add("p");
        priorityQueue.add("j");
        System.out.println(priorityQueue.poll());
        System.out.println(priorityQueue.poll());
        System.out.println(priorityQueue.poll());
        System.out.println(priorityQueue.poll());
        System.out.println(priorityQueue.poll());
        System.out.println(priorityQueue.poll());
        System.out.println(priorityQueue.poll());

        PriorityQueue<MessageObject> MessageObjectQueue = new PriorityQueue<MessageObject>(new Comparator<MessageObject>() {
            @Override
            public int compare(MessageObject o1, MessageObject o2) {
                return o1.order < o2.order ? -1 : 1;
            }
        });

        MessageObject messageObject = new MessageObject();
        messageObject.order = 3;
        MessageObjectQueue.add(messageObject);
        MessageObject messageObject2 = new MessageObject();
        messageObject2.order = 2;
        MessageObjectQueue.add(messageObject2);
        MessageObject messageObject3 = new MessageObject();
        messageObject3.order = 1;
        MessageObjectQueue.add(messageObject3);

        System.out.println(MessageObjectQueue.poll().order);
        System.out.println(MessageObjectQueue.poll().order);
        System.out.println(MessageObjectQueue.poll().order);
    }
}

class MessageObject {
    String content;
    int order;
}
