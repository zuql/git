package com.zuql.jt.web.thread;

import com.zuql.jt.manage.pojo.User;

public class UserThreadLocal {
	
	//1.定义本地线程变量
	private static ThreadLocal<User> userThread = new ThreadLocal<>();
	
	
	public static void set(User user){
		
		userThread.set(user);
	}
	
	
	public static User get(){
		
		return userThread.get();
	}
	
	//防止内存泄漏 gc没有权限回收threadLocal
	public static void remove(){
		
		userThread.remove();
	}
}
