package com.zuql.ioc;

class ConnectionPool{
	public Object getConnection(){
		return null;
	}
}

public class IOCDemo01 {
	static ConnectionPool pool=new ConnectionPool();
	public static void doService01(){
		//ConnectionPool pool=new ConnectionPool();
		pool.getConnection();
		//....
	}
	public static void doService02(){
		//ConnectionPool pool=new ConnectionPool();
		pool.getConnection();
	}

	public static void main(String[] args) {
		
	}
}
