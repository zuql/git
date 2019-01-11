package com.pd.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pd.pojo.PdItem;
import com.pd.pojo.PdResult;
import com.pd.pojo.DetailVO;
import com.pd.service.ApiService;

@Controller
@RequestMapping("/api")
public class ApiController {

	@Autowired
	ApiService apiService;
	//   http://172.211.100.32/api/getItemDetail
	//     .html?itemId=10000028
	@RequestMapping("/getItemDetail.html")
	@ResponseBody
	public PdResult getItemDetail(Long itemId) 
			throws Exception
	{
		PdResult ajiaResult=new PdResult();
		DetailVO detailVO=apiService.getItemDetail(itemId);
		ajiaResult.setData(detailVO);
		ajiaResult.setStatus(200);
		return ajiaResult;
	}
	
	@RequestMapping("/getIndexItem.html")
	@ResponseBody
	public PdResult getIndexItem() throws Exception
	{
		PdResult ajiaResult=new PdResult();
		List<PdItem> list=apiService.getIndexItem();
		ajiaResult.setData(list);
		ajiaResult.setStatus(200);
		return ajiaResult;
		
	}
}
