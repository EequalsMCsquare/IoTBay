package com.eequalsmc2.IoTBay_Final.model;

public class OrderItems {
	private Integer id; 
	private Integer productId;
	private Integer amount;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getProductId() {
		return productId;
	}
	public void setProductId(Integer productId) {
		this.productId = productId;
	}
	public Integer getAmount() {
		return amount;
	}
	public void setAmount(Integer amount) {
		this.amount = amount;
	}
	public OrderItems(Integer id, Integer productId, Integer amount) {
		super();
		this.id = id;
		this.productId = productId;
		this.amount = amount;
	}
	
}
