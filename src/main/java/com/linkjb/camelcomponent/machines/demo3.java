package com.linkjb.camelcomponent.machines;

import java.util.Scanner;

/**
 * @ClassName demo3
 * @Description TODO
 * @Author shark
 * @Data 2022/7/3 10:59
 **/
public class demo3 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            String a = sc.next();
            char[] chara = a.toCharArray();
            String b = sc.next();
            char[] charb = b.toCharArray();
            Integer max = 0;
            int count = sc.nextInt();
            for (int i = 0; i < chara.length; i++) {
                for (int j = i + 1; j < chara.length + 1; j++) {
                    String substringa = a.substring(i, j);
                    String substringb = b.substring(i, j);
                    Boolean flag = true;
                    for (int k = 0; k < substringa.length(); k++) {
                        int charactera = (int) new Character(substringa.substring(k, k + 1).toCharArray()[0]);
                        int characterb = (int) new Character(substringb.substring(k, k + 1).toCharArray()[0]);
                        if (Math.abs(charactera - characterb) > count) {
                            flag = false;
                        }
                    }
                    if (flag) {
                        if (max < substringa.length()) {
                            max = substringa.length();
                        }
                    }
                }

            }
            System.out.println(max / 2);
        }
    }
}


