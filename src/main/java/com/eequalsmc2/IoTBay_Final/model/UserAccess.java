package com.eequalsmc2.IoTBay_Final.model;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class UserAccess implements Serializable {
    private int id;
    private String type;
    private Date time;

    public UserAccess() {

    }
    public UserAccess(int id, String type, Date time) {
        this.id = id;
        this.time = time;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getTime() {
        return time;
    }

    public String getTime(String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(this.time);
    }

    public void setTime(Date time) {
        this.time = time;
    }
}
