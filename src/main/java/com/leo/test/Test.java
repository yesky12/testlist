package com.leo.test;

import java.io.UnsupportedEncodingException;

/**
 * User: Leo Date: 4/22/13 Time: 9:13 PM
 */
public class Test {
    public static int a = 1;

    public static void main(String[] args) {
        for (int i = 0; i < 1; i++) {
            new Thread(new A()).start();
        }
        new Thread(new B()).start();
        int a = 1;
        aa(a);

    }

    public synchronized static void setA(int a) {
        Test.a = a;
    }

    public static void aa(Object o) {

    }
}

class A implements Runnable {

    @Override
    public void run() {
        System.out.println(Test.a);
    }
}

class B implements Runnable {

    @Override
    public void run() {
        Test.setA(5);
        System.out.println("set A start");
//        try {
//            Thread.sleep(3 * 1000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        System.out.println("set A end");
    }
}