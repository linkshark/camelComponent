package com.linkjb.camelcomponent.interview;


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
public class Q33HJ33Fail {
    public static void main(String[] args) {
        double pow = Math.pow(2, 0);

        //65A 90Z 97 a 122 z
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            String next = sc.next();
            if (next.contains(".")) {
                //ip地址
                StringBuilder sb = new StringBuilder();
                String[] split = next.split("[.]");
                for (String s : split) {
                    int i = Integer.parseInt(s);
                    String s1 = Integer.toBinaryString(i);
                    if (s1.length() < 8) {
                        Integer subZeroCount = 8 - s1.length();
                        for (int k = 0; k < subZeroCount; k++) {
                            s1 = "0" + s1;
                        }
                    }
                    sb.append(s1);
                }
                String s = sb.toString();
                Long count = Long.valueOf(0);
                for (int i = 0; i < s.length(); i++) {
                    Double paw = Math.pow(2, s.length() - i - 1);
                    Double v = Integer.parseInt(s.substring(i, i + 1)) * paw;
                    count += v.intValue();
                }
                System.out.println(count);
            } else {
                Long l = Long.parseLong(next);
                String s = Long.toBinaryString(l);
                if (s.length() % 8 != 0) {
                    int i = s.length() % 8;
                    int fixcount = 8 - i;
                    for (int k = 0; k < fixcount; k++) {
                        s = "0" + s;
                    }
                }
                Integer count = s.length() / 8;
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < count; i++) {
                    String substring = s.substring(i * 8, 8 * (i + 1));
                    Integer cc = 0;
                    for (int h = 0; h < substring.length(); h++) {
                        double paw = Math.pow(2, substring.length() - h - 1);
                        Double v = Integer.parseInt(substring.substring(h, h + 1)) * paw;
                        cc += v.intValue();
                    }

                    sb.append(cc + ".");
                }
                String s1 = sb.toString();
                if (s1.contains(".")) {
                    s1 = s1.substring(0, s1.length() - 1);
                }

                System.out.println(s1);

            }
        }


    }


}


