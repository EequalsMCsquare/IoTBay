package com.eequalsmc2.IoTBay_Final.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.eequalsmc2.IoTBay_Final.model.Suppliers;
import com.eequalsmc2.IoTBay_Final.utils.DB;

public class SuppliersManager {
    private DB db;

    public SuppliersManager(DB db) {
        this.db = db;
    }

    private Connection conn() throws SQLException {
        return db.connection();
    }

    public int create(Suppliers s) throws SQLException {
        String sql = "INSERT INTO suppliers VALUE(null,?,?,?,?,?)";
        PreparedStatement st = conn().prepareStatement(sql);
        st.setString(1, s.getName());
        st.setString(2, s.getPhone());
        st.setString(3, s.getEmail());
        st.setString(4, s.getCompany());
        st.setString(5, s.getStatus());
        int row = st.executeUpdate();
        return row;
    }
    
    
    public int update(Suppliers s) throws SQLException {
        String sql = "update suppliers set  name=? , phone=? , email=? , company=? ,status=?  where id = ?";
        PreparedStatement st = conn().prepareStatement(sql);
        st.setString(1, s.getName());
        System.out.println("s.getName->"+s.getName());
        st.setString(2, s.getPhone());
        st.setString(3, s.getEmail());
        st.setString(4, s.getCompany());
        st.setString(5, s.getStatus());
        st.setInt(6, s.getId());
        int row = st.executeUpdate();
        return row ;
    }
    
    

    public int  delete(int id) throws SQLException {
        String sql = "DELETE FROM suppliers WHERE id = ?";
        PreparedStatement st = conn().prepareStatement(sql);
        st.setInt(1, id);
        int row = st.executeUpdate();
        return row;
    }

    public Suppliers get(int id) throws SQLException {
        String sql = "SELECT * FROM suppliers WHERE id = ?";
        PreparedStatement st = conn().prepareStatement(sql);
        st.setInt(1, id);
        ResultSet rs = st.executeQuery();
        while (rs.next()) {
        	int dbId = rs.getInt("id");
        	String name = rs.getString("name");
        	String phone =  rs.getString("phone");
        	String email = rs.getString("email");
        	String company = rs.getString("company");
        	String status = rs.getString("status");
        	Suppliers s = new Suppliers(dbId,name,phone,email,company,status);
        	return s; 
        }
        return null;
    }

    public ArrayList<Suppliers> search(Suppliers s ) throws SQLException {
        ArrayList<Suppliers> suppliers = new ArrayList<>();
        String sql = new String();
        if(s==null) {
        	sql = "select * from suppliers ";
        }else if(s.getCompany()!= null && s.getName()!=null) {
        	sql = "select * from suppliers where company like '%"+s.getCompany()+"%' and name like '%"+s.getName()+"%'"; 
        }else if(s.getCompany()!=null) {
        	sql = "select * from suppliers where company like '%"+s.getCompany()+"%'"; 
        }else if(s.getName()!=null) {
        	sql = "select * from suppliers where name like '%"+s.getName()+"%'";
        }
        PreparedStatement st = conn().prepareStatement(sql);
        ResultSet rs = st.executeQuery();
        while(rs.next()) {
        	int dbId = rs.getInt("id");
        	String name = rs.getString("name");
        	String phone =  rs.getString("phone");
        	String email = rs.getString("email");
        	String company = rs.getString("company");
        	String status = rs.getString("status");
        	Suppliers supplier = new Suppliers(dbId,name,phone,email,company,status);
        	suppliers.add(supplier);
        }
        return suppliers;
    }
}
