package com.zuql.jt.order.service;

import java.util.Date;
import java.util.List;

import com.zuql.jt.manage.pojo.Order;
import com.zuql.jt.manage.pojo.OrderItem;
import com.zuql.jt.manage.pojo.OrderShipping;
import com.zuql.jt.order.mapper.OrderItemMapper;
import com.zuql.jt.order.mapper.OrderMapper;
import com.zuql.jt.order.mapper.OrderShippingMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrderMapper orderMapper;

	@Autowired
	private OrderShippingMapper orderShippingMapper;

	@Autowired
	private OrderItemMapper orderItemMapper;

	/**
	 * 通过一个order对象,完成3张表入库操作
	 * 订单号：登录用户id+当前时间戳  拼串
	 *
	 * 业务2:
	 * 	为了防止黑客攻击,造成总价的损失.应该怎么做?
	 * 	在入库在前应该将总价重新计算
	 * 	totalPrice = 单个商品价格 * 数量 +.....
	 */
	@Override
	public String saveOrder(Order order) {
		String orderId = "" + order.getUserId()
				+ System.currentTimeMillis();


		Date date = new Date();
		//1.实现订单入库
		order.setStatus(1);	//订单生成 未支付
		order.setOrderId(orderId);
		order.setCreated(date);
		order.setUpdated(date);
		orderMapper.insert(order);
		System.out.println("订单入库成功!!!!");

		//2.实现订单物流入库
		OrderShipping shipping = order.getOrderShipping();
		shipping.setOrderId(orderId);
		shipping.setCreated(date);
		shipping.setUpdated(date);
		orderShippingMapper.insert(shipping);
		System.out.println("订单物流入库成功!!!");

		//3.实现订单商品入库
		List<OrderItem> orderItems = order.getOrderItems();
		for (OrderItem orderItem : orderItems) {

			orderItem.setOrderId(orderId);
			orderItem.setCreated(date);
			orderItem.setUpdated(date);
			orderItemMapper.insert(orderItem);
		}
		System.out.println("订单商品信息,入库成功!!!!");
		return orderId;
	}


	@Override
	public Order findOrderById(String orderId) {
		/*1.利用通用Mapper进行查询*/
		Order order = orderMapper.selectByPrimaryKey(orderId);
		OrderShipping shipping = orderShippingMapper.selectByPrimaryKey(orderId);
		OrderItem orderItem = new OrderItem();
		orderItem.setOrderId(orderId);
		List<OrderItem> orderItems = orderItemMapper.select(orderItem);
		order.setOrderShipping(shipping);
		order.setOrderItems(orderItems);
		return order;
		/*2.利用Mybatis进行查询*/

	}
}
