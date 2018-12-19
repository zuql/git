package com.zuql.reflect;

import com.zuql.annotation.Controller;
import com.zuql.annotation.ResponseBody;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

@Controller
class WelcomeController{
	//当此方法通过反射调用时，假如方法上有@ResponseBody注解修饰
	//则将方法的返回值转换为字符串输出
	@ResponseBody
	public Map<String,Object> doWelcome(){
		Map<String,Object> map=new HashMap<>();
		map.put("id",100);
		map.put("nickname","孙悟空");
		return map;
	}
}

public class TestReflect04 {
    public static void main(String[] args)
    throws Exception{
		//1.通过反射构建对象
    	//1)获取类对象(字节码对象)
    	Class<?> c=Class.forName("cgb.reflect.WelcomeController");
    	//1.1)检测类上是否有@Controller注解修饰
    	boolean flag=c.isAnnotationPresent(Controller.class);
    	if(!flag)return;
    	//2)获取类的构造方法对象
    	Constructor<?> con=
    	c.getDeclaredConstructor();
    	//3)设置构造方法可访问
    	con.setAccessible(true);
    	//4)通过构造方法对象构建类的实例
    	Object target=con.newInstance();
    	//2.通过反射调用方法
    	//2.1) 获取要调用的方法对象
    	Method method=c.getDeclaredMethod("doWelcome");
    	//2.2) 执行目标对象的方法
    	Object result=method.invoke(target);//执行target对象的method
    	//3.将方法返回转换为字符串，然后输出。
    	//3.1判定方法上是否有ResponseBody注解
    	flag=method.isAnnotationPresent(ResponseBody.class);
    	//3.2假如有则将返回值转换字符串。
    	if(flag){
			String resultStr=result.toString();
			System.out.println(resultStr);
    	}
    	
	}
}


