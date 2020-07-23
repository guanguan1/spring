package com.guan.spring.di;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @Author : guantenghua
 * @create 2020/5/30 11:49
 */
public class LamdaTest {
    @Test
    public void stringToListInt() {
        String type = "1,2,4";
        List<Integer> list = Arrays.stream(type.split(",")).map(s -> Integer.parseInt(s.trim())).collect(Collectors.toList());
        System.out.println(list);
    }

    @Test
    public void getSetList(){
        List<String> recivedIds = Arrays.asList("123,345,345,".split(","));
        Set<Long> set = recivedIds.stream().map(e -> Long.parseLong(e)).collect(Collectors.toSet());
        System.out.println(set);
        System.out.println(Long.parseLong("1234"));
        System.out.println(Long.getLong("45678", 123));
    }
}
