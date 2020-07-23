package com.guan.data.sort;

import java.util.Random;

/**
 * 排序
 * @Author : guantenghua
 * @create 2020/5/18 10:55
 */
public class Sort {

    // 冒泡排序
    public void bubbleSort(int[] a, int n){
        if(n <= 1){
            return;
        }
        int changeTime = 0;
        for (int i = 0; i < n; ++i) {
            // 提前退出冒泡循环的标志位
            boolean flag = false;
            for (int j = 0; j < n - i -1; ++j) {
                // 交换
                if(a[j] > a[j+1]){
                    ++changeTime;
                    int tmp = a[j];
                    a[j] = a[j + 1];
                    a[j + 1] = tmp;
                    flag = true;
                }
            }
            if(!flag){
                break;
            }
        }
        // 交换次数（逆序度） = 满有序度 - 有序度
        // 满有序度 n*(n - 1)/2
        // 有序度 : 有序个数
        System.out.println("冒泡排序交换了：" + changeTime + "次");
    }

    // 插入排序
    public void insertSort(int[] a, int n){
        if(n <= 1){
            return;
        }
        int changeTime = 0;
        for (int i = 0; i < n; ++i) {
            int value = a[i];
            int j = i - 1;
            // 查找插入的位置
            for (; j >= 0; --j) {
                if(a[j] > value){
                    // 数据移动
                    a[j + 1] = a[j];
                    ++changeTime;
                }else {
                    break;
                }
            }
            a[j + 1] = value;
        }
        System.out.println("插入排序交换了：" + changeTime + "次");
    }

    // 计数排序,a是数组，n是数组大小。假设数组中存储的都是非负整数
    public void countingSort(int[] a, int n){
        if(n <= 1) return;
        // 查找数组中数据的范围
        int max = a[0];
        for (int i = 1; i < n; i++) {
            if(max < a[i]){
                max = a[i];
            }
        }

        // 申请一个计数数组c，下标大小[0, max]
        int[] c = new int[max + 1];
        for (int i = 0; i < max; i++) {
            c[i] = 0;
        }
        // 依次累加
        for (int i = 1; i < max; i++) {
            c[i] = c[i - 1] + c[i];
        }
        // 临时数组r，存储排序之后的结果
        int[] r = new int[n];
        // 计算排序的关键步骤
        for (int i = n - 1; i >= 0; --i) {
            int index = c[a[i]] - 1;
            r[index] = a[i];
            c[a[i]]--;
        }
        // 将结果拷贝给a数组
        for (int i = 0; i < n; i++) {
            a[i] = r[i];
        }
    }
    
    public static void main(String[] args){
        // 有序度 = 13 {(3,3),(3,8),(3,6),(3,9),(1,3),(1,8),(1,6),(1,9),(3,8),(3,6),(3,9),(8,9),(6,9)}
        // 满有序度 = 6 * (6 - 1) / 2 = 15
        int bubbleSort[] = new int[200000];
        Random random = new Random(1000);
        for (int i = 0; i <bubbleSort.length - 1 ; i++) {
            bubbleSort[i] = random.nextInt(1000);
        }
        Sort sort = new Sort();
        Long start = System.currentTimeMillis();
//        sort.bubbleSort(bubbleSort, bubbleSort.length);
        sort.insertSort(bubbleSort, bubbleSort.length);
        Long end = System.currentTimeMillis();
        System.out.println("排序耗时：" + (end - start) + "ms");
        /*
            20000个元素：
                插入排序交换了：99675832次，排序耗时：103ms
                冒泡排序交换了：99675832次，排序耗时：1276ms
            200000个元素：
                插入排序交换了：1420461487次，排序耗时：6482ms
                冒泡排序交换了：1420461487次，排序耗时：103475ms
         */
        System.out.println(bubbleSort);
    }
}
