package com.eequalsmc2.IoTBay_Final.model.dao;

import com.eequalsmc2.IoTBay_Final.model.Customer;
import com.eequalsmc2.IoTBay_Final.utils.DB;
import com.sun.corba.se.impl.io.TypeMismatchException;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Map;

public class CustomerManager {

    private DB db;

    public CustomerManager(DB db) {
        this.db = db;
    }

    private Connection conn() throws SQLException {
        return db.connection();
    }

    public int create(Customer customer) throws SQLException {
        String sql = "INSERT INTO customers (email, first_name, last_name, gender, dob, phone, password) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?);";
        PreparedStatement st = conn().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        st.setString(1, customer.getEmail());
        st.setString(2, customer.getFirstName());
        st.setString(3, customer.getLastName());
        st.setString(4, customer.getGender());
        st.setDate(5, new Date(customer.getDob().getTime()));
        st.setString(6, customer.getPhone());
        st.setString(7, customer.getPassword());
        st.executeUpdate();
        ResultSet rs = st.getGeneratedKeys();
        if(rs.next()) {
            return rs.getInt(1);
        }
        return 0;
    }

    public int create(String email, String firstName, String lastName, String gender, java.util.Date dob, String phone, String password) throws SQLException {
        String sql = "INSERT INTO customers (email, first_name, last_name, gender, dob, phone, password) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?);";
        PreparedStatement st = conn().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        st.setString(1, email);
        st.setString(2, firstName);
        st.setString(3, lastName);
        st.setString(4, gender);
        st.setDate(5, new Date(dob.getTime()));
        st.setString(6, phone);
        st.setString(7, password);
        st.executeUpdate();
        ResultSet rs = st.getGeneratedKeys();
        if(rs.next()) {
            return rs.getInt(1);
        }
        return 0;
    }

    public void update(Customer customer) throws SQLException {
        String sql = "UPDATE customers SET first_name = ?, last_name = ?, gender = ?, dob = ?, phone = ?, password = ? WHERE email = ?";
        PreparedStatement st = conn().prepareStatement(sql);
        st.setString(1, customer.getFirstName());
        st.setString(2, customer.getLastName());
        st.setString(3, customer.getGender());
        st.setDate(4, new Date(customer.getDob().getTime()));
        st.setString(5, customer.getPhone());
        st.setString(6, customer.getPassword());
        st.setString(7, customer.getEmail());
        st.execute();
    }

    public void delete(int id) throws SQLException {
        // delete user
        String sql = "DELETE FROM customers WHERE id = ?";
        PreparedStatement st = conn().prepareStatement(sql);
        st.setInt(1, id);
        st.execute();

        // delete user address
        sql = "DELETE FROM customer_address WHERE customer_id = ?";
        st = conn().prepareStatement(sql);
        st.setInt(1, id);
        st.execute();

        // delete user access log
        sql = "DELETE FROM customer_access WHERE customer_id = ?";
        st = conn().prepareStatement(sql);
        st.setInt(1, id);
        st.execute();
    }

    public Customer get(String email) throws SQLException {
        Customer customer;
        String sql = "SELECT * FROM customers WHERE email = ?";
        PreparedStatement st = conn().prepareStatement(sql);
        st.setString(1, email);
        ResultSet rs = st.executeQuery();
        if (rs.next()) {
            customer = new Customer();
            customer.setId(rs.getInt("id"));
            customer.setEmail(email);
            customer.setFirstName(rs.getString("first_name"));
            customer.setLastName(rs.getString("last_name"));
            customer.setGender(rs.getString("gender"));
            customer.setDob( new java.util.Date(rs.getDate("dob").getTime()));
            customer.setPhone(rs.getString("phone"));
            customer.setPassword(rs.getString("password"));
            return customer;
        }
        return null;
    }
}
