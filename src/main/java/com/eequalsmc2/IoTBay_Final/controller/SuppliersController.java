package com.eequalsmc2.IoTBay_Final.controller;

import com.eequalsmc2.IoTBay_Final.model.Customer;
import com.eequalsmc2.IoTBay_Final.model.Suppliers;
import com.eequalsmc2.IoTBay_Final.model.dao.CustomerAccessManager;
import com.eequalsmc2.IoTBay_Final.model.dao.CustomerManager;
import com.eequalsmc2.IoTBay_Final.model.dao.SuppliersManager;
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

@WebServlet("/supplier")
public class SuppliersController extends HttpServlet {
    DB db;
    SuppliersManager manager;
    String view="supplier?operate=list";
    String view2="supplier.jsp";
    public SuppliersController() throws SQLException {
        super();
        db = new DB();
        manager = new SuppliersManager(db);
    }
    
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	req.setCharacterEncoding("utf-8");
    	resp.setContentType("text/html;charset=utf-8");
    	String operate = req.getParameter("operate");

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
		Suppliers s = new Suppliers();
		if("name".equals(condition)) {
			s.setName(value);
	   		try {
				ArrayList<Suppliers> search = manager.search(s);
				req.setAttribute("list", search);
				req.getRequestDispatcher(view2).forward(req, resp);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}else if("company".equals(condition)) {
			s.setCompany(value);
			try {
				ArrayList<Suppliers> search = manager.search(s);
				req.setAttribute("list", search);
				req.getRequestDispatcher(view2).forward(req, resp);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
    }
    
    private void delete(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
    	String p = req.getParameter("id");
    	System.out.println(p);
    	if(isNotNullOrEmpty(p)) {
    		resp.getWriter().println("<script>alert('Delete failed, please select correct ID to delete')</script>"); ;
    	}
    	try {
			int row = manager.delete(Integer.valueOf(p));
			if(row>=1) {
				showList(req,resp);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			resp.getWriter().println("<script>alert('Delete failed"+e.getMessage()+"')</script>");
		}
    }
    
    private void edit(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
    	String p = req.getParameter("id");
    	String name = req.getParameter("name");
    	String phone = req.getParameter("phone");
    	String email = req.getParameter("email");
    	String company = req.getParameter("company");
    	String status = req.getParameter("status");
    	Suppliers s;
		
    	try {
			s = manager.get(Integer.valueOf(p));
    		if(s==null) {
				resp.getWriter().println("<script>alert('Failed to edit. There is no such record in the database')</script>");
        	}
        	if(isNotNullOrEmpty(name)) {
        		s.setName(name);
        	}
        	if(isNotNullOrEmpty(phone)) {
        		s.setPhone(phone);
        	}
        	if(isNotNullOrEmpty(email)) {
        		s.setEmail(email);
        	}
        	if(isNotNullOrEmpty(company)) {
        		s.setCompany(company);
        	}
        	if(isNotNullOrEmpty(status)) {
        		s.setStatus(status);
        	}
			int row = manager.update(s);
			System.out.print(row);
			if(row>=1) {
				req.getRequestDispatcher(view).forward(req, resp);
				return ; 
			}
		} catch (SQLException e) {
			e.printStackTrace();
			resp.getWriter().println("<script>alert('Failed to edit"+e.getMessage()+"')</script>");
		}
    }
    
    
    
    private void create(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
    	String name = req.getParameter("name");
    	String phone = req.getParameter("phone");
    	String email = req.getParameter("email");
    	String company = req.getParameter("company");
    	String status = req.getParameter("status");
    	if(!isValidEmailFormat(email)) {
    		resp.getWriter().println("<script>alert('Incorrect email address')</script>");
    		showList(req,resp);
    		return ; 
    	}
    	Suppliers s = new Suppliers(name,phone,email,company,status);
    	try {
			int row = manager.create(s);
			if(row>=1) {
				req.getRequestDispatcher(view).forward(req, resp);
				return ;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			resp.getWriter().println("<script>alert('Add failed"+e.getMessage()+"')</script>");
		}
    }
    
    
    private void showList(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	try {
			ArrayList<Suppliers> list = manager.search(null);
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

    private boolean isValidEmailFormat(String email) {
        if (email.contains("@")) {
            return true;
        }
        return false;
    }

   
}
