package com.linkjb.camelcomponent.demo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @ClassName kk
 * @Description TODO
 * @Author shark
 * @Data 2022/7/25 10:16
 **/
public class kk {



    public static void main(String[] args){
        int[] s = {1,2,3,4,66,2,3,1,2,3,4,5,6,1,2,222,4,23,3};

        for(int i=0;i <s.length-1;i++){
            for(int j=0;j<s.length-1;j++){
                if(s[j+1]<s[j]){
                    int temp = s[j+1];
                    s[j+1] = s[j];
                    s[j] = temp;
                }
            }
        }
        System.out.println(Arrays.stream(s).toArray());
    }

}
