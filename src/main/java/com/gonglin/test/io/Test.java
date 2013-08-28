package com.gonglin.test.io;

import java.nio.file.Paths;

/**
 * User: gonglin
 * Date: 8/7/13
 * Time: 9:46 AM
 */
public class Test {
    public static void main(String[] args) {

        System.out.println(Paths.get(".").toAbsolutePath());
    }
}
