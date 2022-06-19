package com.linkjb.camelcomponent.interview;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Q5Hj5 {
    public static void main(String[] args){
        Scanner s = new Scanner(System.in);
        while(s.hasNext()){
            Map<String,Integer> map = new HashMap<>();
            for(int i =0; i<=10;i++){
                map.put(String.valueOf(i),i);
            }
            map.put("A",10);
            map.put("B",11);
            map.put("C",12);
            map.put("D",13);
            map.put("E",14);
            map.put("F",15);
            map.put("G",16);
            String replace = s.nextLine();
            if(replace.contains("0x")){
                replace = replace.replace("0x", "");

            }
            int length = replace.length();
            Integer count = 0;
            char[] chars = replace.toCharArray();
            char[] reChar = new char[chars.length];
            for(int i=chars.length-1;i>=0;i--){
                reChar[chars.length-1-i]=chars[i];
            }
            for(int i =length-1 ;i>=0;i--){
                Double pow = Math.pow(16, i);
                Integer integer = Integer.valueOf(pow.intValue());
                String substring = String.valueOf(reChar[i]);
                Integer mm = map.get(substring);
                integer = integer*mm;
                count +=integer;
            }
            System.out.println(count);
        }
    }
}
