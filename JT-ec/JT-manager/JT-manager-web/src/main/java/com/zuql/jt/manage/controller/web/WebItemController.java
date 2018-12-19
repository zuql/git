package com.zuql.jt.manage.controller.web;

import com.zuql.jt.manage.pojo.Item;
import com.zuql.jt.manage.pojo.ItemDesc;
import com.zuql.jt.manage.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/web/item")
public class WebItemController {
	
	@Autowired
	private ItemService itemService;
	
	//http://manage.jt.com/web/item/findItemById
	@RequestMapping("/findItemById")
	@ResponseBody
	public Item findItemById(Long itemId){
		
		return itemService.findItemById(itemId);
	}
	
	@RequestMapping("/findItemDescById/{itemId}")
	@ResponseBody
	public ItemDesc findItemDescById(@PathVariable Long itemId){
		
		return itemService.findItemDescById(itemId);
	}
	
	
	
	
	
	
	
}
