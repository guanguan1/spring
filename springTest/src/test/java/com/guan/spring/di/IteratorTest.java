package com.guan.spring.di;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @Author : guantenghua
 * @create 2020/7/1 9:33
 */
public class IteratorTest {
    
    @Test
    public void iterTest(){
        List<Integer> iter = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            iter.add(i);
        }
        Iterator<Integer> iterator = iter.iterator();
        while(iterator.hasNext()){
            iterator.remove();
        }
    }
}
