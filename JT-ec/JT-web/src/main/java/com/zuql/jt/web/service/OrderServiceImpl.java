package com.zuql.jt.web.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.zuql.jt.common.service.HttpClientService;
import com.zuql.jt.common.vo.SysResult;
import com.zuql.jt.manage.pojo.Item;
import com.zuql.jt.manage.pojo.Order;
import com.zuql.jt.manage.pojo.OrderItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;


@Service
public class OrderServiceImpl implements OrderService {
	@Autowired
	private ItemService itemService;
	@Autowired
	private HttpClientService httpClient;
	private ObjectMapper objectMapper = new ObjectMapper();

	//用户需要获取orderId
	@Override
	public String saveOrder(Order order) {
		/*
		 * 为了保证数据价格的有效性,查询数据库重新计算价格
		 * 1.获取商品的Id号
		 * 2.获取用户购买商品的数量
		 * 3.求和
		 * 4.重新修改商品的价格
		 */
		List<OrderItem> orderItemList = order.getOrderItems();
		long totalPrice = 0L; //计算的总价
		for (OrderItem orderItemTemp : orderItemList) {
			String itemIdTemp = orderItemTemp.getItemId();
			long num = orderItemTemp.getNum(); //获取商品数量
			//从数据库中获取商品的价格信息
			Item itemTemp =itemService.findItemById(Long.parseLong(itemIdTemp));
			long itemPriceTemp = itemTemp.getPrice();
			//计算总价
			totalPrice  += itemPriceTemp * num;
			//应该将orderItemList中的数据进行更新
			orderItemTemp.setPrice(itemPriceTemp);
		}
		//将总价添加到对象中.
		order.setPayment(""+totalPrice);
		//该请求是后台底层发起的,浏览器不能监控
		String url = "http://order.jt.com/order/create";
		String orderId = null;
		//将order对象转化为orderJSON数据
		try {
			String orderJSON =
					objectMapper.writeValueAsString(order);
			Map<String,String> params = new HashMap<>();
			params.put("orderJSON", orderJSON);
			//发送入库请求.
			String sysJSON = httpClient.doPost(url, params);
			SysResult sysResult =
					objectMapper.readValue(sysJSON,SysResult.class);
			//判断后台执行是否正确
			if(sysResult.getStatus() == 200){

				orderId = (String) sysResult.getData();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return orderId;
    }


	@Override
	public Order findOrderById(String id) {
		String url = "http://order.jt.com/order/query/"+id;
		String orderJSON = httpClient.doGet(url);
		Order order = null;
		try {
			order = objectMapper.readValue(orderJSON, Order.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return order;
	}
}
