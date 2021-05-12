package com.eequalsmc2.IoTBay_Final.model.dao;

import com.eequalsmc2.IoTBay_Final.model.UserAccess;
import com.eequalsmc2.IoTBay_Final.utils.DB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

public class StaffAccessManager {
    private DB db;

    public StaffAccessManager(DB db) {
        this.db = db;
    }

    private Connection conn() throws SQLException {
        return db.connection();
    }

    public void create(int staff_id, String type) throws SQLException {
        String sql = "INSERT INTO staff_access(staff_id, staff_access.type, staff_access.time) VALUE(?, ?, now())";
        PreparedStatement st = conn().prepareStatement(sql);
        st.setInt(1, staff_id);
        st.setString(2, type);
        st.execute();
    }

    public void delete(int id) throws SQLException {
        String sql = "DELETE FROM staff_access WHERE id = ?";
        PreparedStatement st = conn().prepareStatement(sql);
        st.setInt(1, id);
        st.execute();
    }

    public ArrayList<UserAccess> get(int staff_id) throws SQLException {
        ArrayList<UserAccess> accesses = new ArrayList<>();
        String sql = "SELECT * FROM staff_access WHERE staff_id = ?";
        PreparedStatement st = conn().prepareStatement(sql);
        st.setInt(1, staff_id);
        ResultSet rs = st.executeQuery();
        while (rs.next()) {
            accesses.add(new UserAccess(rs.getInt("id"),rs.getString("type"), new Date(rs.getTimestamp("time").getTime())));
        }
        return accesses;
    }

    public ArrayList<UserAccess> getBetween(int staff_id, Date start, Date end) throws SQLException {
        ArrayList<UserAccess> accesses = new ArrayList<>();
        String sql = "SELECT * FROM staff_access WHERE staff_id = ? AND time >= ? AND time <= ?";
        PreparedStatement st = conn().prepareStatement(sql);
        st.setInt(1, staff_id);
        st.setDate(2, new java.sql.Date(start.getTime()));
        st.setDate(3, new java.sql.Date(end.getTime()));
        ResultSet rs = st.executeQuery();
        while(rs.next()) {
            accesses.add(new UserAccess(rs.getInt("id"), rs.getString("type"), new Date(rs.getTimestamp("time").getTime())));
        }
        return accesses;
    }
}
