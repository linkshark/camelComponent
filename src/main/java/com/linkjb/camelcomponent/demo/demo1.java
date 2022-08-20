package com.linkjb.camelcomponent.demo;

import io.swagger.models.auth.In;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @ClassName demo1
 * @Description TODO
 * @Author shark
 * @Data 2022/7/14 20:16
 **/


//有一种压缩算法，对int数组进行压缩，对于连续重复的数字，使用[number,count]来表达,将数组压缩为一个二维数组。
//        例如[1,2,2,2,3,3,3,3],压缩为二维数组[[1,1],[2,3,],[3,4]]。
//        现在要求实现一个功能，输入两个压缩后的二维数组A,B，要求将这两个二维数组解压，得到两个int数组a,b，然后根据这两个int数组，生成一个新的int数组c。规则是
//        c[i]=a[i]*b[i]
//        如果a,b长度不一样，将更短的那个末尾通过追加0来补齐长度。
//        最后将c压缩成为二维数组并输出。
//        样例1：
//        输入[[2,3],[5,3],[1,4],[3,3]], 	[[1,2],[7,1],[3,8]]
//        输出[[2,2],[14,1],[15,3],[3,4],[9,1],[0,2]]
//        样例2：
//        输入[[3,3],[4,1]],	[[2,1],[4,2],[3,2]]
//        输出[[6,1],[12,3],[0,1]]
//        样例3：
//        输入[[2,1000000],[3,1000000]],	[[7,999999],[8,2],[6,999999]]
//        输出[[14,999999],[16,1],[24,1],[18,999999]]
public class demo1 {

    public static void main(String[] args){
        int[][] aa = new int[][]{{2, 3}, {5, 3}, {1, 4}, {3, 3}};
        int[][] bb = new int[][]{{1, 2}, {7, 1}, {3, 8}};
        List<Integer> a = new ArrayList<>();
        List<Integer> b = new ArrayList<>();
        for(int i=0;i<aa.length;i++){
            int i1 = aa[i][0];
            int i2 = aa[i][1];
            for(int k = 0 ;k<i2;k++){
                a.add(i1);
            }
        }
        for(int i=0;i<bb.length;i++){
            int i1 = bb[i][0];
            int i2 = bb[i][1];
            for(int k = 0 ;k<i2;k++){
                b.add(i1);
            }
        }
        Integer finalSIze = 0 ;
       if(a.size()>b.size()){
           int index = a.size() - b.size();
           for(int i = 0;i<index;i++){
               b.add(0);
           }
           finalSIze = a.size();
       }else if(a.size()<b.size()){
           int index = b.size() - a.size();
           for(int i = 0;i<index;i++){
               a.add(0);
           }
           finalSIze = b.size();
       }else{
           finalSIze = a.size();
       }

       List<Integer> c = new ArrayList<>(16);
       for(int i=0 ;i <finalSIze;i++){
           Integer num = a.get(i)*b.get(i);
           c.add(num);
       }
       Integer num = null;
       Integer count = 0;
       List<List<Integer>> list = new ArrayList<>();
       for(Integer s : c){
           if(num==s){
               count++;
           }else{
               if(num==null){
                   num = s;
                   count++;
                   continue;
               }
               List<Integer> tempList = new ArrayList<>();
               tempList.add(num);
               tempList.add(count);
               list.add(tempList);
               num = s;
               count = 1;
           }
       }
        List<Integer> tempList = new ArrayList<>();
        tempList.add(num);
        tempList.add(count);
        list.add(tempList);
       System.out.println("ss");





//       c.stream().collect(Collectors.groupingBy(Integer::intValue))
//        Map<Integer,Integer> map= new HashMap<>();
//        c.forEach(s ->{
//            if(map.get(s)!=null){
//                map.put(s,map.get(s)+1);
//            }else{
//                map.put(s,1);
//            }
//        });

//        for(Map.Entry<Integer,Integer> k :map.entrySet()){
//            System.out.println(k.getKey() +":" +k.getValue());
//        };






    }




}
