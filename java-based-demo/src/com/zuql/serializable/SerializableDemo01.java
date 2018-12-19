package com.zuql.serializable;

import java.io.*;

class Order implements Serializable{
	private static final long serialVersionUID = 1731936408314713160L;
	private int id;
	private Integer status=0;
	public void setId(int id) {
		this.id = id;
	}
	public int getId() {
		return id;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Integer getStatus() {
		return status;
	}
}

/**
 * serialVersionUID 影响反序列化
 */
public class SerializableDemo01 {
	public static void main(String[] args)throws Exception {
		Order o1=new Order();
		o1.setId(100);
		//将如上对象序列化到磁盘
		/*ObjectOutputStream out=
		new ObjectOutputStream(
		new FileOutputStream("cache.txt"));
		out.writeObject(o1);
		out.close();
		System.out.println("序列化ok");*/
		//将cache.txt文件中的对象反序列化
		ObjectInputStream in=
		new ObjectInputStream(new FileInputStream("cache.txt"));
		Order o2=(Order)in.readObject();
		System.out.println("反序列化ok");
		System.out.println("order.id="+o2.getId());
	    in.close();
	}
}









