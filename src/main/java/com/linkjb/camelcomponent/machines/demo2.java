package com.linkjb.camelcomponent.machines;

import java.util.Scanner;

/**
 * @ClassName demo2
 * @Description TODO
 * @Author shark
 * @Data 2022/7/3 10:18
 **/
public class demo2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            String next = sc.next();
            int totalMoney = sc.nextInt();
            String[] split = next.split(",");
            Integer expectMoney = -1;
            for (int i = 0; i < split.length - 2; i++) {
                for (int j = i + 1; j < split.length - 1; j++) {
                    for (int k = j + 1; k < split.length; k++) {
                        Integer num = Integer.parseInt(split[i]) + Integer.parseInt(split[j]) + Integer.parseInt(split[k]);
                        if (num <= totalMoney && num > expectMoney) {
                            expectMoney = num;
                        }
                    }
                }
            }
            System.out.println(expectMoney);
        }
    }
}
