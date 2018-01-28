package com.wangdh.springboot.mybatis.test;

public class App {
    public static void main(String[] args){
        char c = '王';
        System.out.println(c);
       // short s1 = 1;
       // s1 = s1 + 1; // 这是错误的，1是int，int转short是向下转，需要强转
       // s1 += 1; // 只是OK的，隐含了强转

        String type = "aaa";
        switch (type){
            case "aaa":
                System.out.println(123);
                break;
            default:
                System.out.println(321);
                break;
        }
        System.out.println(2 << 3);
        System.out.println(16 >> 3);

        String s1 = "Programming";
        String s2 = new String("Programming");
        String s3 = "Program" + "ming";
        System.out.println(s1 == s2);
        System.out.println(s1 == s3);
        System.out.println(s1 == s1.intern());

    }
}