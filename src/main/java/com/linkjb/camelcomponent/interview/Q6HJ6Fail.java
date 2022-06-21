package com.linkjb.camelcomponent.interview;
//描述
//        功能:输入一个正整数，按照从小到大的顺序输出它的所有质因子（重复的也要列举）（如180的质因子为2 2 3 3 5 ）
//
//
//        数据范围： 1 \le n \le 2 \times 10^{9} + 14 \1≤n≤2×10
//        9
//        +14
//        输入描述：
//        输入一个整数
//
//        输出描述：
//        按照从小到大的顺序输出它的所有质数的因子，以空格隔开。
//
//        示例1
//        输入：
//        180
//        复制
//        输出：
//        2 2 3 3 5
//        复制

import java.util.Scanner;

/**
 * @ClassName Q6HJ6
 * @Description 质数因子
 * @Author shark
 * @Data 2022/6/20 09:11
 **/
public class Q6HJ6Fail {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int input = sc.nextInt();
        printNums(input);
    }

    private static void printNums(int input) {
        int sqrt = (int) Math.sqrt(input);
        for (int i = 2; i <= input; i++) {

            if (input % i == 0) {
                System.out.printf(i + " ");
                printNums(input / i);
                break;
            }
            if (i >= sqrt) {
                System.out.printf(input + " ");
                break;
            }
        }
    }
}
