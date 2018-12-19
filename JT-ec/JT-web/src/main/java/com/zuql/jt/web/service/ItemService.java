package com.zuql.jt.web.service;


import com.zuql.jt.manage.pojo.Item;
import com.zuql.jt.manage.pojo.ItemDesc;

public interface ItemService {

	Item findItemById(Long itemId);

	ItemDesc findItemDescById(Long itemId);

}
