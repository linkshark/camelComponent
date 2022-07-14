package com.linkjb.camelcomponent.interview;


import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

/**
 * @ClassName Q11HJ11
 * @Description TODO
 * @Author shark
 * @Data 2022/6/20 17:40
 **/
//描述
//        现在有一种密码变换算法。
//        九键手机键盘上的数字与字母的对应： 1--1， abc--2, def--3, ghi--4, jkl--5, mno--6, pqrs--7, tuv--8 wxyz--9, 0--0，把密码中出现的小写字母都变成九键键盘对应的数字，如：a 变成 2，x 变成 9.
//        而密码中出现的大写字母则变成小写之后往后移一位，如：X ，先变成小写，再往后移一位，变成了 y ，例外：Z 往后移是 a 。
//        数字和其它的符号都不做变换。
//        数据范围： 输入的字符串长度满足 1 \le n \le 100 \1≤n≤100
//        输入描述：
//        输入一组密码，长度不超过100个字符。
//
//        输出描述：
//        输出密码变换后的字符串
//
//        示例1
//        输入：
//        YUANzhi1987
//        复制
//        输出：
//        zvbo9441987

//    Ihave1nose2hands10fingers
public class Q34HJ34 {
    public static void main(String[] args) {
        //65A 90Z 97 a 122 z
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            String next = sc.next();
            char[] chars = next.toCharArray();
            List<String> s = new ArrayList<>();
            List<Integer> s2 = new ArrayList<>();
            for (int i = 0; i < chars.length; i++) {
                if (Character.isLetter(chars[i])) {
                    //字符串
                    s.add(Character.valueOf(chars[i]).toString());
                } else {
                    s2.add(Integer.valueOf(Character.valueOf(chars[i]).toString()));
                }

            }
            s.sort(Comparator.comparing(String::toString));
            s2.sort(Comparator.comparing(Integer::intValue));
            for (Integer i : s2) {
                System.out.print(i);
            }
            for (String k : s) {
                System.out.print(k);
            }

        }


    }


}


