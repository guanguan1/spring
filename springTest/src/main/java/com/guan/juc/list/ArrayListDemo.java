package com.guan.juc.list;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * @Author : guantenghua
 * @create 2020/6/1 14:59
 */
public class ArrayListDemo {
    public static void main(String[] args){
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("a");
        arrayList.add("b");
        arrayList.add("c");

        for (int i = arrayList.size() - 1; i >= 0; --i) {
            arrayList.remove(arrayList.get(i));
        }
        Iterator<String> iterator = arrayList.iterator();
        System.out.println(arrayList.size());
        while (iterator.hasNext()){
            //iterator.remove(); // IllegalStateException
            arrayList.remove(iterator.next()); // ConcurrentModificationException
        }
    }
}
