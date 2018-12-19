package com.zuql.args;

public class ArgsDemo01 {
	
/*	static void doPrint(String s1){}
	static void doPrint(String s1,String s2){}
	static void doPrint(String s1,String s2,String s3){}
*/
	static void doPrint(String... s){
	//可变参数,可以将其理解为动态数组
		for(int i=0;i<s.length;i++){
			System.out.println(s[i]);
		}
	}
	public static void main(String[] args) {
		doPrint("1");
		doPrint("1","2");
		doPrint("1","2","3");
	}
}
