package com.zuql.reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

class Container{
	private Container() {}
	private Container(int a) {
		System.out.println("a="+a);
	}
}

public class ReflectDemo02 {
	public static void main(String[] args)
	throws Exception{
		//doCreateObj01();
		doCreateObj02();
	}

	/**
	 * 反射 修改访问权限 调用有参构造 创建对象
	 * @throws ClassNotFoundException
	 * @throws NoSuchMethodException
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	private static void doCreateObj02() throws ClassNotFoundException, NoSuchMethodException, InstantiationException,
			IllegalAccessException, InvocationTargetException {
		//1.获取类的字节码对象
		Class<?> c=
		Class.forName("com.zuql.reflect.Container");
		//2.获取类对象中的无参构造方法对象
		Constructor<?> con=
		c.getDeclaredConstructor(int.class);//int.class!=Integer.class
		//3.设置构造方法可访问
		con.setAccessible(true);
		//4.构建类的实例对象
		Object obj=con.newInstance(10);
		System.out.println(obj);
	}

	/**
	 * 反射 修改访问权限 调用无参构造 创建对象
	 * @throws ClassNotFoundException
	 * @throws NoSuchMethodException
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	private static void doCreateObj01() throws ClassNotFoundException, NoSuchMethodException, InstantiationException,
	IllegalAccessException, InvocationTargetException {
		//1.获取类的字节码对象
		Class<?> c=
				Class.forName("com.zuql.reflect.Container");
		//2.获取类对象中的无参构造方法对象
		Constructor<?> con=
				c.getDeclaredConstructor();
		//3.设置构造方法可访问
		con.setAccessible(true);
		//4.构建类的实例对象
		Object obj=con.newInstance();
		System.out.println(obj);
	}
}





