package com.zuql.jt.manage.controller;

import java.util.List;

import com.zuql.jt.common.vo.EasyUITree;
import com.zuql.jt.manage.service.ItemCatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;



@Controller
@RequestMapping("/item/cat")
public class ItemCatController {
	
	@Autowired
	private ItemCatService itemCatService;
	
	/**
	 * 
	 * @param parentId
	 * @return
	 * @RequestParam(value="id",defaultValue="",required=true)
	 * value ="接收参数的名称"
	 * defaultValue = "可以设定一个默认值"
	 * required=true 该属性必须传递.
	 */
	//根据商品parentId查询商品分类信息
	@RequestMapping("/list")
	@ResponseBody
	public List<EasyUITree> findItemCatByParentId(
			@RequestParam(value="id",defaultValue="0")  Long parentId){
		
		//1.实现查询一级商品分类的信息
		//Long parentId = id;
		return itemCatService.findCacheList(parentId);
	}
	
}
