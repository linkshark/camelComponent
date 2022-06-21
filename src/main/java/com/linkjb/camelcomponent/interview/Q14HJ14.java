package com.linkjb.camelcomponent.interview;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * @ClassName Q11HJ11
 * @Description TODO
 * @Author shark
 * @Data 2022/6/20 17:40
 **/
//给定 n 个字符串，请对 n 个字符串按照字典序排列。
//
//        数据范围： 1 \le n \le 1000 \1≤n≤1000  ，字符串长度满足 1 \le len \le 100 \1≤len≤100
//        输入描述：
//        输入第一行为一个正整数n(1≤n≤1000),下面n行为n个字符串(字符串长度≤100),字符串中只含有大小写字母。
//        输出描述：
//        数据输出n行，输出结果为按照字典序排列的字符串。
//        示例1
//        输入：
//        9
//        cap
//        to
//        cat
//        card
//        two
//        too
//        up
//        boat
//        boot
//        复制
//        输出：
//        boat
//        boot
//        cap
//        card
//        cat
//        to
//        too
//        two
//        up
//        复制
public class Q14HJ14 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int count = sc.nextInt();
        List<String> list = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            String s = sc.next();
            list.add(s);
        }
        list.stream().sorted(String::compareTo).collect(Collectors.toList()).forEach(s -> {
            System.out.println(s);
        });
    }
}
