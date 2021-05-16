package com.eequalsmc2.IoTBay_Final.model.dao;

import com.eequalsmc2.IoTBay_Final.model.PaymentDetail;
import com.eequalsmc2.IoTBay_Final.utils.DB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PaymentDetailManager {
    private PaymentManager pdm;
    private OrderManager om;
    private DB db;

    public PaymentDetailManager(DB db) throws SQLException {
        this.db = db;
        pdm = new PaymentManager(db);
        om = new OrderManager(db);
    }

    private Connection conn() throws SQLException {
        return this.db.connection();
    }

    public void create(PaymentDetail payment) throws  SQLException{
        String SQL="insert into payment_details(order_id,amount,pay_id,status,date, customer_id) values(?,?,?,?,?,?)";
        PreparedStatement preparedStatement= conn().prepareStatement(SQL);
        preparedStatement.setInt(1,payment.getOrderId());
        preparedStatement.setFloat(2,payment.getAmount());
        preparedStatement.setInt(3,payment.getPayId());
        preparedStatement.setString(4, payment.getStatus());
        preparedStatement.setDate(5,new Date(payment.getDate().getTime()));
        preparedStatement.setInt(6,payment.getCustomerId());
        preparedStatement.execute();
    }

    public List<PaymentDetail> list() throws SQLException{
        // fix:
        String sql = "select * from payment_details";

        PreparedStatement preparedStatement=conn().prepareStatement(sql);
        List<PaymentDetail> list = new ArrayList<>();
        ResultSet rs= preparedStatement.executeQuery();
        while (rs.next()) {
            PaymentDetail payment=new PaymentDetail();
            payment.setId(rs.getInt("id"));
            payment.setAmount(rs.getFloat("amount"));
            payment.setDate(new java.util.Date(rs.getDate("date").getTime()));
            payment.setStatus(rs.getString("status"));
            payment.setOrderId(rs.getInt("order_id"));
            payment.setPayment(pdm.get(rs.getInt("pay_id")));
            payment.setPayId(rs.getInt("pay_id"));
            list.add(payment);
        }
        return list;
    }

    public void update(PaymentDetail payment, int id) throws SQLException{
        String SQL="update payment_details set amount=?,status=? where id=?";
        PreparedStatement preparedStatement=conn().prepareStatement(SQL);
        preparedStatement.setFloat(1,payment.getAmount());
        preparedStatement.setString(2,payment.getStatus());
        preparedStatement.setInt(3, id);
        preparedStatement.execute();
    }

    public void delete(int id)throws SQLException{
        String SQL="delete from payment_details where id=?";
        PreparedStatement preparedStatement= conn().prepareStatement(SQL);
        preparedStatement.setInt(1,id);
        preparedStatement.execute();
    }
}
