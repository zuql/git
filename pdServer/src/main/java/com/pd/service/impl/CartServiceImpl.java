package com.pd.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pd.mapper.PdCartItemMapper;
import com.pd.mapper.PdItemMapper;
import com.pd.mapper.PdItemParamItemMapper;
import com.pd.pojo.PdCartItem;
import com.pd.pojo.PdCartItemExample;
import com.pd.pojo.PdItem;
import com.pd.pojo.PdItemParamItem;
import com.pd.pojo.PdItemParamItemExample;
import com.pd.pojo.ItemVO;
import com.pd.pojo.paramData.Params;
import com.pd.pojo.paramData.PdItemParamData;
import com.pd.common.utils.JsonUtils;
import com.pd.service.CartService;

@Service
public class CartServiceImpl implements CartService {

	@Autowired
	PdCartItemMapper ajiaCartItemMapper;

	@Autowired
	PdItemMapper ajiaItemMapper;

	@Autowired
	PdItemParamItemMapper ajiaItemParamItemMapper;

	@Override
	public List<ItemVO> selectByUserId(Long userId) throws Exception {
		PdCartItemExample cartItemExample = new PdCartItemExample();
		PdCartItemExample.Criteria criteria = cartItemExample.or();
		criteria.andUserIdEqualTo(userId);
		criteria.andStatusEqualTo(1);
		List<PdCartItem> cartItems = ajiaCartItemMapper.selectByExample(cartItemExample);
		List<ItemVO> itemVOs = new ArrayList<>();
		for (PdCartItem ajiaCartItem : cartItems) {
			Long itemId = ajiaCartItem.getItemId();
			PdItem ajiaItem = ajiaItemMapper.selectByPrimaryKey(itemId);
			PdItemParamItemExample paramExample = new PdItemParamItemExample();
			PdItemParamItemExample.Criteria paramCriteria = paramExample.or();
			paramCriteria.andItemIdEqualTo(itemId);
			List<PdItemParamItem> paramItems = ajiaItemParamItemMapper.selectByExampleWithBLOBs(paramExample);
			List<Params> paramsList = new ArrayList<>();
			if (paramItems != null && paramItems.size() >= 1) {
				PdItemParamItem ajiaItemParamItem = paramItems.get(0);
				String param_data = ajiaItemParamItem.getParamData();
				List<com.pd.pojo.paramData.PdItemParamData> paramDataList = JsonUtils.jsonToList(param_data, com.pd.pojo.paramData.PdItemParamData.class);
				paramsList = paramDataList.get(0).getParams();
			}
			ItemVO itemVO = new ItemVO();
			itemVO.setAjiaCartItem(ajiaCartItem);
			itemVO.setAjiaItem(ajiaItem);
			itemVO.setParamsList(paramsList);
			itemVOs.add(itemVO);
		}
		return itemVOs;
	}

	@Override
	public int insert(PdCartItem ajiaCartItem) throws Exception {
		// where userid=14 and item_id=28 and status=1
		PdCartItemExample example = new PdCartItemExample();
		PdCartItemExample.Criteria criteria = example.or();
		criteria.andUserIdEqualTo(ajiaCartItem.getUserId());
		criteria.andItemIdEqualTo(ajiaCartItem.getItemId());
		criteria.andStatusEqualTo(ajiaCartItem.getStatus());
		List<PdCartItem> list = ajiaCartItemMapper.selectByExample(example);
		int row = 0;
		if (list != null && list.size() >= 1) {
			PdCartItem dbItem = list.get(0);
			ajiaCartItem.setNum(dbItem.getNum() + ajiaCartItem.getNum());
			row = ajiaCartItemMapper.updateByExampleSelective(ajiaCartItem, example);
		} else {
			row = ajiaCartItemMapper.insert(ajiaCartItem);
		}
		return row;
	}

	@Override
	public int updateNum(PdCartItem ajiaCartItem) throws Exception {
		// update ajia_cart_item set num=5
		// where userId=14 and item_id=28 and status=1
		PdCartItemExample example = new PdCartItemExample();
		PdCartItemExample.Criteria criteria = example.or();
		criteria.andUserIdEqualTo(ajiaCartItem.getUserId());
		criteria.andItemIdEqualTo(ajiaCartItem.getItemId());
		criteria.andStatusEqualTo(ajiaCartItem.getStatus());
		int row = ajiaCartItemMapper.updateByExampleSelective(ajiaCartItem, example);

		return row;
	}

	@Override
	public int batchDelete(Long userId, List<Long> itemIds) throws Exception {
		PdCartItemExample example = new PdCartItemExample();
		PdCartItemExample.Criteria criteria = example.or();
		criteria.andUserIdEqualTo(userId);
		criteria.andItemIdIn(itemIds);

		PdCartItem ajiaCartItem = new PdCartItem();
		ajiaCartItem.setStatus(2);

		int row = ajiaCartItemMapper.updateByExampleSelective(ajiaCartItem, example);

		return row;
	}

}
