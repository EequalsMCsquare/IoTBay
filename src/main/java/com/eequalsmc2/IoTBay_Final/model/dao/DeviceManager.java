package com.eequalsmc2.IoTBay_Final.model.dao;

import com.eequalsmc2.IoTBay_Final.model.Device;
import com.eequalsmc2.IoTBay_Final.utils.DB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DeviceManager {
    private DB db;

    public DeviceManager(DB db) {
        this.db = db;
    }

    private Connection conn() throws SQLException {
        return db.connection();
    }

    public int create(Device device) throws SQLException {
        String sql = "INSERT INTO device VALUE(null,?,?,?,?)";
        PreparedStatement st = conn().prepareStatement(sql);
        st.setString(1, device.getDeviceName());
        st.setString(2, device.getType());
        st.setInt(3, device.getUnitPrice());
        st.setInt(4, device.getQuantity());
        int row = st.executeUpdate();
        return row;
    }
    
    
    public int update(Device s) throws SQLException {
        String sql = "update device set  device_name=? , type=? , unit_price=? , quantity=?  where id = ?";
        PreparedStatement st = conn().prepareStatement(sql);
        st.setString(1, s.getDeviceName());
        st.setString(2, s.getType());
        st.setInt(3, s.getUnitPrice());
        st.setInt(4, s.getQuantity());
        st.setInt(5, s.getId());
        int row = st.executeUpdate();
        return row ;
    }
    
    

    public int  delete(int id) throws SQLException {
        String sql = "DELETE FROM device WHERE id = ?";
        PreparedStatement st = conn().prepareStatement(sql);
        st.setInt(1, id);
        int row = st.executeUpdate();
        return row;
    }

    public Device get(int id) throws SQLException {
        String sql = "SELECT * FROM device WHERE id = ?";
        PreparedStatement st = conn().prepareStatement(sql);
        st.setInt(1, id);
        ResultSet rs = st.executeQuery();
        while (rs.next()) {
        	int dbId = rs.getInt("id");
        	String name = rs.getString("device_name");
        	String type =  rs.getString("type");
        	int unitPrice = rs.getInt("unit_price");
        	int quantity = rs.getInt("quantity");
        	Device s = new Device(dbId,name,type,unitPrice,quantity);
        	return s; 
        }
        return null;
    }

    public ArrayList<Device> search(Device device ) throws SQLException {
        ArrayList<Device> Device = new ArrayList<>();
        String sql = new String();
        if(device==null) {
        	sql = "select * from device ";
        }else if(device.getDeviceName()!=null) {
        	sql = "select * from device where device_name like '%"+device.getDeviceName()+"%'"; 
        }else if(device.getType()!=null) {
        	sql = "select * from device where type like '%"+device.getType()+"%'";
        }
        PreparedStatement st = conn().prepareStatement(sql);
        ResultSet rs = st.executeQuery();
        while(rs.next()) {
        	int dbId = rs.getInt("id");
        	String name = rs.getString("device_name");
        	String type =  rs.getString("type");
        	int unitPrice = rs.getInt("unit_price");
        	int quantity = rs.getInt("quantity");
        	Device d = new Device(dbId,name,type,unitPrice,quantity);
        	Device.add(d);
        }
        return Device;
    }
}
