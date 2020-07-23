package com.guan.leetcode;

import java.util.logging.Level;

/**
 * 二进制求和
 * @Author : guantenghua
 * @create 2020/6/23 11:32
 */
public class AddBinary {

    String addBinary(String a, String b){
        StringBuilder ans = new StringBuilder();
        int n = Math.max(a.length(), b.length()), carry = 0;
        for (int i = 0; i < n; i++) {
            System.out.println("i:" + i + "carry:" + carry);
            carry += i < a.length() ? (a.charAt(a.length() -1 -i) - '0') : 0;
            carry += i < b.length() ? (b.charAt(b.length() -1 -i) - '0') : 0;
            ans.append((char) (carry % 2 + '0'));
            carry /= 2;
        }
        if(carry > 0){
            ans.append('1');
        }
        ans.reverse();
        return ans.toString();
    }

    String addBinarySecond(String a, String b){
        StringBuilder ans = new StringBuilder();
        int ca = 0;
        for (int i = a.length() - 1, j = b.length() - 1; i >= 0 || j >= 0 ; i--, j--) {
            int sum = ca;
            sum += i >= 0 ? a.charAt(i) - '0' : 0;
            sum += j >= 0 ? b.charAt(j) - '0' : 0;
            ans.append(sum % 2);
            ca = sum / 2;
        }
        ans.append('1');
        return ans.reverse().toString();
    }


    public static void main(String[] args){
        AddBinary addBinary = new AddBinary();
        String str = addBinary.addBinary("1234", "12345");
        System.out.println(str);
        System.out.println(addBinary.addBinarySecond("1234", "12345"));
    }
}
