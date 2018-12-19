package com.zuql.hash;
import java.security.MessageDigest;
import java.util.Arrays;

public class TestMD501 {
	public static void main(String[] args)
	throws Exception{
		String pwd="123456";
		//1.获取消息摘要对象
		MessageDigest md=//md5不可逆的加密算法
		MessageDigest.getInstance("MD5");
		//2.对内容进行加密
		byte[]array=md.digest(pwd.getBytes());
		System.out.println(array.length);//16byte=128bit
	    //3.将加密结果转换为16进制字符串
		StringBuilder sb=new StringBuilder();
		for(int i=0;i<array.length;i++){
			String s=Integer.toHexString(array[i]&0xff);
			if(s.length()==1){
				s='0'+s;
			}
			sb.append(s);
		}
		System.out.println(sb.toString());
		//e10adc3949ba59abbe56e057f20f883e
		//e10adc3949ba59abbe56e057f20f883e
	}
}







