package com.wangdh.springboot.mybatis.test;

import java.util.ArrayList;

/**
 * @author wangdh
 * @Description
 * @date 2018-01-30
 */
public class App2 {
    public static void main(String[] args){
        int[] iArray = new int[8];
        iArray[0] = 1;
        System.out.println(iArray.length);

        ArrayList<Integer> list = new ArrayList<Integer>(8);
        list.add(12);

        System.out.println(reverse("abcde"));
    }

    public static String reverse(String str) {
        if ((null == str) || (str.length()  <= 1)) {
            return str;
        }
        return reverse(str.substring(1)) + str.charAt(0);
    }
}
