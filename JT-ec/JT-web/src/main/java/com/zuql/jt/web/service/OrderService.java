package com.zuql.jt.web.service;

import com.zuql.jt.manage.pojo.Order;

public interface OrderService {

	String saveOrder(Order order);
	Order findOrderById(String id);

}
