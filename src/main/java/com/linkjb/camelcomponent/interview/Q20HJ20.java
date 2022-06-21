package com.linkjb.camelcomponent.interview;

import lombok.extern.slf4j.Slf4j;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @ClassName Q11HJ11
 * @Description TODO
 * @Author shark
 * @Data 2022/6/20 17:40
 **/
//描述
//        密码要求:
//
//        1.长度超过8位
//
//        2.包括大小写字母.数字.其它符号,以上四种至少三种
//
//        3.不能有长度大于2的包含公共元素的子串重复 （注：其他符号不含空格或换行）
//
//        数据范围：输入的字符串长度满足 1 \le n \le 100 \1≤n≤100
//        输入描述：
//        一组字符串。
//
//        输出描述：
//        如果符合要求输出：OK，否则输出NG
@Slf4j
public class Q20HJ20 {
    public static void main(String[] args) {
        //65 90 97 122
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            String s = sc.next();
            if (s.length() <= 8) {
                System.out.println("NG");
                continue;
            }
            Integer count = 0;
            char[] chars = s.toCharArray();
            Boolean flag1 = false;
            Boolean flag2 = false;
            Boolean flag3 = false;
            Boolean flag4 = false;
            Pattern p = Pattern.compile("[*!@#$\\&]");
            Matcher m = p.matcher(s);
            boolean match = m.find();
            if (match) {
                flag4 = true;
            }
            for (int i = 0; i < chars.length; i++) {
                int aChar = chars[i];
                if (aChar <= 90 && aChar >= 65) {
                    flag1 = true;
                }
                if (aChar <= 122 && aChar >= 97) {
                    flag2 = true;
                }
                if (!flag3) {
                    Character c = new Character(chars[i]);
                    String s1 = c.toString();
                    try {
                        Integer.parseInt(s1);
                        flag3 = true;
                    } catch (Exception e) {
                        continue;
                    }
                }


            }
            if (flag1) {
                count++;
            }
            if (flag2) {
                count++;
            }
            if (flag3) {
                count++;
            }
            if (flag4) {
                count++;
            }
            Boolean flag5 = true;
            for (int i = 0; i < s.length() - 2; i++) {
                String substring = s.substring(i, i + 3);
                if (s.lastIndexOf(substring) != i) {
                    flag5 = false;
                    break;
                }
            }


            if (count >= 3 && flag5) {
                System.out.println("OK");
            } else {
                System.out.println("NG");
            }

        }


    }


}
