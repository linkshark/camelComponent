package com.linkjb.camelcomponent.interview;

import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;
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
@Slf4j
public class Q21HJ21 {
    public static void main(String[] args) {
        //65A 90Z 97 a 122 z
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            String next = sc.next();
            char[] chars = next.toCharArray();
            StringBuilder s = new StringBuilder();
            Map<Character, Integer> map = new HashMap<>();
            map.put('a', 2);
            map.put('b', 2);
            map.put('c', 2);
            map.put('d', 3);
            map.put('e', 3);
            map.put('f', 3);
            map.put('g', 4);
            map.put('h', 4);
            map.put('i', 4);
            map.put('j', 5);
            map.put('k', 5);
            map.put('l', 5);
            map.put('m', 6);
            map.put('n', 6);
            map.put('o', 6);
            map.put('p', 7);
            map.put('q', 7);
            map.put('r', 7);
            map.put('s', 7);
            map.put('t', 8);
            map.put('u', 8);
            map.put('v', 8);
            map.put('w', 9);
            map.put('x', 9);
            map.put('y', 9);
            map.put('z', 9);
            for (int i = 0; i < chars.length; i++) {
                if (((int) chars[i] >= 97 && (int) chars[i] <= 122)) {
                    s.append(map.get(chars[i]));
                    continue;
                }
                if (((int) chars[i] >= 65 && (int) chars[i] <= 90)) {
                    //大写
                    String s1 = new Character(chars[i]).toString().toLowerCase();
                    char c = s1.toCharArray()[0];
                    int c1 = 0;
                    if (s1.equals("z")) {
                        c1 = 97;
                        char sb = (char) c1;
                        s.append(sb);
                    } else {
                        c1 = c + 1;
                        char sb = (char) c1;
                        s.append(sb);
                    }
                    continue;
                } else {
                    s.append(chars[i]);
                }

            }
            System.out.println(s);

        }


    }


}
