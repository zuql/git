package com.pd.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pd.mapper.PdItemDescMapper;
import com.pd.mapper.PdItemMapper;
import com.pd.mapper.PdItemParamItemMapper;
import com.pd.mapper.PdItemParamMapper;
import com.pd.pojo.PdItem;
import com.pd.pojo.PdItemDesc;
import com.pd.pojo.PdItemParam;
import com.pd.pojo.PdItemParamItem;
import com.pd.pojo.PdItemParamItemExample;
import com.pd.pojo.paramData.PdItemParamData;
import com.pd.pojo.paramData.Params;
import com.pd.common.utils.JsonUtils;
import com.pd.service.ItemService;

@Service
public class ItemServiceImpl implements ItemService {
	@Autowired
	PdItemMapper ajiaItemMapper;

	@Autowired
	PdItemParamItemMapper ajiaItemParamItemMapper;

	@Autowired
	PdItemDescMapper ajiaItemDescMapper;

	@Autowired
	PdItemParamMapper ajiaItemParamMapper;

	@Override
	public PdItemParam selectTypeParamByTypeId(Long itemParamId) throws Exception {

		PdItemParam ajiaItemParam = ajiaItemParamMapper.selectByPrimaryKey(itemParamId);

		return ajiaItemParam;
	}

	@Override
	public PdItemDesc selectDescById(Long itemId) throws Exception {
		PdItemDesc ajiaItemDesc = ajiaItemDescMapper.selectByPrimaryKey(itemId);
		return ajiaItemDesc;
	}

	@Override
	public PdItemParamItem getParamDataById(Long itemId) throws Exception {
		// where item_id=10000028;
		PdItemParamItemExample example = new PdItemParamItemExample();

		PdItemParamItemExample.Criteria criteria = example.or();
		criteria.andItemIdEqualTo(itemId);
		List<PdItemParamItem> list = ajiaItemParamItemMapper.selectByExampleWithBLOBs(example);
		if (list != null && list.size() >= 1) {
			return list.get(0);
		}
		return null;
	}

	@Override
	public List<PdItem> selectAll() throws Exception {
		return ajiaItemMapper.selectByExample(null);
	}

	@Override
	public PdItem selectItemById(Long itemId) throws Exception {
		PdItem ajiaItem = ajiaItemMapper.selectByPrimaryKey(itemId);
		return ajiaItem;
	}

	@Override
	public Long selectItemByParams(String color, String model, Long itemParamId) throws Exception {
		// where item_param_id=111;
		PdItemParamItemExample example = new PdItemParamItemExample();

		PdItemParamItemExample.Criteria criteria = example.or();

		criteria.andItemParamIdEqualTo(itemParamId);
		List<PdItemParamItem> itemList = ajiaItemParamItemMapper.selectByExampleWithBLOBs(example);
		for (PdItemParamItem ajiaItemParamItem : itemList) {
			String paramData = ajiaItemParamItem.getParamData();
			List<PdItemParamData> paramList = JsonUtils.jsonToList(paramData, PdItemParamData.class);
			List<Params> list = paramList.get(0).getParams();
			boolean colorIsEquals = false, modelIsEquals = false;
			for (Params params : list) {
				String value = params.getValues().get(0);
				String key = params.getKey();
				if ("颜色".equals(key) && value.equals(color)) {
					colorIsEquals = true;
				}
				if ("型号".equals(key) && value.equals(model)) {
					modelIsEquals = true;
				}
			}

			if (colorIsEquals && modelIsEquals) {
				return ajiaItemParamItem.getItemId();
			}
		}
		return 0L;
	}

}
