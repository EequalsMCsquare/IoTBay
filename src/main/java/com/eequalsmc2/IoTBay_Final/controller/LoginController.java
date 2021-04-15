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

@WebServlet("/loginServlet")
public class LoginController extends HttpServlet {
    public LoginController() {
        super();
    }

    private boolean isValidUsername(String username) {
        // TODO:
        return true;
    }

    private boolean isValidEmail(String email) {
        // TODO:
        return true;
    }
    private boolean isValidPhone(String phone) {
        // TODO:
        return true;
    }
    private boolean isValidPassword(String email, String password) {
        // TODO:
        return true;
    }
    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String __email = req.getParameter("email");
        if (__email == null || !isValidEmail(__email)) {
            resp.getWriter().println("Wrong Login Info");
            return;
        }
        String __password = req.getParameter("password");
        if (__password == null || !isValidPassword(__email, __password)) {
            resp.getWriter().println("Wrong Login Info");
            return;
        }
        Customer user = new Customer();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        user.setEmail(__email);
        user.setPassword(__password);
        user.setUsername("TestUser");
        user.setFirstName("TestUser");
        user.setLastName("TestUser");
        user.setGender("Male");
        user.setId(0);
        user.setAddress("Sydney");
        try {
            user.setDateOfBirth(sdf.parse("1999-07-11"));
        } catch (ParseException e) {
            user.setDateOfBirth(null);
        }
        user.setPreferredLanguage("English");
        user.setPhoneNumber("123123123");
        req.getSession().setAttribute("user", user);
        resp.sendRedirect("index.jsp");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }
}
