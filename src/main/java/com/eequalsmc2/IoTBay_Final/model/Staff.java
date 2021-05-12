package com.eequalsmc2.IoTBay_Final.model;

import java.io.Serializable;
import java.util.Date;

public class Staff extends User implements Serializable {
    private int privilege;
    private String position;

    public Staff() {}

    public int getPrivilege() {
        return privilege;
    }

    public void setPrivilege(int privilege) {
        this.privilege = privilege;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    @Override
    public String toString() {
        return "Staff{" +
                "privilege=" + privilege +
                ", position='" + position + '\'' +
                ", id=" + id +
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
