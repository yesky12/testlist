package com.gonglin.test.Enum;

/**
 * User: lin.gong
 * Date: 12-10-21
 * Time: 下午10:10
 */
public abstract class AbstractClass {
    public abstract String getVal(String a);
}

class SubClass extends AbstractClass{

    @Override
    public String getVal(String a) {
        return a;
    }
}
