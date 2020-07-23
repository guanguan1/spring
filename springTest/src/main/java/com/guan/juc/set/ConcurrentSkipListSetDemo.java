package com.guan.juc.set;

import java.util.Comparator;
import java.util.Iterator;
import java.util.concurrent.ConcurrentSkipListSet;

/**
 * @Author : guantenghua
 * @create 2020/6/1 15:59
 */
public class ConcurrentSkipListSetDemo {
    public static void main(String[] args){
        // 设置比较方式
        ConcurrentSkipListSet<String> skipListSet = new ConcurrentSkipListSet<>(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.compareTo(o2);
            }
        });
        skipListSet.add("aa");
        skipListSet.add("ca");
        skipListSet.add("aa");
        skipListSet.add("da");

        Iterator<String> iterator = skipListSet.iterator();
        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }
    }
}
