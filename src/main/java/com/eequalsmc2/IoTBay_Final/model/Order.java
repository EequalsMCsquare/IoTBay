package com.eequalsmc2.IoTBay_Final.model;

import java.util.Date;
import java.util.List;

public class Order {
   private Integer id ;
   private Integer customerId;
   private Integer orderItemsId;
   private Double totalPrice;
   private Date date;
   private Integer status;
   private Integer shipmentId;
   private List<OrderItems> orderItems;
public Integer getId() {
	return id;
}
public void setId(Integer id) {
	this.id = id;
}
public Integer getCustomerId() {
	return customerId;
}
public void setCustomerId(Integer customerId) {
	this.customerId = customerId;
}
public Integer getOrderItemsId() {
	return orderItemsId;
}
public void setOrderItemsId(Integer orderItemsId) {
	this.orderItemsId = orderItemsId;
}
public Double getTotalPrice() {
	return totalPrice;
}
public void setTotalPrice(Double totalPrice) {
	this.totalPrice = totalPrice;
}
public Date getDate() {
	return date;
}
public void setDate(Date date) {
	this.date = date;
}
public Integer getStatus() {
	return status;
}
public void setStatus(Integer status) {
	this.status = status;
}
public Integer getShipmentId() {
	return shipmentId;
}
public void setShipmentId(Integer shipmentId) {
	this.shipmentId = shipmentId;
}
public List<OrderItems> getOrderItems() {
	return orderItems;
}
public void setOrderItems(List<OrderItems> orderItems) {
	this.orderItems = orderItems;
}
public Order(Integer id, Integer customerId, Integer orderItemsId, Double totalPrice, Date date, Integer status,
             Integer shipmentId, List<OrderItems> orderItems) {
	super();
	this.id = id;
	this.customerId = customerId;
	this.orderItemsId = orderItemsId;
	this.totalPrice = totalPrice;
	this.date = date;
	this.status = status;
	this.shipmentId = shipmentId;
	this.orderItems = orderItems;
}
public Order(Integer id, Integer customerId, Integer orderItemsId, Double totalPrice, Date date, Integer status,
             Integer shipmentId) {
	super();
	this.id = id;
	this.customerId = customerId;
	this.orderItemsId = orderItemsId;
	this.totalPrice = totalPrice;
	this.date = date;
	this.status = status;
	this.shipmentId = shipmentId;
}
   
public Order() {
	
}
@Override
public String toString() {
	return "Order [id=" + id + ", customerId=" + customerId + ", orderItemsId=" + orderItemsId + ", totalPrice="
			+ totalPrice + ", date=" + date + ", status=" + status + ", shipmentId=" + shipmentId + ", orderItems="
			+ orderItems + "]";
}
   
}
