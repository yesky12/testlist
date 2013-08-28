package com.gonglin.test.jrebel;

/**
 * User: gonglin Date: 1/20/13 Time: 12:02 PM
 */
public class jrebel {
    public static void main(String[] args) {
        TestThread testThread = new TestThread();
        testThread.run();
    }

    static class Test {
        public static void a() {
            System.out.println("a");
        }


    }
}


class TestThread implements Runnable {

    @Override
    public void run() {
        try {
            while (true) {
                jrebel.Test.a();
                Thread.sleep(1 * 1000);
                test();
                System.out.println("ddddd");
                System.out.println("ccccc");
                System.out.println("dddd");
                System.out.println("eeeeeeeeeee");
            }
        } catch (InterruptedException e) {
            e.printStackTrace(); // To change body of catch statement use File | Settings | File Templates.
        }
    }

    public void test(){
        System.out.println("aaaaa");
    }
}
