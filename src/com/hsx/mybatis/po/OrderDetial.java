package com.hsx.mybatis.po;

public class OrderDetial {

	private Integer id;
	private Integer ordersId;
	private Integer itemsId;
	private Integer itemsNums;

	private Items items;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getOrdersId() {
		return ordersId;
	}

	public void setOrdersId(Integer ordersId) {
		this.ordersId = ordersId;
	}

	public Integer getItemsId() {
		return itemsId;
	}

	public void setItemsId(Integer itemsId) {
		this.itemsId = itemsId;
	}

	public Integer getItemsNums() {
		return itemsNums;
	}

	public void setItemsNums(Integer itemsNums) {
		this.itemsNums = itemsNums;
	}

	public void setItems(Items items) {
		this.items = items;
	}
	
	public Items getItems() {
		return items;
	}
	
}
