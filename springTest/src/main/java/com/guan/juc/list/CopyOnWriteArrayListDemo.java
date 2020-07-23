package com.guan.juc.list;

import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @Author : guantenghua
 * @create 2020/6/1 15:06
 */
public class CopyOnWriteArrayListDemo {
    public static void main(String[] args){
        CopyOnWriteArrayList<String> arrayList = new CopyOnWriteArrayList<>();
        arrayList.add("a");
        arrayList.add("b");
        arrayList.add("c");
        Iterator<String> iterator = arrayList.iterator();
        while (iterator.hasNext()){
//            iterator.remove(); // UnsupportedOperationException
            arrayList.remove(iterator.next());
        }
        System.out.println(arrayList.size());
    }


}
