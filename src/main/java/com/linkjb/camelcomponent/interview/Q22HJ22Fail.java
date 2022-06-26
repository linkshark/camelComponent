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
public class Q22HJ22Fail {
    public static void main(String[] args) {
        //65A 90Z 97 a 122 z
        Scanner sc = new Scanner(System.in);
        List<Integer> list = new ArrayList<>();
        while (sc.hasNext()) {
            int num = sc.nextInt();
            //如果输出0则退出遍历
            if (num == 0) {
                break;
            } else {
                countCola(num, 0);
            }

        }
    }

    /*递归函数*/

    private static void countCola(int num, int count) {
        int chuShu = num / 3;
        int yuShu = num % 3;
        //这么什么好说的，应该都看得懂
        count = count + chuShu;
        num = chuShu + yuShu;
        //如果除数加余数是1，则借不了汽水，因为喝了你也还不了
        if (num == 1) {
            System.out.println(count);
            return;
        }
        //如果余数加除数是2或者3，那么你可以直接换汽水，或者借一瓶汽水，喝完再还
        if (num == 2 || num == 3) {
            count++;
            System.out.println(count);
            //如果以上情况都不是，接着递归
        } else {
            countCola(num, count);
        }

    }
}
