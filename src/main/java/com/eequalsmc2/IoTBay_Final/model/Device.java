package com.eequalsmc2.IoTBay_Final.model;

public class Device {
	private Integer id ;
	private String deviceName ;
	private String type;
	private Integer unitPrice;
	private Integer quantity;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getDeviceName() {
		return deviceName;
	}
	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Integer getUnitPrice() {
		return unitPrice;
	}
	public void setUnitPrice(Integer unitPrice) {
		this.unitPrice = unitPrice;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	@Override
	public String toString() {
		return "Device [id=" + id + ", deviceName=" + deviceName + ", type=" + type + ", unitPrice=" + unitPrice
				+ ", quantity=" + quantity + "]";
	}
	public Device(Integer id, String deviceName, String type, Integer unitPrice, Integer quantity) {
		super();
		this.id = id;
		this.deviceName = deviceName;
		this.type = type;
		this.unitPrice = unitPrice;
		this.quantity = quantity;
	}
	
	public Device() {
		
	}
	
	public Device(String deviceName, String type, Integer unitPrice, Integer quantity) {
		this.deviceName = deviceName;
		this.type = type;
		this.unitPrice = unitPrice;
		this.quantity = quantity;
	}
	
}
