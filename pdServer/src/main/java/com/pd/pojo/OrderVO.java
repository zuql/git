package com.pd.pojo;

import java.util.List;

public class OrderVO {
	PdOrder ajiaOrder;
	
	List<PdOrderItem> ajiaOrderItems;

	@Override
	public String toString() {
		return "OrderVO [ajiaOrder=" + ajiaOrder + ", ajiaOrderItems=" + ajiaOrderItems + "]";
	}

	public PdOrder getAjiaOrder() {
		return ajiaOrder;
	}

	public void setAjiaOrder(PdOrder ajiaOrder) {
		this.ajiaOrder = ajiaOrder;
	}

	public List<PdOrderItem> getAjiaOrderItems() {
		return ajiaOrderItems;
	}

	public void setAjiaOrderItems(List<PdOrderItem> ajiaOrderItems) {
		this.ajiaOrderItems = ajiaOrderItems;
	}

	

}
