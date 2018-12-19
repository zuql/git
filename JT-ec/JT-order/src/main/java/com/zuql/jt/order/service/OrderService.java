package com.zuql.jt.order.service;


import com.zuql.jt.manage.pojo.Order;

public interface OrderService {

	String saveOrder(Order order);
	Order findOrderById(String orderId);

}
