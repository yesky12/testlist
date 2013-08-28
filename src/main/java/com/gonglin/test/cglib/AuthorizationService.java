package com.gonglin.test.cglib;

import java.lang.reflect.Method;

/**
 * 
 * @author lin.gong
 *
 */
public interface AuthorizationService {
	void authorize(Method method);
}
