package com.eequalsmc2.IoTBay_Final.controller;

import com.eequalsmc2.IoTBay_Final.model.Customer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

@WebServlet("/registerServlet")
public class RegisterController extends HttpServlet {
    public RegisterController() {
        super();
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
        customer.setPhoneNumber(req.getParameter("phone"));
        customer.setAddress(req.getParameter("address"));
        customer.setPreferredLanguage(req.getParameter("language"));
        try {
            customer.setDateOfBirth(sdf.parse(req.getParameter("dob")));
        } catch (ParseException e) {
            System.out.println("nm$l");
        }
        req.getSession().setAttribute("user", customer);
        resp.sendRedirect("index.jsp");
    }
}
