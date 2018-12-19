package com.zuql.jt.manage.service;

import com.zuql.jt.common.vo.EasyUITree;

import java.util.List;

public interface ItemCatService {

	List<EasyUITree> findItemCatList(Long parentId);
	//查询缓存
	List<EasyUITree> findCacheList(Long parentId);
}
