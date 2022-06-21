package com.linkjb.camelcomponent.interview;
//描述
//        写出一个程序，接受一个十六进制的数，输出该数值的十进制表示。
//
//        数据范围：保证结果在 1 \le n \le 2^{31}-1 \1≤n≤2
//        31
//        −1
//        输入描述：
//        输入一个十六进制的数值字符串。
//
//        输出描述：
//        输出该数值的十进制字符串。不同组的测试用例用\n隔开。
//
//        示例1
//        输入：
//        0xAA
//        复制
//        输出：
//        170

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Q5HJ5 {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        while (s.hasNext()) {
            Map<String, Integer> map = new HashMap<>();
            for (int i = 0; i <= 10; i++) {
                map.put(String.valueOf(i), i);
            }
            map.put("A", 10);
            map.put("B", 11);
            map.put("C", 12);
            map.put("D",13);
            map.put("E",14);
            map.put("F",15);
            map.put("G",16);
            String replace = s.nextLine();
            if(replace.contains("0x")){
                replace = replace.replace("0x", "");

            }
            int length = replace.length();
            Integer count = 0;
            char[] chars = replace.toCharArray();
            char[] reChar = new char[chars.length];
            for(int i=chars.length-1;i>=0;i--){
                reChar[chars.length-1-i]=chars[i];
            }
            for(int i =length-1 ;i>=0;i--){
                Double pow = Math.pow(16, i);
                Integer integer = Integer.valueOf(pow.intValue());
                String substring = String.valueOf(reChar[i]);
                Integer mm = map.get(substring);
                integer = integer*mm;
                count +=integer;
            }
            System.out.println(count);
        }
    }
}
