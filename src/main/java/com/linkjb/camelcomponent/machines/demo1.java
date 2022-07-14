package com.linkjb.camelcomponent.machines;

import java.util.Scanner;

/**
 * @ClassName demo1
 * @Description TODO
 * @Author shark
 * @Data 2022/7/3 09:33
 **/
public class demo1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            Integer maxCount = 0;
            String s = sc.nextLine();
            int numU = sc.nextInt();
            String[] split = s.split(" ");
            String tempStr = s.replaceAll(" ", "");
            for (int i = 0; i < split.length - 1; i++) {
                for (int j = i + 1; j < split.length + 1; j++) {

                }
            }
            if (maxCount < split.length) {
                System.out.println(1);
            } else {
                System.out.println(0);
            }

        }
    }
}
