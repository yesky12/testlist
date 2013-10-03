package com.leo.test.classloader;

public class Cat {
    public Cat(){
        System.out.println("我是cat，被："+this.getClass().getClassLoader()+"   加载了");
        new Dog();
    }
}
	