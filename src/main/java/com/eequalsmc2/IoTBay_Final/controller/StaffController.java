package com.eequalsmc2.IoTBay_Final.controller;

import com.eequalsmc2.IoTBay_Final.model.Staff;
import com.eequalsmc2.IoTBay_Final.model.dao.StaffAccessManager;
import com.eequalsmc2.IoTBay_Final.model.dao.StaffManager;
import com.eequalsmc2.IoTBay_Final.utils.DB;
import com.eequalsmc2.IoTBay_Final.utils.Helper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

@WebServlet("/staffServlet")
public class StaffController extends HttpServlet {

    private DB db;
    private StaffManager sm;
    private StaffAccessManager sam;
    SimpleDateFormat sdf;

    public StaffController() throws SQLException {
        super();
        db = new DB();
        sm = new StaffManager(db);
        sam = new StaffAccessManager(db);
        sdf = new SimpleDateFormat("yyyy-MM-dd");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String q = req.getQueryString();
        String[] qs = q.split("&");
        if (qs.length == 1) {
            String action = qs[0].split("=")[1];
            if (action.equalsIgnoreCase("register")) {
                handleRegister(req, resp);
            }
            else if (action.equalsIgnoreCase("cancel")) {
                handleCancel(req, resp);
            }
        } else if (qs.length == 2) {
            String action = qs[0].split("=")[1];
            if(action.equalsIgnoreCase("edit")) {
                int id = Integer.parseInt(qs[1].split("=")[1]);
                req.setAttribute("id", id);
                try {
                    handleEditProfile(req, resp);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            } else if(action.equalsIgnoreCase("modify")) {
                int id = Integer.parseInt(qs[1].split("=")[1]);
                req.setAttribute("id", id);
                try {
                    handleModifyProfile(req, resp);
                } catch (ParseException e) {
                    e.printStackTrace();
                }

            }
        }
    }



    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String q = req.getQueryString();
        String[] qs = q.split("&");
        if (qs.length == 2) {
            String action = qs[0].split("=")[1];
            if (action.equalsIgnoreCase("delete")) {
                int id = Integer.parseInt(qs[1].split("=")[1]);
                req.setAttribute("id", id);
                handleDelete(req, resp);
            } else if (action.equalsIgnoreCase("activate")) {
                int id = Integer.parseInt(qs[1].split("=")[1]);
                try {
                    sm.activate(id);
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                resp.sendRedirect("staff_manage.jsp");
            } else if (action.equalsIgnoreCase("deactivate")) {
                int id = Integer.parseInt(qs[1].split("=")[1]);
                try {
                    sm.deactivate(id);
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                resp.sendRedirect("staff_manage.jsp");
            }
        }
    }

    private void handleCancel(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Staff user = (Staff) req.getSession().getAttribute("user");
        req.getSession().removeAttribute("user");
        try {
            sm.delete(user.getId());
        } catch (SQLException throwables) {
            Helper.alert(resp.getWriter(), "fail to cancel user");
        }
        resp.sendRedirect("index.jsp");
    }

    // required attribute:
        // id: to be deleted user's id
    private void handleDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int id = (int) req.getAttribute("id");
        try {
            sm.delete(id);
        } catch (SQLException throwables) {
            Helper.alert(resp.getWriter(), "fail to delete user");
        }
        resp.sendRedirect("staff_manage.jsp");
    }

    private void handleEditProfile(HttpServletRequest req, HttpServletResponse resp) throws ParseException, IOException {
        int id = (int) req.getAttribute("id");
        String password = req.getParameter("password");
        if(!isValidPasswordFormat(password)) {
            Helper.alert(resp.getWriter(), "Password length must >= 6");
        }
        String firstName = req.getParameter("firstName");
        String lastName = req.getParameter("lastName");
        String gender = req.getParameter("gender");
        String phone = req.getParameter("phone");
        String dob = req.getParameter("dob");
        String privilege = req.getParameter("privilege");
        String position = req.getParameter("position");

        Staff user = null;
        try {
            user = sm.get(id);
        } catch (SQLException throwables) {
            Helper.alert(resp.getWriter(), "User does not exist!");
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
            sam.create(user.getId(), "edit profile");
        } catch (SQLException throwables) {
            Helper.alert(resp.getWriter(), "fail to update info");
        }
        resp.sendRedirect("admin.jsp");
    }

    private void handleModifyProfile(HttpServletRequest req, HttpServletResponse resp) throws IOException, ParseException {
        int id = (int) req.getAttribute("id");
        String password = req.getParameter("password");
        if(!isValidPasswordFormat(password)) {
            Helper.alert(resp.getWriter(), "Invalid password format!");
        }
        String firstName = req.getParameter("firstName");
        String lastName = req.getParameter("lastName");
        String gender = req.getParameter("gender");
        String phone = req.getParameter("phone");
        String dob = req.getParameter("dob");
        String privilege = req.getParameter("privilege");
        String position = req.getParameter("position");

        Staff user = new Staff();
        user.setId(id);
        user.setPassword(password);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setGender(gender);
        user.setPhone(phone);
        user.setDob(dob);
        user.setPrivilege(Integer.parseInt(privilege));
        user.setPosition(position);


        try {
            sm.update(user);
            sam.create(user.getId(), "edit profile");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            Helper.alert(resp.getWriter(), "fail to modify user!");
        }
        resp.sendRedirect("staff_manage.jsp");
    }


    private void handleRegister(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int pk = 0;
        String email = req.getParameter("email") + "@staff.iotbay.com";
        if (!isValidEmailFormat(email)) {
            Helper.alert(resp.getWriter(), "invalid email format");
        }
        String password = req.getParameter("password");
        if(!isValidPasswordFormat(password)) {
            Helper.alert(resp.getWriter(), "invalid password format");
        }
        String firstName = req.getParameter("firstName");
        String lastName = req.getParameter("lastName");
        String gender = req.getParameter("gender");
        String phone = req.getParameter("phone");
        String dob = req.getParameter("dob");
        int privilege = Integer.parseInt(req.getParameter("privilege"));
        String position = req.getParameter("position");

        try {
            pk = sm.create(email, firstName, lastName, gender, dob, phone, password, privilege, position);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            Helper.alert(resp.getWriter(), "fail to create user");
            return;
        }
        resp.sendRedirect("staff_manage.jsp");
    }

    private boolean isValidPasswordFormat(String password) {
        if (password.length() < 6) {
            return false;
        }
        return true;
    }

    private boolean isValidEmailFormat(String userEmail) {
        // TODO
        return true;
    }

    private boolean isNullOrEmpty(String str) {
        if (str == null || str.isEmpty()) {
            return true;
        }
        return false;
    }
}
