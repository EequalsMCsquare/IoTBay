package com.eequalsmc2.IoTBay_Final.controller;

import com.eequalsmc2.IoTBay_Final.model.CreditCard;
import com.eequalsmc2.IoTBay_Final.model.Customer;
import com.eequalsmc2.IoTBay_Final.model.Payment;
import com.eequalsmc2.IoTBay_Final.model.dao.PaymentDetailManager;
import com.eequalsmc2.IoTBay_Final.model.dao.PaymentManager;
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
    PaymentDetailManager pdm;
    PaymentManager pm;
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    public PaymentController() throws SQLException, ClassNotFoundException {
        super();
        db = new DB();
        pdm = new PaymentDetailManager(db.connection());
        pm=new PaymentManager(db.connection());
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
                try {
                    view(req, resp);
                } catch (ParseException e) {
                    e.printStackTrace();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
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
            case "history":
                try {
                    viewHistory(req,resp);
                } catch (ParseException e) {
                    e.printStackTrace();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
        }
    }

    protected void create(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, ParseException, SQLException {
        Date date = sdf.parse(req.getParameter(req.getParameter("date")));
        String name = req.getParameter("name");
        int cardNumber = Integer.parseInt(req.getParameter("cardNumber"));
        CreditCard card = new CreditCard(cardNumber, date, name);
        HttpSession session = req.getSession();
        Customer customer = (Customer) session.getAttribute("user");
        int customerId = customer.getId();
        int pd_id = pdm.create(card, customerId);

        Payment payment=new Payment();
        payment.setCard(card);
        payment.setStatus("pending");
        payment.setDate(date);
        payment.setOrder_id(Integer.parseInt(req.getParameter("orderId")));
        payment.setPaymentDetailId(pd_id);
        payment.setAmount(9.99f);

        pm.create(payment);

        req.getSession().setAttribute("payment",payment);
    }

    protected void view(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, ParseException, SQLException {
        int id=Integer.parseInt(req.getParameter("id"));
        Date date = sdf.parse(req.getParameter(req.getParameter("date")));
        Payment payment=pm.get(id,date);

        req.getSession().setAttribute("payment",payment);
    }

    protected void update(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, ParseException, SQLException {
        Payment payment=(Payment)req.getSession().getAttribute("payment");
        CreditCard card=pdm.get(payment.getPaymentDetailId());

        String name = req.getParameter("name");
        Date date = sdf.parse(req.getParameter(req.getParameter("date")));
        int cardNumber = Integer.parseInt(req.getParameter("cardNumber"));
        card.setName(name);
        card.setDate(date);
        card.setCardNumber(cardNumber);

        HttpSession session = req.getSession();
        Customer customer = (Customer) session.getAttribute("user");
        int customerId = customer.getId();

        pdm.update(card, customerId);

        req.getSession().setAttribute("payment",payment);
    }

    protected void delete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, SQLException {
        int id = Integer.parseInt(req.getParameter("id"));
        pm.delete(id);
    }

    protected void viewHistory(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, ParseException, SQLException {
        List<Payment> paymentList=pm.getHistory();
        req.getSession().setAttribute("paymentList",paymentList);
    }
}
