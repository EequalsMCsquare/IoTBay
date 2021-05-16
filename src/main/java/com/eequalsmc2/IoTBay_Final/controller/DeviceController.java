package com.eequalsmc2.IoTBay_Final.controller;

import com.eequalsmc2.IoTBay_Final.model.Device;
import com.eequalsmc2.IoTBay_Final.model.Staff;
import com.eequalsmc2.IoTBay_Final.model.dao.DeviceManager;
import com.eequalsmc2.IoTBay_Final.utils.DB;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet("/device")
public class DeviceController extends HttpServlet {
    DB db;
    DeviceManager manager;
    String view="device?operate=list";
    String view2="device.jsp";
    public DeviceController() throws SQLException {
        super();
        db = new DB();
        manager = new DeviceManager(db);
    }
    
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	req.setCharacterEncoding("utf-8");
    	resp.setContentType("text/html;charset=utf-8");
    	String operate = req.getParameter("operate");
    	//代表显示页面
    	if("list".equals(operate)) {
    		showList(req,resp);
    	}else if("create".equals(operate)) {
    		create(req,resp);
    	}else if("edit".equals(operate)) {
    		edit(req,resp);
    	}else if("delete".equals(operate)) {
    		delete(req,resp);
    	}else if("search".equals(operate)) {
    		search(req,resp);
    	}
    }
    
    private void search(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	String condition = req.getParameter("condition");
		String value = req.getParameter("value");
		Device s = new Device();
		if("deviceName".equals(condition)) {
			s.setDeviceName(value);
	   		try {
				ArrayList<Device> search = manager.search(s);
				req.setAttribute("list", search);
				req.getRequestDispatcher(view2).forward(req, resp);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}else if("type".equals(condition)) {
			s.setType(value);
			try {
				ArrayList<Device> search = manager.search(s);
				req.setAttribute("list", search);
				req.getRequestDispatcher(view2).forward(req, resp);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
    }
    
    private void delete(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
    	HttpSession session = req.getSession();
    	Object attribute = session.getAttribute("user");
    	if(!checkStaff(attribute)) {
    		resp.getWriter().println("<script>alert('no perimit ,please reloding')</script>");
    		return;
    	}
    	String p = req.getParameter("id");
    	if(isNotNullOrEmpty(p)) {
    		resp.getWriter().println("<script>alert('delete failed , please input correct ID')</script>"); ;
    	}
    	try {
			int row = manager.delete(Integer.valueOf(p));
			if(row>=1) {
				showList(req,resp);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			resp.getWriter().println("<script>alert('delete failed->"+e.getMessage()+"')</script>");
		}
    }
    
    /**
     * @param req
     * @param resp
     * @throws IOException
     * @throws ServletException
     */
    private void edit(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
    	HttpSession session = req.getSession();
    	Object attribute = session.getAttribute("user");
    	if(!checkStaff(attribute)) {
    		resp.getWriter().println("<script>alert('no perimit ,please reloding')</script>");
    		return;
    	}
    	String id = req.getParameter("id");
    	String deviceName = req.getParameter("deviceName");
    	String type = req.getParameter("type");
    	String unitPrice = req.getParameter("unitPrice");
    	String quantity = req.getParameter("quantity");
    	Device device;
		
    	try {
			device = manager.get(Integer.valueOf(id));
    		if(device==null) {
				resp.getWriter().println("<script>alert('edit failed , dabase no data')</script>");
        	}
        	if(isNotNullOrEmpty(deviceName)) {
        		device.setDeviceName(deviceName);
        	}
        	if(isNotNullOrEmpty(type)) {
        		device.setType(type);;
        	}
        	if(isNotNullOrEmpty(unitPrice)) {
        		device.setUnitPrice(Integer.valueOf(unitPrice));
        	}
        	if(isNotNullOrEmpty(quantity)) {
        		device.setQuantity(Integer.valueOf(quantity));
        	}
			int row = manager.update(device);
			System.out.print(row);
			if(row>=1) {
				req.getRequestDispatcher(view).forward(req, resp);
				return ; 
			}
		} catch (SQLException e) {
			e.printStackTrace();
			resp.getWriter().println("<script>alert('edit failed"+e.getMessage()+"')</script>");
		}
    }
    
    public boolean checkStaff(Object obj) {
    
    	if(obj.getClass().equals(Staff.class) ) {
    		return true;
    	}else {
    		return false;
    	}
    }
    
    
    
    private void create(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
    	HttpSession session = req.getSession();
    	Object attribute = session.getAttribute("user");
    	if(!checkStaff(attribute)) {
    		resp.getWriter().println("<script>alert('no perimit ,please reloding')</script>");
    		return;
    	}
    	String deviceName = req.getParameter("deviceName");
    	String type = req.getParameter("type");
    	String unitPrice = req.getParameter("unitPrice");
    	String quantity = req.getParameter("quantity");
    	Device device = new Device(deviceName,type,Integer.valueOf(unitPrice),Integer.valueOf(quantity));
    	try {
			int row = manager.create(device);
			if(row>=1) {
				req.getRequestDispatcher(view).forward(req, resp);
				return ;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			resp.getWriter().println("<script>alert('add failed"+e.getMessage()+"')</script>");
		}
    }
    
    
    private void showList(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	try {
			ArrayList<Device> list = manager.search(null);
			System.out.println(list);
			req.setAttribute("list", list);
			req.getRequestDispatcher(view2).forward(req, resp);
		} catch (SQLException e) {
			e.printStackTrace();
		}
    }
  
    private boolean isNotNullOrEmpty(String str) {
        if (str.isEmpty() || str == null) {
            return false;
        }
        return true;
    }



   
}
