package com.zuql.jt.manage.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zuql.jt.manage.mapper.UserMapper;
import com.zuql.jt.manage.pojo.User;
@Service
public class UserServiceImpl implements com.zuql.jt.manage.service.UserService {
	
	@Autowired
	private UserMapper userMapper;
	
	
	@Override
	public List<User> findAll() {
		
		return userMapper.findAll();
	}
	
	
	
	
	
	
	
	
	
	

}
