package com.linkjb.camelcomponent.letcode;

import java.util.HashSet;
import java.util.Set;

/**
 * @ClassName code1
 * @Description TODO
 * @Author shark
 * @Data 2022/7/20 21:33
 **/
public class code3 {
    public  static int lengthOfLongestSubstring(String s) {
        Integer maxCount = 0;
        char[] chars = s.toCharArray();
        if(s.length()==0){
            return maxCount;
        }
        for (int i = 0; i < chars.length; i++) {
            for (int j = i + 1; j < chars.length+1; j++) {
                String substring = s.substring(i, j);
                Set<String> set = new HashSet<>();
                for (int k = 0; k < substring.length(); k++) {
                    if(!set.add(substring.substring(k, k + 1))){
                        break;
                    }
                }
                if (set.size() == substring.length()) {
                    if (set.size() > maxCount) {
                        maxCount = set.size();
                    }
                }
            }
        }
        return maxCount;

    }

    public static void main(String[] args){
        int[] nums = {2,7,11,15};
        int target = 9;
        System.out.println(lengthOfLongestSubstring("pwwkew"));
    }
}
