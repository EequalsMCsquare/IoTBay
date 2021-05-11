package com.eequalsmc2.IoTBay_Final.model.dao;

import com.eequalsmc2.IoTBay_Final.model.Staff;
import com.eequalsmc2.IoTBay_Final.utils.DB;

import java.sql.*;

public class StaffManager {

    private DB db;

    public StaffManager(DB db) {
        this.db = db;
    }

    private Connection conn() throws SQLException {
        return db.connection();
    }

    private boolean isStaffEmail(String email) {
        if (!email.contains("@")) {
            return false;
        }
        if (email.split("@")[1].equalsIgnoreCase("staff.iotbay.com")) {
            return true;
        }
        return false;
    }

    public int create(Staff staff) throws SQLException {
        String sql = "INSERT INTO staff (email, first_name, last_name, gender, dob, phone, password, privilege, position_id) " +
                "VALUES(?, ?, ?, ?, ?, ?, ?, ?, (SELECT id from staff_positions WHERE name = ?))";
        PreparedStatement st = conn().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        st.setString(1, staff.getEmail());
        st.setString(2, staff.getFirstName());
        st.setString(3, staff.getLastName());
        st.setString(4, staff.getGender());
        st.setDate(5, new Date(staff.getDob().getTime()));
        st.setString(6, staff.getPhone());
        st.setString(7, staff.getPassword());
        st.setInt(8, staff.getPrivilege());
        st.setString(9, staff.getPosition());
        st.executeUpdate();
        ResultSet rs = st.getGeneratedKeys();
        if(rs.next()) {
            return rs.getInt(1);
        }
        return 0;
    }

    public void update(Staff staff) throws SQLException {
        String sql = "UPDATE staff SET first_name = ?, last_name = ?, gender = ?, dob = ?, phone = ?, password = ?, privilege = ?, position_id = (SELECT id from staff_positions WHERE staff_positions.name = ?) WHERE email = ?";
        PreparedStatement st = conn().prepareStatement(sql);
        st.setString(1, staff.getFirstName());
        st.setString(2, staff.getLastName());
        st.setString(3, staff.getGender());
        st.setDate(4, new Date(staff.getDob().getTime()));
        st.setString(5, staff.getPhone());
        st.setString(6, staff.getPassword());
        st.setInt(7, staff.getPrivilege());
        st.setString(8, staff.getPosition());
        st.setString(9, staff.getEmail());
        st.execute();
    }

    public void delete(int id) throws SQLException {
        // delete user
        String sql = "DELETE FROM staff WHERE id = ?";
        PreparedStatement st = conn().prepareStatement(sql);
        st.setInt(1, id);
        st.execute();

        // delete user access
        sql = "DELETE FROM staff_access WHERE staff_id = ?";
        st = conn().prepareStatement(sql);
        st.setInt(1, id);
        st.execute();
    }

    public Staff get(String email) throws SQLException {
        if (!isStaffEmail(email)) {
            return null;
        }
        Staff staff;
        String sql = "SELECT staff.id, email, password, first_name, last_name, phone, gender, dob, privilege, sp.name as position FROM staff\n" +
                "INNER JOIN staff_positions sp on staff.position_id = ? WHERE staff.email = ?;";
        PreparedStatement st = conn().prepareStatement(sql);
        st.setString(1, email);
        ResultSet rs = st.executeQuery();
        if (rs.next()) {
            staff = new Staff();
            staff.setId(rs.getInt("id"));
            staff.setEmail(rs.getString("email"));
            staff.setFirstName(rs.getString("first_name"));
            staff.setLastName(rs.getString("last_name"));
            staff.setGender(rs.getString("gender"));
            staff.setDob(new java.util.Date( rs.getDate("dob").getTime()));
            staff.setPhone(rs.getString("phone"));
            staff.setPassword(rs.getString("password"));
            staff.setPrivilege(rs.getInt("privilege"));
            staff.setPosition(rs.getString("position"));
            return staff;
        }
        return null;
    }
}
