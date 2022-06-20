package com.linkjb.camelcomponent.interview;

import java.util.HashSet;
import java.util.Scanner;

public class Q3HJ3 {
    public static void main(String[] args){
        Scanner s = new Scanner(System.in);
        Integer count = Integer.parseInt(s.nextLine());
        HashSet<Integer> hashSet = new HashSet<>();
        for(int i =0 ;i< count ; i++){
            Integer integer = Integer.parseInt(s.nextLine());
            hashSet.add(integer);
        }
        hashSet.stream().sorted().forEach(k ->{
            System.out.println(k);
        });


    }
}
