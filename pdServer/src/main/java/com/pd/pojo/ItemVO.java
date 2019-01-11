package com.pd.pojo;
import java.util.*;
//���ﳵ�е�һ����Ʒ

import com.pd.pojo.paramData.Params;
public class ItemVO {
	private PdCartItem ajiaCartItem;
	//title,price
	private PdItem ajiaItem;
	List<Params> paramsList;

	public List<Params> getParamsList() {
		return paramsList;
	}
	public void setParamsList(List<Params> paramsList) {
		this.paramsList = paramsList;
	}
	public PdCartItem getAjiaCartItem() {
		return ajiaCartItem;
	}
	public void setAjiaCartItem(PdCartItem ajiaCartItem) {
		this.ajiaCartItem = ajiaCartItem;
	}
	public PdItem getAjiaItem() {
		return ajiaItem;
	}
	public void setAjiaItem(PdItem ajiaItem) {
		this.ajiaItem = ajiaItem;
	}
	
	
}
