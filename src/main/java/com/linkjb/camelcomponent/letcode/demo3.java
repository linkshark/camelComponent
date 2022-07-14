package com.linkjb.camelcomponent.letcode;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @ClassName demo3
 * @Description TODO
 * @Author shark
 * @Data 2022/7/11 22:45
 **/
public class demo3 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            int row = sc.nextInt();
            List<List<Integer>> list = new ArrayList<>();
            for (int i = 0; i < row; i++) {
                List<Integer> list2 = new ArrayList<>();
                for (int j = 0; j <= i; j++) {
                    if (j == 0 || j == 1) {
                        list2.add(1);
                    } else {
                        list2.add(list.get(i - 1).get(j - 1) + list.get(i - 1).get(j));
                    }
                }
            }
        }
    }
}
