package com.zuql.taotao.mapper;

import com.zuql.taotao.pojo.TbItemDesc;

public interface TbItemDescMapper {
    int deleteByPrimaryKey(Long itemId);

    int insert(TbItemDesc record);

    int insertSelective(TbItemDesc record);

    TbItemDesc selectByPrimaryKey(Long itemId);

    int updateByPrimaryKeySelective(TbItemDesc record);

    int updateByPrimaryKey(TbItemDesc record);
}