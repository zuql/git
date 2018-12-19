package com.zuql.proxy;

public class HelloServiceImpl implements HelloService {

	@Override
	public void sayHello(String msg) {
		//long t1=System.currentTimeMillis();
		try{
		Thread.sleep(2000);
		}catch(Exception e){e.printStackTrace();}
		System.out.println(msg);
		//long t2=System.currentTimeMillis();
		//System.out.println(t2-t1);
	}
	
}
