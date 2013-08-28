package com.gonglin.test.classloader;

public class Animal{
    public int a = 2;
    public Animal(){
        System.out.println("我是animal,我被："+this.getClass().getClassLoader()+"   加载了11");
    }
}