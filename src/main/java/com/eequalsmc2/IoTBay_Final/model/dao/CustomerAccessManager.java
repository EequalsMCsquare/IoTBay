package com.eequalsmc2.IoTBay_Final.model.dao;

import com.eequalsmc2.IoTBay_Final.utils.DB;

import java.sql.Connection;
import java.sql.SQLException;

public class CustomerAccessManager {
    private DB db;

    public CustomerAccessManager(DB db) {
        this.db = db;
    }

    private Connection conn() throws SQLException {
        return db.connection();
    }
}
