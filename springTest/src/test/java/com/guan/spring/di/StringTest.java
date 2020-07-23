package com.guan.spring.di;

import org.apache.commons.lang.StringUtils;
import org.junit.Assert;
import org.junit.Test;

/**
 * @Author : guantenghua
 * @create 2020/5/18 14:42
 */
public class StringTest {

    @Test
    public void StringTOArray() {
        String str = "123,12345,2345";
        Object[] list = str.split(",");
        if (list.getClass().getComponentType().equals(String.class)) {
            String[] value = (String[]) list;
        }
        Object[] objs = new Integer[]{1, 2, 4, 5, 7};
        System.out.println(objs.getClass().getComponentType());
        if (objs.getClass().getComponentType().equals(Integer.class)) {
            System.out.println("此类型为int类型");
        }
        if (objs.getClass().getComponentType().equals(Long.class)) {
            System.out.println("此类型为long类型");
        }
        System.out.println(list);
    }

    @Test
    public void substringBetween() {
        String str = "2337_5ed5dfa0590801f24810fd6b_2020-5-2";
        String templateId = StringUtils.substringBetween(str, "_", "_");
        System.out.println(templateId);
        Assert.assertEquals("5ed5dfa0590801f24810fd6b", templateId);
        String[] strs = str.split("_");
        String key = strs[0] + "_" + strs[2];
        System.out.println(key);
        Assert.assertEquals("2337_2020-5-2", key);
    }

    @Test
    public void getString() {

    }
}
