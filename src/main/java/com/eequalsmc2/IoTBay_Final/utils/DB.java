package com.eequalsmc2.IoTBay_Final.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DB {
    // !remember to modify this, when you clone my repo
    private String URL = "jdbc:mysql://47.95.118.206:3306/";
    private String db = "iotbay_db";
    private String dbUser = "group42";
    private String dbPassword = "uts.isd.group42";
    private String dbDriver = "com.mysql.cj.jdbc.Driver";

    private Connection conn;

    public DB() throws SQLException {
        try {
            Class.forName(this.dbDriver);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        this.conn = DriverManager.getConnection(URL+db, dbUser, dbPassword);
    }

    public Connection connection() {
        return this.conn;
    }

    public void dispose() throws SQLException {
        this.conn.close();
    }
}
