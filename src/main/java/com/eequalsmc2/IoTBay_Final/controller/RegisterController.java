package com.eequalsmc2.IoTBay_Final.controller;

import com.eequalsmc2.IoTBay_Final.model.Customer;
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

@WebServlet("/registerServlet")
public class RegisterController extends HttpServlet {
    private DB db;
    private CustomerManager mgr;
    public RegisterController() throws SQLException, ClassNotFoundException {
        super();
        db = new DB();
        mgr = new CustomerManager(db.connection());
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Customer customer = new Customer();
        customer.setUsername(req.getParameter("username"));
        customer.setPassword(req.getParameter("password"));
        customer.setFirstName(req.getParameter("firstName"));
        customer.setLastName(req.getParameter("lastName"));
        customer.setGender(req.getParameter("gender"));
        customer.setEmail(req.getParameter("email"));
        customer.setPhone(req.getParameter("phone"));
        try {
            customer.setDob(sdf.parse(req.getParameter("dob")));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        try {
            mgr.create(customer);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            // TODO: Redirect to Error Page
            return;
        }
        req.getSession().setAttribute("user", customer);
        resp.sendRedirect("index.jsp");
    }
}
