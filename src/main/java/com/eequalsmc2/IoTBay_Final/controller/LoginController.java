package com.eequalsmc2.IoTBay_Final.controller;

import com.eequalsmc2.IoTBay_Final.model.Customer;
import com.eequalsmc2.IoTBay_Final.model.Staff;
import com.eequalsmc2.IoTBay_Final.model.dao.CustomerAccessManager;
import com.eequalsmc2.IoTBay_Final.model.dao.CustomerManager;
import com.eequalsmc2.IoTBay_Final.model.dao.StaffAccessManager;
import com.eequalsmc2.IoTBay_Final.model.dao.StaffManager;
import com.eequalsmc2.IoTBay_Final.utils.DB;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

@WebServlet("/loginServlet")
public class LoginController extends HttpServlet {
    DB db;
    SimpleDateFormat sdf;
    CustomerManager customerManager;
    StaffManager staffManager;
    CustomerAccessManager customerAccessManager;
    StaffAccessManager staffAccessManager;

    public LoginController() throws SQLException {
        super();
        db = new DB();
        sdf = new SimpleDateFormat("yyyy-MM-dd");
        customerManager = new CustomerManager(db);
        staffManager = new StaffManager(db);
        customerAccessManager = new CustomerAccessManager(db);
        staffAccessManager = new StaffAccessManager(db);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        if(email == null || !isValidEmailFormat(email)) {
            resp.getWriter().println("Wrong Login Info");
            return;
        }
        if (password == null || !isValidPasswordFromat(password)) {
            resp.getWriter().println("Wrong Login Info");
            return;
        }
        if (email.split("@")[1].equalsIgnoreCase("staff.iotbay.com")) {
            handleStaffLogin(email, password, req, resp);
        } else {
            handleCustomerLogin(email, password, req, resp);
        }

    }

    private void handleCustomerLogin(String email, String password, HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Customer user = null;
        try {
            user = customerManager.get(email);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        if (user == null) {
            resp.getWriter().println("Wrong Login Info");
            return;
        }
        if (!user.getPassword().equals(password)) {
            resp.getWriter().println("Wrong Login Info");
            return;
        }
        req.getSession().setAttribute("user", user);
        // update access log
        try {
            customerAccessManager.create(user.getId(), "login");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        resp.sendRedirect("index.jsp");
    }

    private void handleStaffLogin(String email, String password, HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Staff user = null;
        try {
            user = staffManager.get(email);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        if (user == null) {
            resp.getWriter().println("Wrong Login Info");
            return;
        }
        if (!user.getPassword().equals(password)) {
            resp.getWriter().println("Wrong Login Info");
            return;
        }
        req.getSession().setAttribute("user", user);
        // update access log
        try {
            staffAccessManager.create(user.getId(), "login");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        resp.sendRedirect("index.jsp");
    }

    private boolean isValidEmailFormat(String email) {
        if (!email.contains("@")) {
            return false;
        }
        return true;
    }

    private boolean isValidPasswordFromat(String password) {
        if (password.length() < 6) {
            return false;
        }
        return true;
    }
}