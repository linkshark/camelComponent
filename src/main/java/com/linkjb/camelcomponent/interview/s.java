package com.linkjb.camelcomponent.interview;

/**
 * @ClassName s
 * @Description TODO
 * @Author shark
 * @Data 2022/6/22 15:25
 **/
public class s {
    public static void main(String[] args) {
        Integer[] s = new Integer[]{1, 3, 4, 2, 3, 1, 5, 7, 1, 4, 65, 7, 8, 4};
        for (int i = 0; i < s.length - 1; i++) {
            for (int j = 0; j < s.length - 1 - i; j++) {
                if (s[j] > s[j + 1]) {
                    Integer temp = s[j];
                    s[j] = s[j + 1];
                    s[j + 1] = temp;
                }
            }
        }
        for (int i = 0; i < s.length; i++) {
            System.out.print(s[i] + ",");
        }
    }
}
