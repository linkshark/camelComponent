package com.linkjb.camelcomponent.demo;

public class Demo1 {
    //private static final String s;
    int count = 0;
    public void add() {
        System.out.println(count++);
        add();
    }
    public static void gg(StringBuilder s){
        s.append("nihao");
        System.out.println(s);
    }

    public static void main(String[] args) {
        System.getProperties().list(System.out);
    }


}
