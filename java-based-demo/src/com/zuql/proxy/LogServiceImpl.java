package com.zuql.proxy;

public class LogServiceImpl implements LogService {
	@Override
	public int deleteObject(int id) {
		try{
			Thread.sleep(3000);
		}catch(Exception e){e.printStackTrace();}
		System.out.println("delete ok");
		return 1;
	}

}
