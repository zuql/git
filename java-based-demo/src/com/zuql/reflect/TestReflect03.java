package com.zuql.reflect;


import com.zuql.annotation.Controller;
import com.zuql.annotation.RequestMapping;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

@Controller
class HelloController{

	@RequestMapping(value = "sayHello")
	public void sayHello(){}
}

public class TestReflect03 {
	public static void main(String[] args)
			throws Exception{
		Class<?> c=
				Class.forName("cgb.reflect.HelloController");
		//判定类上是否有Controller注解
		boolean flag=c.isAnnotationPresent(Controller.class);
		System.out.println(flag);
		//假如类上有Controller注解，则构建此类实例
		if(!flag)return;
		Constructor<?> con=c.getDeclaredConstructor();
		con.setAccessible(true);
		Object target=con.newInstance();//后续将实力存map
		//获取此类中的sayHello方法对象
		Method method=
				c.getDeclaredMethod("sayHello");
		//获取方法对象上的@RequestMapping注解
		flag=
				method.isAnnotationPresent(RequestMapping.class);
		if(!flag)return;
		//获取RequestMapping注解中value属性值并输出
		RequestMapping rm=
				method.getDeclaredAnnotation(RequestMapping.class);
		System.out.println(rm.value()[0]);//获取value属性中的值


	}
}





