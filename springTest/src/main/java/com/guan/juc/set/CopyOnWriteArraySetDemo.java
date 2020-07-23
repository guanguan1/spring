package com.guan.juc.set;

import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * @Author : guantenghua
 * @create 2020/6/1 15:52
 */
public class CopyOnWriteArraySetDemo {

    public static void main(String[] args){
        CopyOnWriteArraySet<String> copayOnWriteArraySet = new CopyOnWriteArraySet<>();
        copayOnWriteArraySet.add("aa");
        copayOnWriteArraySet.add("ca");
        copayOnWriteArraySet.add("aa");
        copayOnWriteArraySet.add("da");

        Iterator<String> iterator = copayOnWriteArraySet.iterator();
        while (iterator.hasNext()){
            copayOnWriteArraySet.remove(iterator.next());
        }
        System.out.println(copayOnWriteArraySet.size());
        System.out.println(copayOnWriteArraySet);
    }
}
