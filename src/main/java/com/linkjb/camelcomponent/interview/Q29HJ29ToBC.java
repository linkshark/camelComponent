package com.linkjb.camelcomponent.interview;


import java.util.Scanner;


public class Q29HJ29ToBC {
    public static void main(String[] args) {
        //65A 90Z 97 a 122 z
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            String code = sc.next();
            String decode = sc.next();
            char[] chars = code.toCharArray();
            for (int i = 0; i < chars.length; i++) {
                if (Character.isLetter(chars[i])) {
                    if (chars[i] > 97 && chars[i] < 122) {
                        //小写字母
                        chars[i] = (char) (chars[i] - 31);
                        continue;
                    }
                    if (chars[i] == 'a') {
                        //小写字母
                        chars[i] = 'Z';
                        continue;
                    }
                    if (chars[i] >= 65 && chars[i] < 90) {
                        //大写字母
                        chars[i] = (char) (chars[i] + 33);
                        continue;
                    }
                    if (chars[i] == 90) {
                        //小写字母
                        chars[i] = 'z';
                        continue;
                    }
                }
                if (Character.isDigit(chars[i])) {
                    chars[i] = (char) ((chars[i] + 1) % 10);
                }
            }
            char[] decodeChars = decode.toCharArray();
            for (int i = 0; i < decodeChars.length; i++) {
                if (Character.isLetter(decodeChars[i])) {
                    if (decodeChars[i] >= 97 && decodeChars[i] < 122) {
                        //小写字母
                        chars[i] = (char) (chars[i] - 31);
                        continue;
                    }
                    if (chars[i] == 122) {
                        //小写字母
                        chars[i] = 'a';
                        continue;
                    }

                    if (chars[i] >= 65 && chars[i] < 90) {
                        //大写字母
                        chars[i] = (char) (chars[i] + 33);
                        continue;
                    }
                    if (chars[i] == 65) {
                        //大写字母
                        chars[i] = 'z';
                        continue;
                    }
                }
                if (Character.isDigit(decodeChars[i])) {
                    if (decodeChars[i] == '0') {
                        decodeChars[i] = '9';
                    } else {
                        decodeChars[i] = (char) (Math.abs(decodeChars[i] - 1) % 10);
                    }
                }
            }
            System.out.println(String.valueOf(chars));

        }


    }


}


