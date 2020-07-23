package com.guan.data.search;

import org.junit.Assert;

/**
 * @Author : guantenghua
 * @create 2020/5/22 10:28
 */
public class Search {
    // 二分查找
    public int bsearch(int[] a, int n, int value){
        int low = 0;
        int high = n - 1;
        int time = 0;
        while(low <= high){
            ++time;
            int mid = (low + high) / 2;
            if(a[mid] == value){
                System.out.println("查找次数为：" + time);
                return mid;
            }else if(a[mid] < value){
                low = mid + 1;
            }else {
                high = mid - 1;
            }
        }
        return -1;
    }

    // 二分查找的递归实现
    public int bsearchByDG(int[] a, int n, int value){
        return bsearchInternally(a, 0, n -1, value);
    }
    int time = 0;
    private int bsearchInternally(int[] a, int low, int high, int value) {
        ++time;
        if(low > high){
            return -1;
        }
        int mid = low + ((high - low) >> 1);
        if(a[mid] == value){
            System.out.println("查找次数为：" + time);
            return mid;
        }else if(a[mid] < value){
            return bsearchInternally(a, mid + 1, high, value);
        }else{
            return bsearchInternally(a, low, mid - 1, value);
        }
    }

    public static void main(String[] args){
        int[] a = {2, 4, 6, 7, 9, 13, 17, 19, 20};
        Search search = new Search();
        int index = search.bsearch(a, a.length, 19);
        Assert.assertEquals(index, 7);

        int indexByDG = search.bsearchInternally(a, 0, a.length, 19);
        Assert.assertEquals(index, 7);
    }
}
