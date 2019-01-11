package com.pd.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.pd.mapper.PdOrderMapper;
import com.pd.pojo.PdOrder;
import com.pd.pojo.ItemVO;
import com.pd.pojo.OrderVO;
import com.github.pagehelper.PageInfo;
import com.pd.service.OrderService;

@Controller
@RequestMapping("/order")
public class OrderController {
	
	@Autowired
	OrderService orderService;
	
	@Value("${pageSize}")
	String pageSize;
	
	@RequestMapping("/toPayment.html")
	public String toPayment(Model model
			,String orderId) 
			throws Exception
	{
		PdOrder ajiaOrder=orderService.selectById(orderId);
		model.addAttribute("ajiaOrder", ajiaOrder);
		return "payment";
	}
	
	@RequestMapping("/cancelOrder.html")
	public ModelAndView cancelOrder(String orderId,
			@RequestParam(defaultValue="all")
	String status,
	@RequestParam(defaultValue="1")
	int currentPage
			) 
			throws Exception
	{
		ModelAndView modelAndView=new ModelAndView();
		PdOrder ajiaOrder=orderService
				.findOrderByOrderid(orderId);
		if (ajiaOrder!=null && ajiaOrder.getUserId()==14L)
		{
			orderService.updateOrderStatus(orderId, 8);
		}		
		modelAndView.setViewName		
		("redirect:/order/toMyOrder.html?status="
		+status+"&currentPage="+currentPage);
		return modelAndView;
	}
	
	@RequestMapping("/toMyOrder.html")
	public ModelAndView toMyOrder
	(@RequestParam(defaultValue="all")String status,
		@RequestParam(defaultValue="1")	int currentPage) throws Exception {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("status", status);
		
		
		int istatus=0;
		switch (status) {
		case "waitPay":
			istatus=1;
			break;
		case "waitReceive":
			istatus=5;
			break;
		case "waitAssess":
			istatus=6;
			break;
		case "all":
			istatus=0;
			break;
		}
//		List<OrderVO> orderVOs=orderService
//				.selectByUserIdAndStatus(14L, istatus);
		List<OrderVO> orderVOs=orderService.selectByUserIdAndStatus(14L, istatus);
		modelAndView.addObject("orderVOs", 	orderVOs);
		
		modelAndView.setViewName("myOrder");
		return modelAndView;
	}

	@RequestMapping("/submitOrder.html")
	// http://www.ajstore.com/order/submitOrder.html
	// ?addId=1&itemId=28&itemId=30
	public ModelAndView submitOrder(Long addId, Long[] itemId) throws Exception {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("payment");
		List<Long> itemIdList = Arrays.asList(itemId);

		PdOrder ajiaOrder = orderService.saveOrder(addId, 14L, itemIdList);
		modelAndView.addObject("ajiaOrder", ajiaOrder);
		return modelAndView;
	}

	@RequestMapping("/confirmOrder.html")
	public ModelAndView confirmOrder(String itemIds) throws Exception {
		ModelAndView modelAndView = new ModelAndView();
		String[] array = itemIds.split("\\,");
		List<Long> itemIdList = new ArrayList<>();
		for (String string : array) {
			itemIdList.add(Long.parseLong(string));
		}
		List<ItemVO> itemVOs = orderService.selectCartItemByUseridAndItemIds(14L, itemIdList);
		modelAndView.addObject("itemVOs", itemVOs);

		modelAndView.setViewName("orderConfirm");
		return modelAndView;
	}

	// http://www.ajstore.com/select.html
	@RequestMapping("/select.html")
	@ResponseBody
	public PdOrder select() {
		PdOrder ajiaOrder = new PdOrder();
		ajiaOrder.setOrderId("1111");
		return ajiaOrder;
	}

	// http://www.ajstore.com/show.html
	@RequestMapping("/show.html")
	public ModelAndView show() throws Exception {
		
		ModelAndView modelAndView = new ModelAndView("order");
		PdOrder ajiaOrder = orderService.selectById("20161001490667343075");
		modelAndView.addObject("payment", ajiaOrder.getPayment());
		return modelAndView;
	}

}
