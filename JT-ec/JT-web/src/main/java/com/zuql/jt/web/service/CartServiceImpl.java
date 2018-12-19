package com.zuql.jt.web.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.zuql.jt.common.service.HttpClientService;
import com.zuql.jt.common.vo.SysResult;
import com.zuql.jt.manage.pojo.Cart;
import com.zuql.jt.manage.pojo.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;


@Service
public class CartServiceImpl implements CartService {
	
	//获取商品信息
	@Autowired
	private ItemService itemService;
	
	@Autowired
	private HttpClientService httpClient;
	private ObjectMapper objectMapper = new ObjectMapper();
	
	@Override
	public List<Cart> findCartByUserId(Long userId) {
		//通过restFul风格进行数据提交
		String url = "http://cart.jt.com/cart/query/"+userId;
		String sysJSON = httpClient.doGet(url);
		List<Cart> cartList = null;
		try {
			//将json转化为SysResult对象
			SysResult sysResult =
			objectMapper.readValue(sysJSON,SysResult.class);
			
			if(sysResult.getStatus() == 200){
				//证明后台返回数据成功!
				cartList = (List<Cart>) sysResult.getData();
			}
			//如果状态为201时,表示后台查询数据为null.需要告知用户
			//服务器异常,请稍后重试. 后期维护.
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return cartList;
	}

	@Override
	public void updateNum(Long userId, Long itemId, Long num) {
		String url = "http://cart.jt.com/cart/update/num/"+userId+"/"+itemId+"/"+num;
		
		httpClient.doGet(url);
	}

	@Override
	public void deleteCart(Long userId, Long itemId) {
		
		String url = "http://cart.jt.com/cart/delete/"+userId+"/"+itemId;
		httpClient.doGet(url);
	}
	
	/**
	 * 有些黑客通过数据的替换,会将商品的价格进行修改
	 * 所以不能使用前台提交过来的数据进行入库操作.
	 * 应该再次查询商品的价格信息之后入库.
	 */
	@Override
	public void saveCart(Cart cart) {
		//获取真实的商品信息  一般前台查询的商品都会保存到缓存中 作业
		Item item = itemService.findItemById(cart.getItemId());
		cart.setItemPrice(item.getPrice());
		//定义url请求
		String url = "http://cart.jt.com/cart/save";
		try {
			//将数据封装为json
			String cartJSON = 
					objectMapper.writeValueAsString(cart);
			Map<String,String> params = new HashMap<>();
			params.put("cartJSON",cartJSON);
			httpClient.doPost(url, params);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	
	
	
	
	
	
}
