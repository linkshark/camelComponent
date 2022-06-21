package com.linkjb.camelcomponent.interview;

import java.util.LinkedHashSet;
import java.util.Scanner;
import java.util.Set;

/**
 * @ClassName Q9Hj9
 * @Description TODO
 * @Author shark
 * @Data 2022/6/20 16:53
 **/
//输入一个 int 型整数，按照从右向左的阅读顺序，返回一个不含重复数字的新的整数。
//        保证输入的整数最后一位不是 0 。
//
//        数据范围： 1 \le n \le 10^{8} \1≤n≤10
//        8
//
//        输入描述：
//        输入一个int型整数
//
//        输出描述：
//        按照从右向左的阅读顺序，返回一个不含重复数字的新的整数
//
//        示例1
//        输入：
//        9876673
//        复制
//        输出：
//        37689
public class Q9HJ9 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();

        char[] chars = s.toCharArray();
        Set<Integer> set = new LinkedHashSet<>();
        for (int i = chars.length - 1; i >= 0; i--) {
            int i1 = Integer.parseInt(String.valueOf(chars[i]));
            if (set.add(i1)) {
                System.out.print(i1);
            }
        }

    }
}
