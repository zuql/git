package com.zuql.jt.cart.mapper;

import com.zuql.jt.common.mapper.SysMapper;
import com.zuql.jt.manage.pojo.Cart;

public interface CartMapper extends SysMapper<Cart> {

	void updateNum(Cart cart);

	Cart findCartByUI(Cart cart);
	
	
}
