package com.guan.juc.set;

import java.util.HashSet;
import java.util.Iterator;

/**
 * @Author : guantenghua
 * @create 2020/6/1 15:38
 */
public class HashSetDemo {
    public static void main(String[] args){
        HashSet<String> hashSet = new HashSet<>();
        hashSet.add("aa");
        hashSet.add("ca");
        hashSet.add("aa");
        hashSet.add("da");
        Iterator<String> iterator = hashSet.iterator();
//        while (iterator.hasNext()){
//            iterator.remove(); // IllegalStateException
//            hashSet.remove(iterator.next()); // ConcurrentModificationException
//        }
    }
}
