package com.linkjb.camelcomponent.interview;

import java.util.Scanner;

public class Q4HJ4 {
    public static void main(String[] args){
        Scanner s = new Scanner(System.in);
        String s1 = s.nextLine();
        int length = s1.length();
        if(length %8 !=0){
            int i = 8-(length % 8);
            for(int temp =0 ; temp<i ;temp++ ){
                s1 = s1 + 0;
            }
        }
        int i = s1.length() / 8;

        for(int j = 0 ; j<i;j++){
            String substring = s1.substring(j * 8, (j + 1) * 8);
            System.out.println(substring);
        }

    }
}
