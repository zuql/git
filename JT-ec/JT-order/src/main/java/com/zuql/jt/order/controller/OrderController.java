package com.zuql.jt.order.controller;

import com.zuql.jt.common.vo.SysResult;
import com.zuql.jt.manage.pojo.Order;
import com.zuql.jt.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;


@RestController	//要求后台返回数据都是json
@RequestMapping("/order")
public class OrderController {
	
	@Autowired
	private OrderService orderService;
	private ObjectMapper objectMapper = new ObjectMapper();
	
	
	
	//实现订单入库
	@RequestMapping("/create")
	public SysResult saveOrder(String orderJSON){
		try {
			Order order =
					objectMapper.readValue(orderJSON,Order.class);
			String orderId = orderService.saveOrder(order);
			if(!StringUtils.isEmpty(orderId)){
				return SysResult.oK(orderId);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SysResult.build(201,"订单入库失败");
	}
	//根据订单ID进行查询
	@RequestMapping("/query/{orderId}")
	public Order findOrderById(@PathVariable String orderId){

		return orderService.findOrderById(orderId);
	}
}
