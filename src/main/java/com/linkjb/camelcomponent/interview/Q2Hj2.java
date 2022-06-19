package com.linkjb.camelcomponent.interview;



import java.util.Scanner;

public class Q2Hj2 {
    public static void main(String[] args){
        Scanner s = new Scanner(System.in);
        String allWord = s.nextLine().toLowerCase();
        String specificWord = s.nextLine().toLowerCase();
        char[] chars = allWord.toCharArray();
        int count = 0 ;
        for(int i =0 ;i < chars.length ; i++){
            char aChar = chars[i];
            if(specificWord.toCharArray()[0] == aChar){
                count++;
            }
        }
        System.out.println(count);


    }
}
