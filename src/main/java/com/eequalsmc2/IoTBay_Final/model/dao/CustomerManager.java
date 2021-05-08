package com.eequalsmc2.IoTBay_Final.model.dao;

import com.eequalsmc2.IoTBay_Final.model.Customer;
import com.sun.corba.se.impl.io.TypeMismatchException;

import java.sql.*;
import java.util.Map;

public class CustomerManager {

    private Connection conn;

    public CustomerManager(Connection conn) throws SQLException {
        this.conn = conn;
    }

    public void create(Customer customer) throws SQLException {
        Statement st = conn.createStatement();
        String columns = "INSERT INTO APP.CUSTOMERS (USERNAME, FIRST_NAME, LAST_NAME, GENDER, DOB, EMAIL, PHONE, PASSWORD)";
        String values = String.format("VALUES(%s, %s, %s, %s, %s, %s, %s, %s)",
                customer.getUsername(), customer.getFirstName(),
                customer.getLastName(), customer.getGender(),
                customer.getDob(), customer.getEmail(),
                customer.getPhone(), customer.getPassword());
        st.executeUpdate(columns + values);
    }

    public void create(String username, String password, String firstName, String lastName, String gender, String dob, String email, String phoneNumber) throws SQLException {
        Statement st = conn.createStatement();
        String columns = "INSERT INTO APP.CUSTOMERS (USERNAME, FIRST_NAME, LAST_NAME, GENDER, DOB, EMAIL, PHONE, PASSWORD)";
        String values = String.format("VALUES(%s, %s, %s, %s, %s, %s, %s, %s)",
                username, firstName, lastName,
                gender, dob, email, phoneNumber,
                password);
        st.executeUpdate(columns + values);
    }

    public void update(Customer customer) throws SQLException {
        String sql = "UPDATE 'CUSTOMERS' SET 'first_name' = ?, 'last_name' = ?, gender = ?, dob = ?, email = ?, phone = ?, password = ? WHERE username = ?";
        PreparedStatement st = conn.prepareStatement(sql);
        st.setString(1, customer.getFirstName());
        st.setString(2, customer.getLastName());
        st.setString(3, customer.getGender());
        st.setDate(4, (Date)customer.getDob());
        st.setString(5, customer.getEmail());
        st.setString(6, customer.getPhone());
        st.setString(7, customer.getPassword());
        st.setString(8, customer.getUsername());
        st.execute();
    }

    public void delete(String username) throws SQLException {
        String sql = "DELETE FROM CUSTOMERS WHERE username = ?";
        PreparedStatement st = conn.prepareStatement(sql);
        st.setString(1, username);
        st.execute();
    }

    public Customer get(String username) throws SQLException {
        Customer customer;
        String sql = "SELECT * FROM CUSTOMERS WHERE 'username' = ?";
        PreparedStatement st = conn.prepareStatement(sql);
        st.setString(1, username);
        ResultSet rs = st.executeQuery();
        if (rs.next()) {
            customer = new Customer();
            customer.setId(rs.getInt("id"));
            customer.setUsername(username);
            customer.setFirstName(rs.getString("first_name"));
            customer.setLastName(rs.getString("last_name"));
            customer.setGender(rs.getString("gender"));
            customer.setEmail(rs.getString("email"));
            customer.setDob(rs.getDate("dob"));
            customer.setPhone(rs.getString("phone"));
            customer.setPassword(rs.getString("password"));

            // TODO: Address

            return customer;
        }
        return null;
    }
}
