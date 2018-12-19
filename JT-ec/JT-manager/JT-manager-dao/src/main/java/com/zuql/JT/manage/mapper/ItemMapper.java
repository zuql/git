package com.zuql.jt.manage.mapper;

import java.util.List;

import com.zuql.jt.common.mapper.SysMapper;
import com.zuql.jt.manage.pojo.Item;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;


public interface ItemMapper extends SysMapper<Item> {
	
	//使用注解形式查询数据库
	//@Insert  @Select  @Update  @Delete
	
	@Select("select count(*) from tb_item")
	int findItemCount();
	
	/**
	 * Mybatis不允许多值传参
	 * 1.将数据封装为POJO对象
	 * 2.将数据封装为Map集合  
	 * 	 @Param("start")Integer start 
	 * 	  将数据封装为map集合
	 * 3.将数据封装为Array数组类型
	 * 4.将数据封装为List集合 arrayList
	 * @param start
	 * @param rows
	 * @return
	 */
	List<Item> findItemByPage(
            @Param("start") Integer start,
            @Param("rows") Integer rows
    );
	
	@Select("select name from tb_item_cat where id = #{itemId}")
	String findItemCatNameById(Long itemId);

	void updateStatus(@Param("ids")Long[] ids, @Param("status")int status);
}
