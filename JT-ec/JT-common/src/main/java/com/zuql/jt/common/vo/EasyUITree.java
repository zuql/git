package com.zuql.jt.common.vo;

public class EasyUITree {
	
	private Long id;		//定义节点Id
	private String text;	//定义节点名称
	private String state;	//定义节点的状态 closed或者open
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	
	
	
}
