package com.linkjb.camelcomponent.interview;

import java.util.Scanner;

/**
 * @ClassName Q11HJ11
 * @Description TODO
 * @Author shark
 * @Data 2022/6/20 17:40
 **/
//描述
//        输入一个 int 型的正整数，计算出该 int 型数据在内存中存储时 1 的个数。
//
//        数据范围：保证在 32 位整型数字范围内
//        输入描述：
//        输入一个整数（int类型）
//
//        输出描述：
//        这个数转换成2进制后，输出1的个数
//
//        示例1
//        输入：
//        5
//        复制
//        输出：
//        2
//        复制
//        示例2
//        输入：
//        0
//        复制
//        输出：
//        0
public class Q15HJ15 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Integer number = sc.nextInt();
        String s = Integer.toBinaryString(number);
        char[] chars = s.toCharArray();
        int count = 0;
        for (int i = 0; i < chars.length; i++) {
            if ('1' == chars[i]) {
                count++;
            }
        }
        System.out.println(count);


    }
}
