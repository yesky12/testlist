package com.gonglin.test;

import java.io.UnsupportedEncodingException;

/**
 * User: lin.gong Date: 4/22/13 Time: 9:13 PM
 */
public class Test {

    public static void main(String[] args) {

    }

    public static void printEncodeLength(String s, String encodeName) throws UnsupportedEncodingException {
        System.out.println(s + ":" + s.getBytes(encodeName).length + ":" + encodeName);
    }
}