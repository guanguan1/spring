package com.guan.data.search;

/**
 *
 *
 * @Author : guantenghua
 * @create 2020/5/22 17:14
 */
public class SearchFirstLastData {
    // 查找第一个值等于给定值的元素
    public int bsearchFirstData(int[] a, int n, int value) {
        int low = 0;
        int high = n - 1;
        while(low <= high){
            int mid = low + ((high - low) >> 1);
            if(a[mid] > value){
                high = mid - 1;
            }else if(a[mid] < value){
                low = mid + 1;
            }else{
                if((mid == 0) || (a[mid - 1] != value))
                    return mid;
                else high = mid - 1;
            }
        }
        return -1;
    }

    public int bsearchLastData(int[] a, int n, int value) {
        int low = 0;
        int high = n - 1;
        while(low <= high){
            int mid = low + ((high - low) >> 1);
            if(a[mid] > value){
                high = mid - 1;
            }else if(a[mid] < value){
                low = mid + 1;
            }else{
                if((mid == n - 1) || (a[mid + 1] != value)){
                    return mid;
                }else
                    low = mid + 1;
            }
        }
        return -1;
    }


    public static void main(String[] args) {
        int[] a = {1, 3, 8, 8, 8, 11, 18};
        SearchFirstLastData firstData = new SearchFirstLastData();
        int firstIndex = firstData.bsearchFirstData(a, a.length, 8);
        System.out.println("第一个值为8的位置是：" + firstIndex);
        int lastIndex = firstData.bsearchLastData(a, a.length, 8);
        System.out.println("最后一个值为8的位置是：" + lastIndex);
    }
}
