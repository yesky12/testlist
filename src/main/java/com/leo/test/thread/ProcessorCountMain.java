package com.leo.test.thread;

public class ProcessorCountMain {

    public static void main(String[] args) {
        int count = Runtime.getRuntime().availableProcessors();
        System.out.println("Processor Count:" + count);
    }
}