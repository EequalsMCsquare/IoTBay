package com.eequalsmc2.IoTBay_Final.controller;

import com.eequalsmc2.IoTBay_Final.model.Customer;
import com.eequalsmc2.IoTBay_Final.model.Order;
import com.eequalsmc2.IoTBay_Final.model.Payment;
import com.eequalsmc2.IoTBay_Final.model.PaymentDetail;
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
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@WebServlet("/paymentDetailServlet")
public class PaymentDetailController extends HttpServlet {
    DB db;
    PaymentManager pdm;
    PaymentDetailManager pm;
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    public PaymentDetailController() throws SQLException, ClassNotFoundException {
        super();
        db = new DB();
        pdm = new PaymentManager(db);
        pm=new PaymentDetailManager(db);
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");

        switch (action) {
            case "view":
                try {
                    view(req, resp);
                } catch (ParseException | SQLException e) {
                    e.printStackTrace();
                }
                break;
            case "delete":
                try {
                    delete(req, resp);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                break;

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
        /*

         */
        HttpSession session = req.getSession();
        Customer customer = (Customer) session.getAttribute("user");
        int customerId = customer.getId();
        PaymentDetail payment=new PaymentDetail();
        payment.setPayId(Integer.parseInt(req.getParameter("payId")));
        payment.setStatus("pending");
        payment.setDate(new Date());
        payment.setCustomerId(customerId);
        Integer orderId = Integer.parseInt(req.getParameter("orderId"));
        payment.setOrderId(orderId);
        payment.setAmount(9.99f);
        pm.create(payment);
        resp.sendRedirect("paymentDetailServlet?action=view");
    }

    protected void view(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, ParseException, SQLException {


        HttpSession session = req.getSession();
        Customer customer = (Customer) session.getAttribute("user");
        int customerId = customer.getId();
        List<PaymentDetail> payments = pm.list();
        List<String> orderList = new ArrayList<>();
        orderList.add("1");
        orderList.add("2");
        orderList.add("3");
        req.getSession().setAttribute("payments", pdm.listById(customerId));
        req.getSession().setAttribute("orders", orderList);
        req.getSession().setAttribute("paymentDetails", payments);
        req.getRequestDispatcher("paymentDetail.jsp").forward(req, resp);
    }

    protected void update(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, ParseException, SQLException {
        Integer id = Integer.parseInt(req.getParameter("id"));
        PaymentDetail detail = new PaymentDetail();
        String status = req.getParameter("status");
        detail.setStatus(status);
        pm.update(detail, id);
        resp.sendRedirect("paymentDetailServlet?action=view");
    }

    protected void delete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, SQLException {
        int id = Integer.parseInt(req.getParameter("id"));
        pm.delete(id);
        resp.sendRedirect("paymentDetailServlet?action=view");
    }
}
