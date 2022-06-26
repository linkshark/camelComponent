package com.linkjb.camelcomponent.interview;

import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Scanner;

/**
 * @ClassName Q11HJ11
 * @Description TODO
 * @Author shark
 * @Data 2022/6/20 17:40
 **/
//描述
//        实现删除字符串中出现次数最少的字符，若出现次数最少的字符有多个，则把出现次数最少的字符都删除。输出删除这些单词后的字符串，字符串中其它字符保持原来的顺序。
//
//        数据范围：输入的字符串长度满足 1 \le n \le 20 \1≤n≤20  ，保证输入的字符串中仅出现小写字母
//        输入描述：
//        字符串只包含小写英文字母, 不考虑非法输入，输入的字符串长度小于等于20个字节。
//
//        输出描述：
//        删除字符串中出现次数最少的字符后的字符串。
//
//        示例1
//        输入：
//        aabcddd
//        复制
//        输出：
//        aaddd
@Slf4j
public class Q24HJ24fail {
    public static void main(String[] args) {
        //65A 90Z 97 a 122 z
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            Map<Character, Integer> map = new HashMap<>();
            String next = sc.next();
            char[] chars = next.toCharArray();
            for (int i = 0; i < chars.length; i++) {
                if (map.get(chars[i]) == null) {
                    map.put(chars[i], 1);
                } else {
                    map.put(chars[i], map.get(chars[i]) + 1);
                }
            }
            Optional<Integer> min = map.values().stream().sorted().min(Integer::compareTo);
            for (Map.Entry<Character, Integer> entry : map.entrySet()) {
                if (entry.getValue().equals(min.get())) {
                    String s = entry.getKey().toString();
                    next = next.replaceAll(s, "");
                }
            }
            System.out.print(next);

        }


    }


}
