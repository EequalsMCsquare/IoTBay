package com.eequalsmc2.IoTBay_Final.controller;

import com.eequalsmc2.IoTBay_Final.model.CreditCard;
import com.eequalsmc2.IoTBay_Final.model.Customer;
import com.eequalsmc2.IoTBay_Final.model.dao.PaymentDetailManager;
import com.eequalsmc2.IoTBay_Final.utils.DB;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

@WebServlet("/paymentServlet")
public class PaymentDetailsController extends HttpServlet {
    DB db;
    PaymentDetailManager pdm;

    public PaymentDetailsController() throws SQLException, ClassNotFoundException {
        super();
        db = new DB();
        pdm = new PaymentDetailManager(db.connection());
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.service(req, resp);
        String action = req.getParameter("action");
        switch (action) {
            case "create":
                try {
                    create(req, resp);
                } catch (ParseException | SQLException e) {
                    e.printStackTrace();
                }
                break;
            case "view":
                view(req, resp);
                break;
            case "update":
                try {
                    update(req, resp);
                } catch (ParseException | SQLException e) {
                    e.printStackTrace();
                }
                break;
            case "delete":
                try {
                    delete(req, resp);
                } catch (SQLException throwable) {
                    throwable.printStackTrace();
                }
                break;
        }
    }

    protected void create(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, ParseException, SQLException {
        String name = req.getParameter("name");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = sdf.parse(req.getParameter(req.getParameter("date")));
        int cardNumber = Integer.parseInt(req.getParameter("cardNumber"));
        CreditCard card = new CreditCard(cardNumber, date, name);

        HttpSession session = req.getSession();
        Customer customer = (Customer) session.getAttribute("user");
        int customerId = customer.getId();
        pdm.create(card, customerId);
        super.doPost(req, resp);
    }

    protected void view(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    protected void update(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, ParseException, SQLException {
        String name = req.getParameter("name");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = sdf.parse(req.getParameter(req.getParameter("date")));
        int cardNumber = Integer.parseInt(req.getParameter("cardNumber"));
        CreditCard card = new CreditCard(cardNumber, date, name);

        HttpSession session = req.getSession();
        Customer customer = (Customer) session.getAttribute("user");
        int customerId = customer.getId();
        pdm.update(card, customerId);
    }

    protected void delete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, SQLException {
        int id=Integer.parseInt(req.getParameter("id"));
        pdm.delete(id);
    }
}
