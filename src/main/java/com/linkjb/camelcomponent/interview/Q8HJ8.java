package com.linkjb.camelcomponent.interview;

import java.util.Comparator;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

/**
 * @ClassName Q7HJ7
 * @Description 取近似值
 * @Author shark
 * @Data 2022/6/20 11:12
 **/
//描述
//        数据表记录包含表索引index和数值value（int范围的正整数），请对表索引相同的记录进行合并，即将相同索引的数值进行求和运算，输出按照index值升序进行输出。
//
//
//        提示:
//        0 <= index <= 11111111
//        1 <= value <= 100000
//
//        输入描述：
//        先输入键值对的个数n（1 <= n <= 500）
//        接下来n行每行输入成对的index和value值，以空格隔开
//
//        输出描述：
//        输出合并后的键值对（多行）
//
//        示例1
//        输入：
//        4
//        0 1
//        0 2
//        1 2
//        3 4
//        复制
//        输出：
//        0 3
//        1 2
//        3 4
//        复制
//        示例2
//        输入：
//        3
//        0 1
//        0 2
//        8 9
//        复制
//        输出：
//        0 3
//        8 9
public class Q8HJ8 {

    public static void main(String[] args) {
        gogo();
    }

    private static void gogo() {
        Scanner sc = new Scanner(System.in);
        Integer input = sc.nextInt();
        Map<Integer, Integer> map = new TreeMap<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1.compareTo(o2);
            }
        });
        for (int i = 0; i < input; i++) {
            int i1 = sc.nextInt();
            int value = sc.nextInt();
            if (map.get(i1) == null) {
                map.put(i1, value);
            } else {
                map.replace(i1, value + map.get(i1));
            }
        }
        for (Map.Entry<Integer, Integer> ss : map.entrySet()) {
            System.out.println(ss.getKey() + " " + ss.getValue());
        }
    }
}
