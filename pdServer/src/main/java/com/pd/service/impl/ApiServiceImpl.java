package com.pd.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pd.mapper.PdItemDescMapper;
import com.pd.mapper.PdItemMapper;
import com.pd.pojo.PdItem;
import com.pd.pojo.PdItemDesc;
import com.pd.pojo.PdItemExample;
import com.pd.pojo.DetailVO;
import com.pd.service.ApiService;

@Service
public class ApiServiceImpl implements ApiService {

	@Autowired
	PdItemMapper ajiaItemMapper;

	@Autowired
	PdItemDescMapper ajiaItemDescMapper;

	@Override
	public List<PdItem> getIndexItem() throws Exception {
		// select * from ajia_item where item_id in(10000028,1000029)
		ArrayList<Long> itemIds = new ArrayList<>();
		itemIds.add(830972L);
		itemIds.add(832739L);
		itemIds.add(844022L);
		itemIds.add(847276L);

		PdItemExample example = new PdItemExample();
		PdItemExample.Criteria criteria = example.or();
		criteria.andIdIn(itemIds);

		List<PdItem> list = ajiaItemMapper.selectByExample(example);

		return list;
	}

	@Override
	public DetailVO getItemDetail(Long itemId) throws Exception {
		DetailVO detailVO = new DetailVO();

		PdItem ajiaItem = ajiaItemMapper
				.selectByPrimaryKey(itemId);
		PdItemDesc ajiaItemDesc = ajiaItemDescMapper
				.selectByPrimaryKey(itemId);

		//detailVO.setPdItem(ajiaItem);
		//detailVO.setPdItemDesc(ajiaItemDesc);
		detailVO.setId(ajiaItem.getId());
		detailVO.setTitle(ajiaItem.getTitle());
		detailVO.setSellPoint(ajiaItem.getSellPoint());
		detailVO.setImage(ajiaItem.getImage());
		detailVO.setDesc(ajiaItemDesc.getItemDesc());

		return detailVO;
	}

}
