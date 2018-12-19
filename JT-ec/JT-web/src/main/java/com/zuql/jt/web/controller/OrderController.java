package com.zuql.jt.web.controller;

import java.util.List;

import com.zuql.jt.common.vo.SysResult;
import com.zuql.jt.manage.pojo.Cart;
import com.zuql.jt.manage.pojo.Order;
import com.zuql.jt.web.service.CartService;
import com.zuql.jt.web.service.OrderService;
import com.zuql.jt.web.thread.UserThreadLocal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/order")
public class OrderController {
	
	@Autowired
	private CartService cartService;
	
	@Autowired
	private OrderService orderService;
	
	//实现订单确认页面的跳转
	@RequestMapping("/create")
	public String create(Model model){
		//获取用户数据
		Long userId = UserThreadLocal.get().getId();
		List<Cart> carts =
				cartService.findCartByUserId(userId);
		model.addAttribute("carts", carts);
		return "order-cart";
	}
	
	//orderShipping.receiverName
	
	@RequestMapping("/submit")
	@ResponseBody
	public SysResult saveOrder(Order order){
		//前台要求获取orderId之后进行页面跳转
		try {
			Long userId = UserThreadLocal.get().getId();
			order.setUserId(userId);	//获取用户id
			String orderId = orderService.saveOrder(order);
			if(!StringUtils.isEmpty(orderId)){
				
				return SysResult.oK(orderId) ;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SysResult.build(201,"订单创建失败");
	}

	//根据orderId查询订单信息 Order{orderShipping,List<OrderItem>}
	@RequestMapping("/success")
	public String findOrderById(String id,Model model){

		Order order = orderService.findOrderById(id);
		model.addAttribute("order", order);
		return "success";
	}
	
	
	
	
}
