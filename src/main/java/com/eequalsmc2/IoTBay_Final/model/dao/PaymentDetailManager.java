package com.eequalsmc2.IoTBay_Final.model.dao;

import com.eequalsmc2.IoTBay_Final.model.CreditCard;
import com.eequalsmc2.IoTBay_Final.utils.DB;

import java.sql.*;

public class PaymentDetailManager {

    private DB db;

    public PaymentDetailManager(DB db) throws SQLException {
        this.db = db;
    }

    private Connection conn() throws SQLException {
        return this.db.connection();
    }

    public Integer create(CreditCard card, int id) throws SQLException {
        String SQL = "insert into payments(name,expire_date,card_number,customer_id)values(?,?,?,?)";
        PreparedStatement st = conn().prepareStatement(SQL);
        st.setString(1, card.getName());
        st.setDate(2, new Date(card.getDate().getTime()));
        st.setInt(3, card.getCardNumber());
        st.setInt(4, id);
        st.execute();

        ResultSet resultSet = st.getGeneratedKeys();
        return resultSet.getInt(1);
    }

    public void update(CreditCard card, int id) throws SQLException {
        String SQL = "update payment_details set'name'=?,'expire_date'=?,'card_number'=?,'customer_id'=? where id=?";
        PreparedStatement st = conn().prepareStatement(SQL);
        st.setString(1, card.getName());
        st.setDate(2, new Date(card.getDate().getTime()));
        st.setInt(3, card.getCardNumber());
        st.setInt(4, id);
        st.setInt(5,card.getId());
        st.execute();
    }

    public CreditCard get(int id) throws SQLException {
        String SQL = "select * from payment_details where id=?";
        PreparedStatement st = conn().prepareStatement(SQL);
        st.setInt(1, id);
        ResultSet rs = st.executeQuery();
        if (rs.next()) {
            CreditCard card = new CreditCard();
            card.setId(rs.getInt("id"));
            card.setCardNumber(rs.getInt("card_number"));
            card.setName(rs.getString("name"));
            card.setDate(rs.getDate("expire_date"));
            return card;
        }
        return null;
    }

    public void delete(int id) throws SQLException {
        String SQL = "delete from payment_details where id=?";
        PreparedStatement st = conn().prepareStatement(SQL);
        st.setInt(1, id);
        st.execute();
    }
}
