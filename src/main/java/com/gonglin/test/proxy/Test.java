package com.gonglin.test.proxy;


/**
 * 动态代理
 * 
 * @author GONGLIN
 * 
 */
public class Test {

	public static void main(String[] args) {
		ProxyUser proxyuser=new ProxyUser();
		UserInterface user= (UserInterface) proxyuser.bind(new User());
		System.out.println(user.getNames());
	}

}
