package com.zuql.jt.web.service;

import java.util.HashMap;
import java.util.Map;

import com.zuql.jt.common.service.HttpClientService;
import com.zuql.jt.manage.pojo.Item;
import com.zuql.jt.manage.pojo.ItemDesc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;


@Service
public class ItemServiceImpl implements ItemService{
	
	@Autowired
	private HttpClientService httpClient;
	
	private ObjectMapper objectmapper = new ObjectMapper();
	
	/**
	 * 1.定义url地址
	 * 2.封装参数
	 * 3.发起请求,解析返回值结果
	 */
	@Override
	public Item findItemById(Long itemId) {
		String url = "http://manager.jt.com/web/item/findItemById";
		Map<String,String> params = new HashMap<>();
		params.put("itemId",itemId+"");
		String itemJSON = httpClient.doGet(url, params);
		Item item = null;
		try {
			//将json转化为对象
			item = objectmapper.readValue(itemJSON,Item.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return item;
	}

	@Override
	public ItemDesc findItemDescById(Long itemId) {
		String url = "http://manager.jt.com/web/item/findItemDescById/"+itemId;
		String itemDescJSON = httpClient.doGet(url);
		ItemDesc itemDesc = null;
		try {
			itemDesc = objectmapper.readValue(itemDescJSON, ItemDesc.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return itemDesc;
	}
	
	
	
	
	
	
	
	
}
