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

@WebServlet("/loginServlet")
public class LoginController extends HttpServlet {
    DB db;
    CustomerManager mgr;

    public LoginController() throws SQLException, ClassNotFoundException {
        super();
        db = new DB();
        mgr = new CustomerManager(db.connection());
    }


    private boolean isValidEmail(String email) {
        // TODO:
        return true;
    }

    private boolean isValidPassword(String password) {
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
        if (__password == null || !isValidPassword(__password)) {
            resp.getWriter().println("Wrong Login Info");
            return;
        }
        Customer user = null;
        try {
            user = mgr.get(__email);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        if (user == null) {
            resp.getWriter().println("Wrong Login Info");
            return;
        }
        if (!user.getPassword().equals(__password)) {
            resp.getWriter().println("Wrong Login Info");
            return;
        }
        req.getSession().setAttribute("user", user);
        resp.sendRedirect("index.jsp");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }
}
