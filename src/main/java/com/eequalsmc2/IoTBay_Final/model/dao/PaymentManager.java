package com.eequalsmc2.IoTBay_Final.model.dao;

import com.eequalsmc2.IoTBay_Final.model.Order;
import com.eequalsmc2.IoTBay_Final.model.Payment;
import com.eequalsmc2.IoTBay_Final.model.PaymentStatus;
import com.eequalsmc2.IoTBay_Final.utils.DB;
import com.sun.org.apache.xpath.internal.operations.Or;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class PaymentManager {
    private PaymentDetailManager pdm;
    private OrderManager om;
    private DB db;

    public PaymentManager(DB db) throws SQLException {
        this.db = db;
        pdm = new PaymentDetailManager(db);
        om = new OrderManager(db);
    }

    private Connection conn() throws SQLException {
        return this.db.connection();
    }

    public int create(int orderId, int paymentDetailId) throws SQLException {
        Order order = om.get(orderId);
        if(order == null) {
            return 0;
        }
        String sql = "INSERT INTO payments (order_id, amount, payment_detail_id, status, 'date') " +
                "VALUE(?, (SELECT total_price FROM orders WHERE orders.id = ?), ?, ?, now())";
        PreparedStatement st = conn().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        st.setInt(1, orderId);
        st.setInt(2, orderId);
        st.setInt(3, paymentDetailId);
        st.setInt(4, (int)PaymentStatus.Pending.ordinal());
        ResultSet rs = st.getGeneratedKeys();
        return rs.getInt(1);
    }

    public Integer create(Payment payment) throws  SQLException{
        String SQL="insert into payments(order_id,amount,payment_detail_id,status,date) values(?,?,?,?,?)";
        PreparedStatement preparedStatement= conn().prepareStatement(SQL);
        preparedStatement.setInt(1,payment.getOrder_id());
        preparedStatement.setFloat(2,payment.getAmount());
        preparedStatement.setInt(3,payment.getPaymentDetailId());
        preparedStatement.setString(4, payment.getStatus());
        preparedStatement.setDate(5,new Date(payment.getDate().getTime()));
        preparedStatement.execute();

        ResultSet rs=preparedStatement.getGeneratedKeys();
        return rs.getInt(1);
    }

    public Payment get(int id, java.util.Date date) throws SQLException{
        // fix:
        String SQL="select * from payments where id=? and date=?";
        PreparedStatement preparedStatement=conn().prepareStatement(SQL);
        preparedStatement.setInt(1,id);
        preparedStatement.setDate(2,new Date(date.getTime()));

        ResultSet rs= preparedStatement.executeQuery();
        if (rs.next()) {
            Payment payment=new Payment();
            payment.setId(rs.getInt("id"));
            payment.setAmount(rs.getFloat("amount"));
            payment.setDate(new java.util.Date(rs.getDate("date").getTime()));
            payment.setStatus(rs.getString("status"));
            payment.setOrder_id(rs.getInt("order_id"));
            payment.setCard(pdm.get(rs.getInt("payment_detail_id")));
            payment.setPaymentDetailId(rs.getInt("payment_detail_id"));
            return payment;
        }
        return null;
    }

    public void update(Payment payment,int id) throws SQLException{
        String SQL="update payments set amount=?,status=?,date=?,payment_detail_id=? where id=?";
        PreparedStatement preparedStatement=conn().prepareStatement(SQL);
        preparedStatement.setFloat(1,payment.getAmount());
        preparedStatement.setString(2,payment.getStatus());
        preparedStatement.setDate(3,new Date(payment.getDate().getTime()));
        preparedStatement.setInt(4,payment.getPaymentDetailId());
        preparedStatement.setInt(5,id);

        preparedStatement.execute();
    }

    public void delete(int id)throws SQLException{
        String SQL="delete from payments where id=?";
        PreparedStatement preparedStatement= conn().prepareStatement(SQL);
        preparedStatement.setInt(1,id);
        preparedStatement.execute();
    }

    public List<Payment> getHistory() throws SQLException {
        String SQL="select * from payments";
        PreparedStatement preparedStatement=conn().prepareStatement(SQL);

        ResultSet rs=preparedStatement.executeQuery();

        List<Payment> paymentList=new LinkedList<>();
        while(rs.next()){
            Payment payment=new Payment();
            payment.setId(rs.getInt("id"));
            payment.setAmount(rs.getFloat("amount"));
            payment.setDate(rs.getDate("date"));
            payment.setStatus(rs.getString("status"));
            payment.setOrder_id(rs.getInt("order_id"));
            payment.setCard(pdm.get(rs.getInt("payment_detail_id")));
            payment.setPaymentDetailId(rs.getInt("payment_detail_id"));
            paymentList.add(payment);
        }
        return paymentList;
    }
}
