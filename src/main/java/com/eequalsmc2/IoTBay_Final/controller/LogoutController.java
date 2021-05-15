package com.eequalsmc2.IoTBay_Final.controller;

import com.eequalsmc2.IoTBay_Final.model.Customer;
import com.eequalsmc2.IoTBay_Final.model.Staff;
import com.eequalsmc2.IoTBay_Final.model.User;
import com.eequalsmc2.IoTBay_Final.model.dao.CustomerAccessManager;
import com.eequalsmc2.IoTBay_Final.model.dao.StaffAccessManager;
import com.eequalsmc2.IoTBay_Final.utils.DB;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/logoutServlet")
public class LogoutController extends HttpServlet {

    private DB db;
    private StaffAccessManager sam;
    private CustomerAccessManager cam;

    public LogoutController() throws SQLException {
        db = new DB();
        sam = new StaffAccessManager(db);
        cam = new CustomerAccessManager(db);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute("user");
        req.getSession().removeAttribute("user");
        if(user instanceof Staff) {
            try {
                // create staff access log
                sam.create(user.getId(), "logout");
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        } else if (user instanceof Customer) {
            try {
                // create customer access log
                cam.create(user.getId(), "logout");
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        resp.sendRedirect("index.jsp");
    }
}
