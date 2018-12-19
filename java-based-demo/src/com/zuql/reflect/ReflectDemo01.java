package com.zuql.reflect;
class Point{
	public Point(){}
	public Point(int x){}
}//Point.class  方法区

public class ReflectDemo01 {
	
	public static void main(String[] args) 
	throws Exception{
		doClass();
		//doObject01("com.zuql.reflect.Point");
	}
	public static void doObject01(String className)throws Exception{
		//获取类对象(字节码对象)
		//此对象内部记录什么内容？(类的结构信息，例如属性，方法)
		//此类对象在jvm内存的什么区域？(堆)
		//此类对象在堆内存中有几份？，何时创建的？类加载时创建
		Class<?> c=Class.forName(className);
		//基于Class对象构建类的实例对象(默认会调用类的无参构造函数)
		Object instance=c.newInstance();
		System.out.println("instance="+instance);
	}
	private static void doClass() throws ClassNotFoundException {
		//获取类的字节码对象(此对象中记录了类中成员信息)
		//每个类对应的字节码对象只有一份
		Class<?> c1=Point.class;
		Class<?> c2=
		Class.forName("com.zuql.reflect.Point");
		Class<?> c3=new Point().getClass();
		
		System.out.println(c1==c2);//true
		System.out.println(c2==c3);//true
	}
}
