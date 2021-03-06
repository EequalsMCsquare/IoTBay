package com.eequalsmc2.IoTBay_Final.controller;

import com.eequalsmc2.IoTBay_Final.model.Payment;
import com.eequalsmc2.IoTBay_Final.model.Customer;
import com.eequalsmc2.IoTBay_Final.model.PaymentDetail;
import com.eequalsmc2.IoTBay_Final.model.dao.PaymentManager;
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
import java.util.List;

@WebServlet("/paymentServlet")
public class PaymentController extends HttpServlet {
    DB db;
    PaymentManager pdm;
    PaymentDetailManager pm;
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    public PaymentController() throws SQLException, ClassNotFoundException {
        super();
        db = new DB();
        pdm = new PaymentManager(db);
        pm=new PaymentDetailManager(db);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if("view".equals(action)){
            try {
                view(req, resp);
            } catch (ParseException | SQLException e) {
                e.printStackTrace();
            }
        }else{
            try {
                delete(req, resp);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        switch (action) {
            case "add":
                try {
                    create(req, resp);
                } catch (ParseException | SQLException e) {
                    e.printStackTrace();
                }
                break;
            case "edit":
                try {
                    update(req, resp);
                } catch (ParseException | SQLException e) {
                    e.printStackTrace();
                }
                break;
            default:
        }
    }

    protected void create(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, ParseException, SQLException {
        Date date = sdf.parse(req.getParameter("expireDate"));
        String name = req.getParameter("name");
        int cardNumber = Integer.parseInt(req.getParameter("cardNumber"));
        Payment card = new Payment(cardNumber, date, name);
        HttpSession session = req.getSession();
        Customer customer = (Customer) session.getAttribute("user");
        int customerId = customer.getId();
        pdm.create(card, customerId);
        resp.sendRedirect("paymentServlet?action=view");
    }

    protected void view(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, ParseException, SQLException {
        HttpSession session = req.getSession();
        Customer customer = (Customer) session.getAttribute("user");
        int customerId = customer.getId();
        req.getSession().setAttribute("payments", pdm.listById(customerId));
        req.getRequestDispatcher("payment.jsp").forward(req, resp);
    }

    protected void update(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, ParseException, SQLException {
        Payment card= new Payment();
        String name = req.getParameter("name");
        Date date = sdf.parse(req.getParameter("expireDate"));
        int cardNumber = Integer.parseInt(req.getParameter("cardNumber"));
        card.setId(Integer.parseInt(req.getParameter("id")));
        card.setName(name);
        card.setDate(date);
        card.setCardNumber(cardNumber);
        HttpSession session = req.getSession();
        Customer customer = (Customer) session.getAttribute("user");
        int customerId = customer.getId();
        pdm.update(card, customerId);
        resp.sendRedirect("paymentServlet?action=view");
    }

    protected void delete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, SQLException {
        int id = Integer.parseInt(req.getParameter("id"));
        pdm.delete(id);
        resp.sendRedirect("paymentServlet?action=view");
    }
}
