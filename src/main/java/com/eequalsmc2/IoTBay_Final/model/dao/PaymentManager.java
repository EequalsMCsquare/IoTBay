package com.eequalsmc2.IoTBay_Final.model.dao;

import com.eequalsmc2.IoTBay_Final.model.Payment;
import com.eequalsmc2.IoTBay_Final.utils.DB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PaymentManager {

    private DB db;

    public PaymentManager(DB db) throws SQLException {
        this.db = db;
    }

    private Connection conn() throws SQLException {
        return this.db.connection();
    }

    public void create(Payment card, int id) throws SQLException {
        String SQL = "insert into payments(name,expire_date,card_number,customer_id)values(?,?,?,?)";
        PreparedStatement st = conn().prepareStatement(SQL);
        st.setString(1, card.getName());
        st.setDate(2, new Date(card.getDate().getTime()));
        st.setInt(3, card.getCardNumber());
        st.setInt(4, id);
        st.execute();
    }

    public void update(Payment card, int id) throws SQLException {
        String SQL = "update payments set name=?,expire_date=?,card_number=?,customer_id=? where id=?";
        PreparedStatement st = conn().prepareStatement(SQL);
        st.setString(1, card.getName());
        st.setDate(2, new Date(card.getDate().getTime()));
        st.setInt(3, card.getCardNumber());
        st.setInt(4, id);
        st.setInt(5,card.getId());
        st.execute();
    }

    public Payment get(int id) throws SQLException {
        String SQL = "select * from payments where id=?";
        PreparedStatement st = conn().prepareStatement(SQL);
        st.setInt(1, id);
        ResultSet rs = st.executeQuery();
        if (rs.next()) {
            Payment card = new Payment();
            card.setId(rs.getInt("id"));
            card.setCustomerId(rs.getInt("customer_id"));
            card.setCardNumber(rs.getInt("card_number"));
            card.setName(rs.getString("name"));
            card.setDate(rs.getDate("expire_date"));
            return card;
        }
        return null;
    }

    public List<Payment> listById(int id) throws SQLException {
        String SQL = "select * from payments where customer_id=?";
        PreparedStatement st = conn().prepareStatement(SQL);
        st.setInt(1, id);
        List<Payment> payments = new ArrayList<>();
        ResultSet rs = st.executeQuery();
        while (rs.next()) {
            Payment card = new Payment();
            card.setId(rs.getInt("id"));
            card.setCustomerId(rs.getInt("customer_id"));
            card.setCardNumber(rs.getInt("card_number"));
            card.setName(rs.getString("name"));
            card.setDate(rs.getDate("expire_date"));
            payments.add(card);
        }
        return payments;
    }

    public void delete(int id) throws SQLException {
        String SQL = "delete from payments where id=?";
        PreparedStatement st = conn().prepareStatement(SQL);
        st.setInt(1, id);
        st.execute();
    }
}
