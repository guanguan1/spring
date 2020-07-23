package com.guan.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 三数之和
 * @Author : guantenghua
 * @create 2020/6/24 10:35
 */
public class ThreeSum {
    public List<List<Integer>> threeSum(int[] nums){
        int n = nums.length;
        Arrays.sort(nums);
        List<List<Integer>> ans = new ArrayList<>();
        // 枚举a
        for (int first = 0; first < n; first++) {
            // 需要和上一次枚举的数不同
            if(first > 0 && nums[first] == nums[first - 1]){
                continue;
            }
            // c 对应的指针初始指向数组的最右端
            int third = n - 1;
            int target = -nums[first];
            // 枚举b
            for (int second = first + 1; second < n; second++) {
                // 需要和上一次枚举的数不同
                if(second > first + 1 && nums[second] == nums[second - 1]){
                    continue;
                }
                // 需要保证b的指针在c的指针左侧
                while(second < third && nums[second] + nums[third] > target){
                    --third;
                }
                // 如果指针重合，随着b后续的增加，就不会有满足 a+b+c=0 并且b<c的c了，可以退出循环
                if(second == third){
                    break;
                }
                if(nums[second] + nums[third] == target){
                    List<Integer> list = new ArrayList<>();
                    list.add(nums[first]);
                    list.add(nums[second]);
                    list.add(nums[third]);
                    ans.add(list);
                }
            }
        }
        return ans;
    }

    public static void main(String[] args){
        ThreeSum threeSum = new ThreeSum();
        List<List<Integer>> list = threeSum.threeSum(new int[] {-1, 0, 1, 2, -1, -4});
        System.out.println(list);
    }
}
