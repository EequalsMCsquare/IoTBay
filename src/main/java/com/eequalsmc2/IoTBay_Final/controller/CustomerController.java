package com.eequalsmc2.IoTBay_Final.controller;

import com.eequalsmc2.IoTBay_Final.model.Customer;
import com.eequalsmc2.IoTBay_Final.model.dao.CustomerAccessManager;
import com.eequalsmc2.IoTBay_Final.model.dao.CustomerManager;
import com.eequalsmc2.IoTBay_Final.utils.DB;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet("/customerServlet")
public class CustomerController extends HttpServlet {
    DB db;
    CustomerManager manager;
    CustomerAccessManager accessManager;
    SimpleDateFormat sdf;

    public CustomerController() throws SQLException {
        super();
        db = new DB();
        manager = new CustomerManager(db);
        sdf = new SimpleDateFormat("yyyy-MM-dd");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String q = req.getQueryString();
        String[] qs = q.split("=");
        if(qs[1].equalsIgnoreCase("register")) {
            handleRegister(req, resp);
        } else if(qs[1].equalsIgnoreCase("edit")) {
            handleEditProfile(req, resp);
        } else if (qs[1].equalsIgnoreCase("delete")) {
            handleDelete(req, resp);
        } else if (qs[1].equalsIgnoreCase("cancel")) {
            handleCancel(req, resp);
        }
    }

    private void handleCancel(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Customer user = (Customer) req.getSession().getAttribute("user");
        req.getSession().removeAttribute("user");
        try {
            manager.delete(user.getId());
        } catch (SQLException e) {
            resp.getWriter().println("fail to delete user!");
        }
        resp.sendRedirect("index.jsp");
    }

    // required parameter:
    // id: to be deleted user's id
    private void handleDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    private void handleRegister(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Customer customer = new Customer();
        int pk = 0;
        customer.setEmail(req.getParameter("email"));
        customer.setPassword(req.getParameter("password"));
        customer.setFirstName(req.getParameter("firstName"));
        customer.setLastName(req.getParameter("lastName"));
        customer.setGender(req.getParameter("gender"));
        customer.setPhone(req.getParameter("phone"));
        try {
            customer.setDob(sdf.parse(req.getParameter("dob")));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        try {
            pk = manager.create(customer);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            resp.getWriter().println(throwables.getMessage());
            return;
        }
        customer.setId(pk);
        try {
            accessManager.create(pk, "register");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        req.getSession().setAttribute("user", customer);
        resp.sendRedirect("index.jsp");
    }

    private boolean isNullOrEmpty(String str) {
        if (str.isEmpty() || str == null) {
            return true;
        }
        return false;
    }


    private void handleEditProfile(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Customer customer = (Customer) req.getSession().getAttribute("user");
        if (customer == null) {
            resp.sendRedirect("login.jsp");
        } else {
            String password = req.getParameter("password");
            String firstName = req.getParameter("firstName");
            String lastName = req.getParameter("lastName");
            String gender = req.getParameter("gender");
            String phone = req.getParameter("phone");
            String dob = req.getParameter("dob");

            if (!isNullOrEmpty(password)) {
                customer.setPassword(password);
            }
            if (!isNullOrEmpty(firstName)) {
                customer.setFirstName(firstName);
            }
            if(!isNullOrEmpty(lastName)) {
                customer.setLastName(lastName);
            }
            if (!isNullOrEmpty(gender)) {
                customer.setGender(gender);
            }
            if (!isNullOrEmpty(phone)) {
                customer.setPhone(phone);
            }
            if (!isNullOrEmpty(dob)) {
                try {
                    customer.setDob(sdf.parse(req.getParameter("dob")));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
            try {
                manager.update(customer);
                accessManager.create(customer.getId(), "update profile");
            } catch (SQLException throwables) {
                throwables.printStackTrace();
                resp.getWriter().println(throwables.getMessage());
                return;
            }
            resp.sendRedirect("customer_profile.jsp");
        }

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
