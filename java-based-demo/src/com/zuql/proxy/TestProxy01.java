package com.zuql.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class TestProxy01 {
	static class TimeAspect{
		public long  start(){
			long time=System.currentTimeMillis();
			return time;
		}
		public long end(){
			long time=System.currentTimeMillis();
			return time;
		}
	}
	//代理对象的处理器(负责代理业务的处理)
	static class ServiceHandler implements InvocationHandler{
		private Object target;
		public ServiceHandler(Object target) {
			this.target=target;
		}
		/**此方法中要执行代理对象的业务：
		 * 当执行代理对象的业务方法时，
		 * 此方法会自动执行。*/
		@Override
		public Object invoke(Object proxy, //代理对象
				Method method, //接口方法
				Object[] args) throws Throwable {
			TimeAspect tAspect=new TimeAspect();
			long t1=tAspect.start();
			System.out.println("start time="+t1);
			//执行目标对象方法
			Object result=method.invoke(target, args);
			long t2=tAspect.end();
			System.out.println("end time="+t2);
			System.out.println("totalTime="+(t2-t1));
			return result;//目标方法的返回值
		}
	}
	static Object newProxy(Object target){
		return Proxy.newProxyInstance(
				target.getClass().getClassLoader(),//ClassLoader(代理对象要使用加载器)
				target.getClass().getInterfaces(),//interfaces (代理对象要实现的接口)
				new ServiceHandler(target));//handler (代理对象要实现的业务交给handler)
	}

	public static void main(String[] args) {
		//target (目标对象)
		HelloService hService=new HelloServiceImpl();
		//proxy(代理对象)
		hService=(HelloService)newProxy(hService);
		//invoke method
		hService.sayHello("xia fei");
		//=================================
		
		LogService logService=new LogServiceImpl();
		logService=(LogService)newProxy(logService);
		int rows=logService.deleteObject(10);
		System.out.println("delete rows="+rows);
		
	}
}
