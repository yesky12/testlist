package com.gonglin.test.Enum;

import java.lang.reflect.Modifier;

/**
 * User: lin.gong
 * Date: 12-10-21
 * Time: 下午8:18
 */
public class TestMain {

    public static void main(String[] args) {
        System.out.println(Modifier.toString(Color.class.getModifiers()));
        System.out.println(Color.class.getName());
    }
}
