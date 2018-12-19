package com.zuql.jt.sso.mapper;

import com.zuql.jt.common.mapper.SysMapper;
import com.zuql.jt.manage.pojo.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;


public interface UserMapper extends SysMapper<User> {
	
	//根据用户指定的数据查询数据库
	int findCheckUser(@Param("param") String param, @Param("column") String column);
	
	@Select("select * from tb_user where username = #{username} "
			+ "and password = #{password}")
	User findUserByUP(User user);
	
	
	
	
	
	
	
	
	
}
