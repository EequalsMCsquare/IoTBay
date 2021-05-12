package com.eequalsmc2.IoTBay_Final.model;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Customer extends User implements Serializable {

    public Customer() {
    }

    public Customer( String email, String password, String firstName, String lastName, String gender, Date dob,  String phoneNumber) {
        super.password = password;
        super.firstName = firstName;
        super.lastName = lastName;
        super.gender = gender;
        super.dob = dob;
        super.email = email;
        super.phone = phoneNumber;
    }

    public Customer(String email, String username, String password, String firstName, String lastName, String gender, String dob,  String phoneNumber) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        super.password = password;
        super.firstName = firstName;
        super.lastName = lastName;
        super.gender = gender;
        super.dob = sdf.parse(dob);
        super.email = email;
        super.phone = phoneNumber;
    }

    public ArrayList<String> getAddress() {
        return this.address;
    }

    public void addAddress(String address) {
        if(this.address.size() < 3) {
            this.address.add(address);
        }
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", gender='" + gender + '\'' +
                ", dob=" + dob +
                ", phone='" + phone + '\'' +
                '}';
    }
}
