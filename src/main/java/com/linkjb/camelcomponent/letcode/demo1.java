package com.linkjb.camelcomponent.letcode;

import java.util.Scanner;

/**
 * @ClassName demo1
 * @Description TODO
 * @Author shark
 * @Data 2022/7/5 09:40
 **/
public class demo1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            int i = sc.nextInt();
            if (i == 1) System.out.println(1);
            if (i == 2) System.out.println(2);
            if (i > 2) {
                int[] s = new int[i];
                s[0] = 1;
                s[1] = 1;
                Integer temp = 0;
                for (int j = 2; j < i; j++) {
                    s[j] = s[j - 2] + s[j - 1];
                    System.out.print(s[j] + " ");
                }
            }
        }
    }
}
