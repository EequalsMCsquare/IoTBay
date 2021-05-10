package com.eequalsmc2.IoTBay_Final.model;

import java.util.Date;

public class Payment {
    private int id;
    private int order_id;
    private float amount;
    private String status;
    private Date date;
    private CreditCard card;
    private int paymentDetailId;

    public int getPaymentDetailId() {
        return paymentDetailId;
    }

    public void setPaymentDetailId(int paymentDetailId) {
        this.paymentDetailId = paymentDetailId;
    }

    public CreditCard getCard() {
        return card;
    }

    public void setCard(CreditCard card) {
        this.card = card;
    }

    public Payment(int id, int order_id, float amount, String status, Date date,CreditCard card) {
        this.id = id;
        this.order_id = order_id;
        this.amount = amount;
        this.status = status;
        this.date = date;
        this.card=card;
    }

    public Payment(){}
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getOrder_id() {
        return order_id;
    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

}
