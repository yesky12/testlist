package com.leo.test;

/**
 * User: Leo Date: 4/22/13 Time: 9:13 PM
 */
public class Test {

    public static void main(String[] args) {
        args = new String[5];
        Class<? extends String[]> aClass = args.getClass();
        System.out.println(aClass.getComponentType());
    }
}
