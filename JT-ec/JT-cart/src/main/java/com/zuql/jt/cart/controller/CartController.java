package com.zuql.jt.cart.controller;

import java.util.List;

import com.zuql.jt.cart.service.CartService;
import com.zuql.jt.common.vo.SysResult;
import com.zuql.jt.manage.pojo.Cart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;

//该注解只能使用在后台,目的返回json
@RestController  //效果 +@Controller+@ResponseBody
//@Controller
@RequestMapping("/cart")
public class CartController {
	
	@Autowired
	private CartService cartService;
	private ObjectMapper objectMapper = new ObjectMapper();
	
	
	
	//编辑测试案例
	@RequestMapping("/hello")
	@ResponseBody
	public String hello(){
		
		return "hello ";
	}
	
	
	//根据用户id查询购物车信息
	@RequestMapping("/query/{userId}")
	@ResponseBody
	public SysResult findCartByUserId(@PathVariable Long userId){
		try {
			List<Cart> cartList = cartService.findCartByUserId(userId);
			return SysResult.oK(cartList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return SysResult.build(201,"购物车查询失败");
	}
	
	
	//修改购物车数量
	@RequestMapping("/update/num/{userId}/{itemId}/{num}")
	@ResponseBody
	public SysResult updateNum(
			@PathVariable Long userId,
			@PathVariable Long itemId,
			@PathVariable Integer num){
		try {
			Cart cart = new Cart();
			cart.setUserId(userId);
			cart.setItemId(itemId);
			cart.setNum(num);
			cartService.updateNum(cart);
			return SysResult.oK();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SysResult.build(201,"购物车修改失败");
	}
	
	@RequestMapping("/delete/{userId}/{itemId}")
	//@ResponseBody
	public SysResult deleteCart(@PathVariable Long userId,
			@PathVariable Long itemId){
		try {
			Cart cart = new Cart();
			cart.setUserId(userId);
			cart.setItemId(itemId);
			cartService.deleteCart(cart);
			return SysResult.oK();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SysResult.build(201,"购物车删除失败");
	}
	
	
	//新增购物车
	@RequestMapping("/save")
	public SysResult saveCart(String cartJSON){
		try {
			Cart cart = 
					objectMapper.readValue(cartJSON,Cart.class);
			cartService.saveCart(cart);
			return SysResult.oK();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SysResult.build(201,"新增购物车失败");
	}
	
	
	
	
	
	
	
	
}
