package com.pd.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pd.mapper.PdCartItemMapper;
import com.pd.mapper.PdItemMapper;
import com.pd.mapper.PdItemParamItemMapper;
import com.pd.mapper.PdOrderItemMapper;
import com.pd.mapper.PdOrderMapper;
import com.pd.mapper.PdShippingMapper;
import com.pd.pojo.PdCartItem;
import com.pd.pojo.PdCartItemExample;
import com.pd.pojo.PdItem;
import com.pd.pojo.PdItemParamItem;
import com.pd.pojo.PdItemParamItemExample;
import com.pd.pojo.PdOrder;
import com.pd.pojo.PdOrderExample;
import com.pd.pojo.PdOrderItem;
import com.pd.pojo.PdOrderItemExample;
import com.pd.pojo.PdShipping;
import com.pd.pojo.ItemVO;
import com.pd.pojo.OrderVO;
import com.pd.pojo.paramData.PdItemParamData;
import com.pd.pojo.paramData.Params;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pd.common.utils.JsonUtils;
import com.pd.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService {
	@Autowired
	PdOrderMapper ajiaOrderMapper;

	@Autowired
	PdCartItemMapper ajiaCartItemMapper;

	@Autowired
	PdItemMapper ajiaItemMapper;

	@Autowired
	PdItemParamItemMapper ajiaItemParamItemMapper;

	@Autowired
	PdShippingMapper ajiaShippingMapper;

	@Autowired
	PdOrderItemMapper ajiaOrderItemMapper;

	public synchronized String generateId() {
		Random random = new Random();
		int number = random.nextInt(9000000) + 1000000;
		return "" + System.currentTimeMillis() + number;
	}

	
	public PdOrder saveOrder(Long addId, Long userId, List<Long> itemId) throws Exception {
		PdShipping ajiaShipping = ajiaShippingMapper.selectByPrimaryKey(addId);
		String orderId = generateId();
		PdOrder ajiaOrder = new PdOrder();
		ajiaOrder.setOrderId(orderId);
		ajiaOrder.setUserId(userId);
		ajiaOrder.setAddId(addId);
		ajiaOrder.setShippingName(ajiaShipping.getReceiverName());
		ajiaOrder.setShippingCode(ajiaShipping.getReceiverAddress());
		ajiaOrder.setStatus(1);// 
		ajiaOrder.setPaymentType(1);
		ajiaOrder.setPostFee(10D);
		ajiaOrder.setCreateTime(new Date());

		double payment = 0;
		List<ItemVO> itemVOs = selectCartItemByUseridAndItemIds(userId, itemId);
		for (ItemVO itemVO : itemVOs) {
			PdOrderItem ajiaOrderItem = new PdOrderItem();
			String id = generateId();
			//String id="2";
			ajiaOrderItem.setId(id);
			ajiaOrderItem.setOrderId(orderId);
			ajiaOrderItem.setItemId("" + itemVO.getAjiaItem().getId());
			ajiaOrderItem.setTitle(itemVO.getAjiaItem().getTitle());
			ajiaOrderItem.setPrice(itemVO.getAjiaItem().getPrice());
			ajiaOrderItem.setNum(itemVO.getAjiaCartItem().getNum());

			payment = payment + itemVO.getAjiaCartItem().getNum() * itemVO.getAjiaItem().getPrice();
			ajiaOrderItemMapper.insert(ajiaOrderItem);
		}
		ajiaOrder.setPayment(payment);
		ajiaOrderMapper.insert(ajiaOrder);
		return ajiaOrder;
	}

	
	public List<ItemVO> selectCartItemByUseridAndItemIds(Long userId, List<Long> itemIds) throws Exception {
		PdCartItemExample cartItemExample = new PdCartItemExample();
		PdCartItemExample.Criteria criteria = cartItemExample.or();
		criteria.andUserIdEqualTo(userId);
		criteria.andItemIdIn(itemIds);
		criteria.andStatusEqualTo(1);

		List<PdCartItem> cartItems = ajiaCartItemMapper.selectByExample(cartItemExample);
		List<ItemVO> itemVOs = new ArrayList<>();
		for (PdCartItem ajiaCartItem : cartItems) {
			Long itemId = ajiaCartItem.getItemId();
			PdItem ajiaItem = ajiaItemMapper.selectByPrimaryKey(itemId);
			PdItemParamItemExample paramExample = new PdItemParamItemExample();
			PdItemParamItemExample.Criteria paramCriteria = paramExample.or();
			paramCriteria.andItemIdEqualTo(itemId);
			List<PdItemParamItem> items = ajiaItemParamItemMapper.selectByExampleWithBLOBs(paramExample);
			List<Params> paramsList = new ArrayList<>();
			if (items != null && items.size() >= 1) {
				PdItemParamItem item = items.get(0);
				String paramData = item.getParamData();
				List<PdItemParamData> list = JsonUtils.jsonToList(paramData, PdItemParamData.class);
				paramsList = list.get(0).getParams();
			}
			ItemVO itemVO = new ItemVO();
			itemVO.setAjiaCartItem(ajiaCartItem);
			itemVO.setAjiaItem(ajiaItem);
			itemVO.setParamsList(paramsList);
			itemVOs.add(itemVO);
		}
		return itemVOs;
	}

	
	public PdOrder selectById(String orderId) throws Exception {
		PdOrder ajiaOrder = ajiaOrderMapper.selectByPrimaryKey(orderId);
		return ajiaOrder;
	}

	
	public List<OrderVO> selectByUserIdAndStatus
	(Long userId, int status) throws Exception {
		//where user_id=14 and status!=9 order by create_time desc
		PdOrderExample orderExample=new PdOrderExample();
		PdOrderExample.Criteria criteria=orderExample.or();
		criteria.andUserIdEqualTo(userId);
		criteria.andStatusNotEqualTo(9);//9锟斤拷示锟斤拷锟斤拷锟窖撅拷删锟斤拷
		if (status!=0)
		{
			criteria.andStatusEqualTo(status);
		}
		orderExample.setOrderByClause("create_time desc");
		List<PdOrder> orders=ajiaOrderMapper
				.selectByExample(orderExample);
		
		List<OrderVO> orderVOs=new ArrayList<>();
		for (PdOrder ajiaOrder:orders)
		{
			//from ajia_order_item where order_id=30;
			PdOrderItemExample itemExample=new PdOrderItemExample();
			PdOrderItemExample.Criteria itemCriteria=itemExample.or();
			itemCriteria.andOrderIdEqualTo(ajiaOrder.getOrderId());
			List<PdOrderItem> orderItems=
					ajiaOrderItemMapper
					.selectByExample(itemExample);
			for (PdOrderItem ajiaOrderItem:orderItems)
			{
				
			//3.3.1 example,criteria
				PdItemParamItemExample example=
						new PdItemParamItemExample();
				PdItemParamItemExample.Criteria paramCriteria=
						example.or();
				long itemId=Long.parseLong(ajiaOrderItem
						.getItemId());
				paramCriteria.andItemIdEqualTo(itemId);
				
				List<PdItemParamItem> paramItemList=ajiaItemParamItemMapper
						.selectByExampleWithBLOBs(example);
				List<Params> paramsList=new ArrayList<>();
				if (paramItemList.size()>=1)
				{
			//3.3.3 取param_data
					PdItemParamItem ajiaItemParamItem=
							paramItemList.get(0);
					String paramData=ajiaItemParamItem
							.getParamData();					
					List<PdItemParamData> paramDataList=JsonUtils
							.jsonToList(paramData, 
									PdItemParamData.class);
					paramsList=paramDataList.get(0).getParams();
				}
			ajiaOrderItem.setParamsList(paramsList);	
			}
			OrderVO orderVO=new OrderVO();
			orderVO.setAjiaOrder(ajiaOrder);
			orderVO.setAjiaOrderItems(orderItems);
			
			orderVOs.add(orderVO);
		}
		return orderVOs;
	}

	
	

	
	public PdOrder findOrderByOrderid(String orderId) throws Exception {
PdOrder ajiaOrder=ajiaOrderMapper
.selectByPrimaryKey(orderId);
		return ajiaOrder;
	}

	@Override
	public int updateOrderStatus(String orderId, int status) throws Exception {
//update ajia_order set status=8 where orderId=100
		PdOrder ajiaOrder=new PdOrder();
		ajiaOrder.setStatus(status);
		
		PdOrderExample example=new PdOrderExample();
		PdOrderExample.Criteria criteria=example.or();
		criteria.andOrderIdEqualTo(orderId);
		
		int row=ajiaOrderMapper
				.updateByExampleSelective
				(ajiaOrder, example); 
		return row;
	}

}
