package com.zuql.memory;

public class TestMemory01 {

	static void doMethod01(){
	    doMethod02();
	}
	static void doMethod02(){
		Thread t=Thread.currentThread();
		StackTraceElement[] stm=t.getStackTrace();
		System.out.println(stm.length);
		for(StackTraceElement e:stm){
			System.out.println(e);
		}
	}//栈帧(Stack Track)
	public static void main(String[] args) {
		doMethod01();
	}
}
