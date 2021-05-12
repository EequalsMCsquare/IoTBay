package com.eequalsmc2.IoTBay_Final.controller;

import com.eequalsmc2.IoTBay_Final.utils.DB;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/staffServlet")
public class StaffController extends HttpServlet {

    private DB db;
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String q = req.getQueryString();
        String[] qs = q.split("=");
        if (qs[1].equalsIgnoreCase("login")) {
            handleLogin(req, resp);
        } else if(qs[1].equalsIgnoreCase("register")) {
            handleRegister(req, resp);
        } else if(qs[1].equalsIgnoreCase("edit")) {
            handleEditProfile(req, resp);
        } else if(qs[1].equalsIgnoreCase("delete")) {
            handleDelete(req, resp);
        } else {
            // TODO: Unknown action
        }
    }

    private void handleDelete(HttpServletRequest req, HttpServletResponse resp) {
    }

    private void handleEditProfile(HttpServletRequest req, HttpServletResponse resp) {
    }

    private void handleRegister(HttpServletRequest req, HttpServletResponse resp) {
    }

    private void handleLogin(HttpServletRequest req, HttpServletResponse resp) {
    }
}
