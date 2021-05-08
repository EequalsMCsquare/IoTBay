package com.eequalsmc2.IoTBay_Final.model;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Customer implements Serializable {

    private int id;
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String gender;
    private Date dob;
    private String email;
    private String phone;
    private String[] address;

    public Customer() {
    }

    public Customer(String username, String password, String firstName, String lastName, String gender, Date dob, String email, String phoneNumber) {
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.dob = dob;
        this.email = email;
        this.phone = phoneNumber;
    }

    public Customer(String username, String password, String firstName, String lastName, String gender, String dob, String email, String phoneNumber) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.dob = sdf.parse(dob);
        this.email = email;
        this.phone = phoneNumber;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dateOfBirth) {
        this.dob = dateOfBirth;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phoneNumber) {
        this.phone= phoneNumber;
    }

    @Override
    public String toString() {
        return "Customer{" +
                ", username='" + username + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", gender='" + gender + '\'' +
                ", dateOfBirth=" + dob+
                ", email='" + email + '\'' +
                ", phoneNumber='" + phone+ '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
