package com.eequalsmc2.IoTBay_Final.model.dao;

import com.eequalsmc2.IoTBay_Final.model.CreditCard;

import java.sql.*;

public class PaymentDetailManager {
    private Connection conn;


    public PaymentDetailManager(Connection conn) throws SQLException {
        this.conn = conn;
    }

    public void create(CreditCard card, int id) throws SQLException {
        String SQL = "insert into payments(name,expire_date,card_number,customer_id)values(?,?,?,?)";
        PreparedStatement st = conn.prepareStatement(SQL);
        st.setString(1, card.getName());
        st.setDate(2, new Date(card.getDate().getTime()));
        st.setInt(3, card.getCardNumber());
        st.setInt(4, id);
        st.execute();
    }

    public void update(CreditCard card, int id) throws SQLException {
        String SQL = "update payment_details set'name'=?,'expire_date'=?,'card_number'=?,'customer_id'=?";
        PreparedStatement st = conn.prepareStatement(SQL);
        st.setString(1, card.getName());
        st.setDate(2, new Date(card.getDate().getTime()));
        st.setInt(3, card.getCardNumber());
        st.setInt(4, id);
        st.execute();
    }

    public CreditCard get(int id) throws SQLException {
        String SQL = "select * from payment_details where id=?";
        PreparedStatement st = conn.prepareStatement(SQL);
        st.setInt(1, id);
        ResultSet rs = st.executeQuery();
        if (rs.next()) {
            CreditCard card = new CreditCard();
            card.setCardNumber(rs.getInt("card_number"));
            card.setName(rs.getString("name"));
            card.setDate(rs.getDate("expire_date"));
            return card;
        }
        return null;
    }

    public void delete(int id) throws SQLException {
        String SQL = "delete from payment_details where id=?";
        PreparedStatement st = conn.prepareStatement(SQL);
        st.setInt(1, id);
        st.execute();
    }
}
