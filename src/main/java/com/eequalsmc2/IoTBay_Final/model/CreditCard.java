package com.eequalsmc2.IoTBay_Final.model;

import java.util.Date;

public class CreditCard {
    public CreditCard(int cardNumber, Date date, String name) {
        this.cardNumber = cardNumber;
        this.date = date;
        this.name = name;
    }

    private int id;
    private int cardNumber;
    private Date date;
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CreditCard(){}
    public int getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(int cardNumber) {
        this.cardNumber = cardNumber;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

}
