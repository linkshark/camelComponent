package com.linkjb.camelcomponent.letcode;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * @ClassName demo2
 * @Description TODO
 * @Author shark
 * @Data 2022/7/11 22:14
 **/
public class demo2 {


    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        while (s.hasNext()) {
            Integer maxCount = 0;
            String s1 = s.nextLine();
            System.out.println(lengthOfLongestSubstring(" "));

        }
    }


    public static int lengthOfLongestSubstring(String s) {
        Integer maxLength = 0;
        char[] chars = s.toCharArray();
        if (s.length() == 0) {
            return maxLength;
        }
        for (int i = 0; i < chars.length; i++) {
            ArrayList<Character> characters = new ArrayList<>();
            for (int j = i; j < chars.length; j++) {
                if (characters.contains(s.charAt(j))) {
                    break;
                }
                characters.add(s.charAt(j));
                maxLength = characters.size() > maxLength ? characters.size() : maxLength;
            }
        }
        return maxLength;

    }
}
