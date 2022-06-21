package com.linkjb.camelcomponent.interview;
//描述
//        写出一个程序，接受一个由字母、数字和空格组成的字符串，和一个字符，然后输出输入字符串中该字符的出现次数。（不区分大小写字母）
//
//        数据范围： 1 \le n \le 1000 \1≤n≤1000
//        输入描述：
//        第一行输入一个由字母和数字以及空格组成的字符串，第二行输入一个字符。
//
//        输出描述：
//        输出输入字符串中含有该字符的个数。（不区分大小写字母）
//
//        示例1
//        输入：
//        ABCabc
//        A
//        复制
//        输出：
//        2


import java.util.Scanner;

public class Q2HJ2 {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        String allWord = s.nextLine().toLowerCase();
        String specificWord = s.nextLine().toLowerCase();
        char[] chars = allWord.toCharArray();
        int count = 0;
        for (int i = 0; i < chars.length; i++) {
            char aChar = chars[i];
            if (specificWord.toCharArray()[0] == aChar) {
                count++;
            }
        }
        System.out.println(count);


    }
}
