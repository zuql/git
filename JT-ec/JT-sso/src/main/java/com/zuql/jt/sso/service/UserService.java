package com.zuql.jt.sso.service;

import com.zuql.jt.manage.pojo.User;

public interface UserService {

	Boolean findCheckUser(String param, Integer type);

	void saveUser(User user);

	String findUserByUP(User user);

}
