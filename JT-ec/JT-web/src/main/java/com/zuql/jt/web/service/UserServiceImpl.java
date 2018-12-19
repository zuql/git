package com.zuql.jt.web.service;

import java.util.HashMap;
import java.util.Map;


import com.zuql.jt.common.service.HttpClientService;
import com.zuql.jt.common.vo.SysResult;
import com.zuql.jt.manage.pojo.User;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private HttpClientService httpClient;
	
	private ObjectMapper objectMapper = new ObjectMapper();

	@Override
	public void saveUser(User user) {
		//1.定义url地址
		String url = "http://sso.jt.com/user/register";
		
		//2.封装参数
		String md5Pass = DigestUtils.md5Hex(user.getPassword());
		Map<String,String> params = new HashMap<>();
		params.put("username", user.getUsername());
		params.put("password", md5Pass);
		params.put("phone", user.getPhone());
		params.put("email", user.getEmail());
		
		//3.发起post请求,获取返回值 
		String sysJSON = httpClient.doPost(url, params);
		try {
			SysResult result =
			objectMapper.readValue(sysJSON,SysResult.class);
			
			//如果后台有错,则告知用户
			if(result.getStatus() != 200){
				
				throw new RuntimeException();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

	@Override
	public String findUserByUP(User user) {
		String token = null;
		//1.定义url
		String url = "http://sso.jt.com/user/login";
		String md5Pass = DigestUtils.md5Hex(user.getPassword());
		Map<String,String> params = new HashMap<>();
		params.put("username", user.getUsername());
		params.put("password", md5Pass);
		
		String sysJSON = httpClient.doPost(url, params);
		
		//将sysJSON转化为对象
		try {
			SysResult sysResult = objectMapper.readValue(sysJSON,SysResult.class);
			
			//判断后台处理是否正确.
			if(sysResult.getStatus() == 200){
				
				token = (String) sysResult.getData();
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
		
		return token;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
}
