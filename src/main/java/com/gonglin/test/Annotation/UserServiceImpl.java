package com.gonglin.test.Annotation;

/**
 * User: lin.gong
 * Date: 12-8-16
 * Time: 下午10:21
 */
public class UserServiceImpl implements UserService {
    @Override
    public String getUserName() {
        return "gonglin";
    }
}
