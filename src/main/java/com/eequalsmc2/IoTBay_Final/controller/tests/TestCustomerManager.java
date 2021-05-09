package com.eequalsmc2.IoTBay_Final.controller.tests;

import com.eequalsmc2.IoTBay_Final.model.Customer;
import com.eequalsmc2.IoTBay_Final.model.dao.CustomerManager;
import com.eequalsmc2.IoTBay_Final.utils.DB;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Scanner;

public class TestCustomerManager {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        new TestCustomerManager();
    }

    private DB db;
    private Connection conn;
    private CustomerManager mgr;

    public TestCustomerManager() throws ClassNotFoundException, SQLException {
        db = new DB();
        mgr = new CustomerManager(db);
    }

    private void testCreate() throws SQLException, ParseException {
        System.out.println("Adding user to the datebase...");
        Customer customer = new Customer("eequalsmc2", "123123", "Xiao", "ZHONG", "Male", "1999/07/11", "zhongxiao0711@gmail.com", "13281111222");
        try {
            mgr.create(customer);
        } catch (SQLException e) {
            System.out.println("fail to create customer. ("+e.getMessage() + ")");
        }
    }
}
