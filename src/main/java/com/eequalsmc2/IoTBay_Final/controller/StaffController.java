package com.eequalsmc2.IoTBay_Final.controller;

import com.eequalsmc2.IoTBay_Final.model.Staff;
import com.eequalsmc2.IoTBay_Final.model.dao.StaffManager;
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

@WebServlet("/staffServlet")
public class StaffController extends HttpServlet {

    private DB db;
    private StaffManager sm;
    SimpleDateFormat sdf;

    public StaffController() throws SQLException {
        super();
        db = new DB();
        sm = new StaffManager(db);
        sdf = new SimpleDateFormat("yyyy-MM-dd");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String q = req.getQueryString();
        String[] qs = q.split("=");
        if(qs[1].equalsIgnoreCase("register")) {
            handleRegister(req, resp);
        } else if(qs[1].equalsIgnoreCase("edit")) {
            try {
                handleEditProfile(req, resp);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        } else if(qs[1].equalsIgnoreCase("delete")) {
            handleDelete(req, resp);
        } else {
            // TODO: Unknown action
        }
    }

    private void handleDelete(HttpServletRequest req, HttpServletResponse resp) {

    }

    private void handleEditProfile(HttpServletRequest req, HttpServletResponse resp) throws ParseException {
        String idParameter = req.getParameter("id");
        if (!isNullOrEmpty(idParameter)) {
            // TODO:
        }
        int id = Integer.parseInt(req.getParameter("id"));
        String password = req.getParameter("password");
        if(!isValidPasswordFormat(password)) {

        }
        String firstName = req.getParameter("first_name");
        String lastName = req.getParameter("last_name");
        String gender = req.getParameter("gender");
        String phone = req.getParameter("phone");
        String dob = req.getParameter("dob");
        String privilege = req.getParameter("privilege");
        String position = req.getParameter("position");

        Staff user = null;
        try {
            user = sm.get(id);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        if (!isNullOrEmpty(password)) {
            user.setPassword(password);
        }
        if (!isNullOrEmpty(firstName)) {
            user.setFirstName(firstName);
        }
        if(!isNullOrEmpty(lastName)) {
            user.setLastName(lastName);
        }
        if (!isNullOrEmpty(gender)) {
            user.setGender(gender);
        }
        if (!isNullOrEmpty(phone)) {
            user.setPhone(phone);
        }
        if (!isNullOrEmpty(dob)) {
            user.setDob(dob);
        }
        if(!isNullOrEmpty(privilege)) {
            user.setPrivilege(Integer.parseInt(privilege));
        }
        if(!isNullOrEmpty(position)) {
            user.setPosition(position);
        }
        try {
            sm.update(user);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        // TODO....
    }

    private void handleRegister(HttpServletRequest req, HttpServletResponse resp) {
        int pk = 0;
        String email = req.getParameter("email");
        if (!isValidEmailFormat(email)) {

        }
        String password = req.getParameter("password");
        if(!isValidPasswordFormat(password)) {

        }
        String firstName = req.getParameter("first_name");
        String lastName = req.getParameter("last_name");
        String gender = req.getParameter("gender");
        String phone = req.getParameter("phone");
        String dob = req.getParameter("dob");
        int privilege = Integer.parseInt(req.getParameter("privilege"));
        String position = req.getParameter("position");

        try {
            pk = sm.create(email, firstName, lastName, gender, dob, phone, password,privilege, position);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            // handle error
        }
        // TODO
    }

    private boolean isValidPasswordFormat(String password) {
        // TODO:
        return true;
    }

    private boolean isValidEmailFormat(String userEmail) {
        // TODO
        return true;
    }

    private boolean isNullOrEmpty(String str) {
        if (str.isEmpty() || str == null) {
            return true;
        }
        return false;
    }
}
