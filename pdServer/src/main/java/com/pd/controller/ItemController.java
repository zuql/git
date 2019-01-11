package com.pd.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.pd.pojo.PdItem;
import com.pd.pojo.PdItemDesc;
import com.pd.pojo.PdItemParam;
import com.pd.pojo.PdItemParamItem;
import com.pd.pojo.PdResult;
import com.pd.pojo.paramData.PdItemParamData;
import com.pd.pojo.paramData.Params;
import com.pd.common.utils.JsonUtils;
import com.pd.service.ItemService;

@Controller
@RequestMapping("/item")
public class ItemController {
	@Autowired
	ItemService itemService;
	//www.ajstore.com/item/getItemId.html
	@RequestMapping("/getItemId.html")
	@ResponseBody
	public PdResult getItemId(String color,
			String model,
			String itemParamId) throws Exception
	{
		PdResult ajiaResult=new PdResult();
		Long itemId=10000033L;
		
		itemId=itemService.selectItemByParams(color, model,
				Long.parseLong(itemParamId));
		ajiaResult.setData(itemId);
		return ajiaResult;
	}
	@RequestMapping("/toItemInfo.html")
	public ModelAndView toItemInfo(Long itemId) throws Exception
	{
		ModelAndView modelAndView=new ModelAndView();
		modelAndView.addObject("itemId", itemId);
		
		
		PdItemParamItem ajiaItemParamItem=itemService
				.getParamDataById(itemId);
		String paramData=ajiaItemParamItem.getParamData();
		List<PdItemParamData> list=JsonUtils
				.jsonToList(paramData, PdItemParamData.class);
		Long itemParamId=ajiaItemParamItem.getItemParamId();
		modelAndView.addObject("itemParamId", itemParamId);
		//[{params:[{}]},{}]
		String itemColor="",itemModel="";
		for (Params params:list.get(0).getParams())
		{
			if (params.getKey().equals("颜色") 
					&& params.getValues()!=null)
			{
				itemColor=params.getValues().get(0);
			}
			if (params.getKey().equals("型号") 
					&& params.getValues()!=null)
			{
				itemModel=params.getValues().get(0);
			}
		}
		modelAndView.addObject("itemColor", itemColor);
		modelAndView.addObject("itemModel", itemModel);
		
		PdItem ajiaItem=itemService
				.selectItemById(itemId);
		modelAndView.addObject("ajiaItem", ajiaItem);
		
		PdItemDesc ajiaItemDesc=itemService
				.selectDescById(itemId);
		String desc=ajiaItemDesc.getItemDesc();
		modelAndView.addObject("desc", desc);
		
		itemParamId=ajiaItemParamItem
				.getItemParamId();
		PdItemParam ajiaItemParam=itemService
				.selectTypeParamByTypeId(itemParamId);
		String typeParamData=ajiaItemParam
				.getParamData();
		List<PdItemParamData> typeList=JsonUtils
				.jsonToList(typeParamData, 
						PdItemParamData.class);
		modelAndView.addObject("typeParamData",typeList);
		
		modelAndView.addObject("paramData", list);
		modelAndView.setViewName("product_details");
		return modelAndView;
	}

}
