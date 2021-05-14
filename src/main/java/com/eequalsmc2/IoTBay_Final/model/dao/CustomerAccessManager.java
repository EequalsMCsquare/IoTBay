package com.eequalsmc2.IoTBay_Final.model.dao;

import com.eequalsmc2.IoTBay_Final.model.UserAccess;
import com.eequalsmc2.IoTBay_Final.utils.DB;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;

public class CustomerAccessManager {
    private DB db;

    public CustomerAccessManager(DB db) {
        this.db = db;
    }

    private Connection conn() throws SQLException {
        return db.connection();
    }

    public void create(int customer_id, String type) throws  SQLException {
        String sql = "INSERT INTO customer_access(customer_id, customer_access.type, customer_access.time) " +
                "VALUE(?, ?, ?)";
        PreparedStatement st = conn().prepareStatement(sql);
        st.setInt(1, customer_id);
        st.setString(2, type);
        st.setTimestamp(3, new Timestamp(new Date().getTime()));
        st.execute();
    }
    
    public void delete(int id) throws SQLException {
        String sql = "DELETE FROM customer_access WHERE id = ?";
        PreparedStatement st = conn().prepareStatement(sql);
        st.setInt(1, id);
        st.execute();
    }
    
    public ArrayList<UserAccess> get(int customer_id) throws SQLException {
        ArrayList<UserAccess> accesses = new ArrayList<>();
        String sql = "SELECT * FROM customer_access WHERE customer_id = ?";
        PreparedStatement st = conn().prepareStatement(sql);
        st.setInt(1, customer_id);
        ResultSet rs = st.executeQuery();
        while (rs.next()) {
            accesses.add(new UserAccess(rs.getInt("id"),rs.getString("type"), new Date(rs.getTimestamp("time").getTime())));
        }
        return accesses;
    }

//    public ArrayList<UserAccess> getBetween(int customer_id, Date start, Date end) throws SQLException {
//        ArrayList<UserAccess> accesses = new ArrayList<>();
//        String sql = "SELECT * FROM customer_access WHERE customer_id = ? AND 'time' >= ? AND 'time' <= ?";
//        PreparedStatement st = conn().prepareStatement(sql);
//        st.setInt(1, customer_id);
//        st.setTimestamp(2, new java.sql.Timestamp(start.getTime()));
//        st.setTimestamp(3, new java.sql.Timestamp(end.getTime()));
//        ResultSet rs = st.executeQuery();
//        while(rs.next()) {
//            accesses.add(new UserAccess(rs.getInt("id"), rs.getString("type"), new Date(rs.getTimestamp("time").getTime())));
//        }
//        return accesses;
//    }
}
