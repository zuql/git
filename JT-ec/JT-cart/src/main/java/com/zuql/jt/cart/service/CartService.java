package com.zuql.jt.cart.service;

import com.zuql.jt.manage.pojo.Cart;

import java.util.List;

public interface CartService {

	List<Cart> findCartByUserId(Long userId);

	void updateNum(Cart cart);

	void deleteCart(Cart cart);

	void saveCart(Cart cart);

}
