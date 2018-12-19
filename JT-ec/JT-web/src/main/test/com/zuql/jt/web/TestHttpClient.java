package com.zuql.jt.web;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

public class TestHttpClient {
	
	//测试远程访问tmooc
	/**
	 * 步骤:
	 * 	1.定义httpClient请求对象
	 *  2.定义访问url地址
	 *  3.定义请求类型.get/post/put
	 *  4.发起请求,获取返回值结果
	 *  5.判断返回值状态是否为200
	 *  6.解析返回值.
	 * @throws IOException 
	 * @throws ClientProtocolException 
	 */
	@Test
	public void testGet() throws ClientProtocolException, IOException{
		CloseableHttpClient client = 
				HttpClients.createDefault();
		String url = "http://www.tmooc.cn/course/100040.shtml";
		HttpGet get = new HttpGet(url);
		HttpPost post = new HttpPost(url);
		CloseableHttpResponse response = 
				client.execute(get);
		if(response.getStatusLine()
				.getStatusCode() == 200){
			String result = 
					EntityUtils.toString
					(response.getEntity());
			System.out.println(result);
		}	
	}
}
