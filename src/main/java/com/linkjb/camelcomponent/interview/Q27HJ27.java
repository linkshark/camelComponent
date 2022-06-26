package com.linkjb.camelcomponent.interview;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
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
@Slf4j
public class Q27HJ27 {
    public static void main(String[] args) {
        //65A 90Z 97 a 122 z
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            int count = sc.nextInt();
            List<String> keyWord = new ArrayList<>();
            for (int i = 0; i < count; i++) {
                String next = sc.next();
                keyWord.add(next);
            }
            String mainWord = sc.next();
            Integer order = sc.nextInt();
            List<String> trueList = new ArrayList<>();
            keyWord.forEach(s -> {
                Boolean flag = false;
                if (s.length() == mainWord.length()) {
                    //首先大小相等
                    if (!s.equals(mainWord)) {
                        //要不等于才行
                        List<String> mainList = new ArrayList<>();
                        for (Character kk : mainWord.toCharArray()) {
                            mainList.add(kk.toString());
                        }
                        List<String> keyWordList = new ArrayList<>();
                        for (Character kk : s.toCharArray()) {
                            keyWordList.add(kk.toString());
                        }
                        final Integer[] countlag = {0};
                        mainList.forEach(l -> {
                            if (keyWordList.contains(l)) {
                                countlag[0] = countlag[0] + 1;
                                keyWordList.remove(l);
                            }
                        });
                        if (countlag[0].equals(mainList.size())) {
                            flag = true;
                        }
                        if (flag) {
                            trueList.add(s);
                        }

                    }
                }
            });
            System.out.println(trueList.size());
            trueList.sort(String::compareTo);
            if (trueList.size() >= order - 1) {
                try {
                    System.out.println(trueList.get(order - 1));
                } catch (Exception e) {

                }
            }


        }


    }


}


