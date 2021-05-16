package com.eequalsmc2.IoTBay_Final.model;

import java.io.Serializable;
import java.util.Date;

public class Staff extends User implements Serializable {
    private int privilege;
    private String position;
    private boolean activated;

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

    public boolean isActivated() {
        return activated;
    }

    public void setActivated(boolean activated) {
        this.activated = activated;
    }

    @Override
    public String toString() {
        return "Staff{" +
                "privilege=" + privilege +
                ", position='" + position + '\'' +
                ", activated=" + activated +
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
