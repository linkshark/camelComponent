package com.linkjb.camelcomponent.interview;


import java.util.Scanner;

/**
 * @ClassName TrueQ1
 * @Description TODO
 * @Author shark
 * @Data 2022/6/20 10:46
 **/
/*
*
 * @Author shark
 * @Description //输入: 第一行输入数组个数,第二行输入数组,第三行输入N
输出:数组中最大的N个数和最小的N个数的和,这最值的几个数中,有重复的输出-1
 * @Date 2022/6/20
 * @Param
 * @return
 **/
public class TrueQ1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            int count = scanner.nextInt();
            int[] arr = new int[count];
            for (int i = 0; i < count; i++) {
                arr[i] = scanner.nextInt();
            }
            int num = scanner.nextInt();
            if (num <= 0) {
                System.out.println("-1");
            }
            for (int i = 0; i < arr.length - 1; i++) {
                for (int j = 0; j < arr.length - 1; j++) {
                    if (arr[j] > arr[j + 1]) {
                        int temp = arr[j];
                        arr[j] = arr[j + 1];
                        arr[j + 1] = temp;
                    }
                }
            }


        }
    }
}
