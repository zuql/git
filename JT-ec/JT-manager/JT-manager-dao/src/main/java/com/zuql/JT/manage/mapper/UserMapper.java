package com.zuql.jt.manage.mapper;

import com.zuql.jt.manage.pojo.User;

import java.util.List;

public interface UserMapper {
	
	//查询用户表中的数据
	List<User> findAll();
}
