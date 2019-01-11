package com.pd.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pd.mapper.PdShippingMapper;
import com.pd.pojo.PdShipping;
import com.pd.pojo.PdShippingExample;
import com.pd.service.AddressService;

//
@Service
public class AddressServiceImpl implements AddressService{

	//
	@Autowired
	PdShippingMapper ajiaShippingMapper;
	@Override
	public int insert(PdShipping ajiaShipping) throws Exception {
		//
		int row=ajiaShippingMapper.insert(ajiaShipping);
		return row;
	}
	@Override
	public List<PdShipping> selectByUserId(Long userId) throws Exception {
// from ajia_shipping where user_id=14 and status=1
		PdShippingExample example=new 
				PdShippingExample();
		
		PdShippingExample.Criteria criteria=
				example.or();
		criteria.andUserIdEqualTo(userId);
		criteria.andStatusEqualTo((byte)1);
		
		List<PdShipping> list=ajiaShippingMapper
				.selectByExample(example);		
		
		return list;
	}
	@Override
	public int setDefault(Long userId, Long addId) throws Exception {
//
		// update ajia_shipping set is_default=0
		//where user_=14;
		PdShippingExample example=
				new PdShippingExample();
		
		PdShippingExample.Criteria criteria=example.or();
		criteria.andUserIdEqualTo(userId);
		
		PdShipping ajiaShipping=new PdShipping();
		ajiaShipping.setIsDefault(false);
		//set receiverName=null,phone=null
		
		int row=ajiaShippingMapper
				.updateByExampleSelective(ajiaShipping, example);
		

		criteria.andAddIdEqualTo(addId);
		ajiaShipping.setIsDefault(true);
		ajiaShippingMapper
		.updateByExampleSelective(ajiaShipping, example);
		return row;
	}
	@Override
	public int delete(Long addId) throws Exception {
		int row = ajiaShippingMapper.deleteByPrimaryKey(addId);
		return row;
	}
	@Override
	public PdShipping getDefaultByUserId(Long userId) throws Exception {
PdShippingExample example=new PdShippingExample();
PdShippingExample.Criteria criteria=example.or();
criteria.andUserIdEqualTo(userId);
criteria.andIsDefaultEqualTo(true);

List<PdShipping> ajiaShippings=ajiaShippingMapper
.selectByExample(example);

	if (ajiaShippings!=null 
			&& ajiaShippings.size()>=1)
	{
		return ajiaShippings.get(0);
	}
		return null;
	}
}
