package com.zuql.jt.web.service;

import com.zuql.jt.manage.pojo.Cart;

import java.util.List;



public interface CartService {

	List<Cart> findCartByUserId(Long userId);

	void updateNum(Long userId, Long itemId, Long num);
	
	void deleteCart(Long userId, Long itemId);

	void saveCart(Cart cart);

}
