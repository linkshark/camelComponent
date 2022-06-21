package com.linkjb.camelcomponent.interview;

import java.util.Scanner;

/**
 * @ClassName Q11HJ11
 * @Description TODO
 * @Author shark
 * @Data 2022/6/20 17:40
 **/
//描述
//        输入一个整数，将这个整数以字符串的形式逆序输出
//        程序不考虑负数的情况，若数字含有0，则逆序形式也含有0，如输入为100，则输出为001
//
//
//        数据范围： 0 \le n \le 2^{30}-1 \0≤n≤2
//        30
//        −1
//        输入描述：
//        输入一个int整数
//
//        输出描述：
//        将这个整数以字符串的形式逆序输出
//
//        示例1
//        输入：
//        1516000
//        复制
//        输出：
//        0006151
//        复制
//        示例2
//        输入：
//        0
//        复制
//        输出：
//        0
public class Q13HJ13 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        String[] s1 = s.split(" ");
        for (int i = s1.length - 1; i >= 0; i--) {
            System.out.print(s1[i] + " ");
        }
    }
}
