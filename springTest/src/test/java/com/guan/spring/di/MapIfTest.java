package com.guan.spring.di;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author : guantenghua
 * @create 2020/6/8 16:41
 */
public class MapIfTest {

    @Test
    public void MapIf(){
        Map<String, String> map = new HashMap<>();
        map.put("test", "100");
        map.put("test01", "100");
        if(StringUtils.isNotBlank(MapUtils.getString(map, "test"))){
            System.out.println(MapUtils.getLong(map, "test"));
        }
        if(StringUtils.isNotBlank(MapUtils.getString(map, "test01"))){
            System.out.println(MapUtils.getLong(map, "test01"));
        }
    }

    @Test
    public void MapList(){
        List<String> one = new ArrayList<>();
        one.add("1");
        one.add("2");
        one.add("3");
        List<String> two = new ArrayList<>();
        two.add("1");
        two.add("3");
        List<String> three = new ArrayList<>();
        three.add("1");
        Map<String, List<String>> mapList = new HashMap<>();
        mapList.put("one", one);
        mapList.put("two", two);
        mapList.put("three", three);
        int maxRow = 3;
//        List<String> stringList = (List<String>) MapUtils.getObject(mapList, "one");
        List<Map<String, String>> result = new ArrayList<>();
        for (Map.Entry<String, List<String>> map : mapList.entrySet()) {
            Map<String, String> data = new HashMap<>();
            if(map.getValue().size() == 1){
                data.put("", map.getValue().get(1));
            }
            for (int i = 0; i < maxRow; i++) {
                int thisMaxRow = map.getValue().size();
                List<String> strs = map.getValue();
                if(i > thisMaxRow - 1){
                    continue;
                }
                data.put(i + "", strs.get(i));
                result.add(data);
            }
        }
        System.out.println(result);
    }

}
