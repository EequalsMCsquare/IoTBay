package com.eequalsmc2.IoTBay_Final.model.dao;

import com.eequalsmc2.IoTBay_Final.model.Order;
import com.eequalsmc2.IoTBay_Final.utils.DB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class OrderManager {
    private DB db;

    public OrderManager(DB db) {
        this.db = db;
    }

    private Connection conn() throws SQLException {
        return db.connection();
    }

    public int create(Order order) throws SQLException {
        String sql = "INSERT INTO orders VALUE(null,?,?,?,?,?,?)";
        PreparedStatement st = conn().prepareStatement(sql);
        st.setInt(1, order.getCustomerId());
        st.setInt(2, order.getOrderItemsId());
        st.setDouble(3, order.getTotalPrice());
        st.setDate(4, new java.sql.Date(order.getDate().getTime()));
        st.setInt(5, order.getStatus());
        st.setInt(6, order.getShipmentId());
        int row = st.executeUpdate();
        return row;
    }
    
    
    public int update(Order order) throws SQLException {
        String sql = "update orders set  customer_id=? , order_items_id=? , total_price=? , date=? ,status=? , shipment_id = ? where id = ?";
        PreparedStatement st = conn().prepareStatement(sql);
        st.setInt(1, order.getCustomerId());
        st.setInt(2, order.getOrderItemsId());
        st.setDouble(3, order.getTotalPrice());
        st.setDate(4, new java.sql.Date(order.getDate().getTime()));
        st.setInt(5, order.getStatus());
        st.setInt(6, order.getShipmentId());
        st.setInt(7, order.getId());
        int row = st.executeUpdate();
        return row ;
    }
    
    

    public int  delete(int id) throws SQLException {
        String sql = "DELETE FROM orders WHERE id = ?";
        PreparedStatement st = conn().prepareStatement(sql);
        st.setInt(1, id);
        int row = st.executeUpdate();
        return row;
    }

    public Order get(int id) throws SQLException {
        String sql = "SELECT * FROM orders WHERE id = ?";
        PreparedStatement st = conn().prepareStatement(sql);
        st.setInt(1, id);
        ResultSet rs = st.executeQuery();
        while (rs.next()) {
        	Integer dbId = rs.getInt("id");
        	Integer customerId = rs.getInt("customer_id");
        	Integer orderItemsId =  rs.getInt("order_items_id");
        	Double totalPrice = rs.getDouble("total_price");
        	java.util.Date date = rs.getDate("date");
        	Integer status = rs.getInt("status");
        	Integer shipmentId = rs.getInt("shipment_id");
        	Order o = new Order(dbId,customerId,orderItemsId,totalPrice,date,status,shipmentId);
        	return o; 
        }
        return null;
    }

    public ArrayList<Order> search(Order o ) throws SQLException {
    	System.err.println(o);
        ArrayList<Order> orders = new ArrayList<>();
        String sql = new String();
        if(o==null) {
        	sql = "select * from orders order by status ";
        }else if(o.getId()!=null) {
        	sql = "select * from orders where id = " + o.getId() +" order by status"; 
        }else if(o.getDate()!=null) {
        	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        	String format = sdf.format(o.getDate());
        	System.err.println(format);
        	sql = "select * from orders where date = '"+format+"' order by status";
        }
        PreparedStatement st = conn().prepareStatement(sql);
        ResultSet rs = st.executeQuery();
        while(rs.next()) {
        	Integer dbId = rs.getInt("id");
        	Integer customerId = rs.getInt("customer_id");
        	Integer orderItemsId =  rs.getInt("order_items_id");
        	Double totalPrice = rs.getDouble("total_price");
        	java.util.Date date = rs.getDate("date");
        	Integer status = rs.getInt("status");
        	Integer shipmentId = rs.getInt("shipment_id");
        	Order order = new Order(dbId,customerId,orderItemsId,totalPrice,date,status,shipmentId);
        	orders.add(order);
        }
        return orders;
    }
}
