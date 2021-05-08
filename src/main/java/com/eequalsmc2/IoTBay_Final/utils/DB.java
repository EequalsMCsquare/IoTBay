package com.eequalsmc2.IoTBay_Final.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DB {
    // !remember to modify this, when you clone my repo
    private String URL = "jdbc:derby:C:/Users/Reco/Desktop/IoTBay/db/";
    private String db = "APP";
    private String dbUser = "group42";
    private String dbPassword = "group42";
    private String dbDriver = "org.apache.derby.jdbc.EmbeddedDriver";

    private Connection conn;

    public DB() throws ClassNotFoundException, SQLException {
        Class.forName(this.dbDriver);
        this.conn = DriverManager.getConnection(URL+db, dbUser, dbPassword);
    }

    public Connection connection() {
        return this.conn;
    }

    public void dispose() throws SQLException {
        this.conn.close();
    }
}
