package com.linkjb.camelcomponent.interview;

import java.util.Scanner;
//描述
//        •输入一个字符串，请按长度为8拆分每个输入字符串并进行输出；
//
//        •长度不是8整数倍的字符串请在后面补数字0，空字符串不处理。
//        输入描述：
//        连续输入字符串(每个字符串长度小于等于100)
//
//        输出描述：
//        依次输出所有分割后的长度为8的新字符串
//
//        示例1
//        输入：
//        abc
//        复制
//        输出：
//        abc00000
public class Q4HJ4 {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        String s1 = s.nextLine();
        int length = s1.length();
        if (length % 8 != 0) {
            int i = 8 - (length % 8);
            for (int temp = 0; temp < i; temp++) {
                s1 = s1 + 0;
            }
        }
        int i = s1.length() / 8;

        for(int j = 0 ; j<i;j++){
            String substring = s1.substring(j * 8, (j + 1) * 8);
            System.out.println(substring);
        }

    }
}
