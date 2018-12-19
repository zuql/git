package com.zuql.jt.web.service;

import com.zuql.jt.manage.pojo.User;

public interface UserService {

	void saveUser(User user);

	String findUserByUP(User user);

}
