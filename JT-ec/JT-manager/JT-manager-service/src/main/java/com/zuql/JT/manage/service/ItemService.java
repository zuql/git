package com.zuql.jt.manage.service;

import com.zuql.jt.common.vo.EasyUIResult;
import com.zuql.jt.manage.pojo.Item;
import com.zuql.jt.manage.pojo.ItemDesc;

public interface ItemService {

	EasyUIResult findItemByPage(Integer page, Integer rows);

	String findItemCatNameById(Long itemId);

	void saveItem(Item item, String desc);

	void updateItem(Item item, String desc);

	void updateState(Long[] ids, int state);

	ItemDesc findItemDescById(Long itemId);

	Item findItemById(Long itemId);
}
