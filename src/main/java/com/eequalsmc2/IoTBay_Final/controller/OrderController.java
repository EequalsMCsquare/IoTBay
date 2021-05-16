package com.eequalsmc2.IoTBay_Final.controller;

import com.eequalsmc2.IoTBay_Final.model.Order;
import com.eequalsmc2.IoTBay_Final.model.dao.OrderManager;
import com.eequalsmc2.IoTBay_Final.utils.DB;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

@WebServlet("/order")
public class OrderController extends HttpServlet {
    DB db;
    OrderManager manager;
    String view="order?operate=list";
    String view2="order.jsp";
    public OrderController() throws SQLException {
        super();
        db = new DB();
        manager = new OrderManager(db);
    }
    
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	req.setCharacterEncoding("utf-8");
    	resp.setContentType("text/html;charset=utf-8");
    	String operate = req.getParameter("operate");
    	//������ʾҳ��
    	if("list".equals(operate)) {
    		showList(req,resp);
    	}else if("add".equals(operate)) {
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
		Order s = new Order();
		if("id".equals(condition)) {
			s.setId(Integer.valueOf(value));
	   		try {
				ArrayList<Order> search = manager.search(s);
				req.setAttribute("list", search);
				req.getRequestDispatcher(view2).forward(req, resp);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}else if("date".equals(condition)) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Date parse = null;
			try {
				parse = sdf.parse(value);
			} catch (ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			s.setDate(parse);
			try {
				ArrayList<Order> search = manager.search(s);
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
    		resp.getWriter().println("<script>alert('ɾ��ʧ�ܣ���ѡ����ȷIDɾ��')</script>"); ;
    	}
    	try {
			int row = manager.delete(Integer.valueOf(p));
			if(row>=1) {
				showList(req,resp);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			resp.getWriter().println("<script>alert('ɾ��ʧ��"+e.getMessage()+"')</script>");
		}
    }
    
    private void edit(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
    	String id = req.getParameter("id");
    	String customerId = req.getParameter("customerId");
    	String orderItemsId = req.getParameter("orderItemsId");
    	String totalPrice = req.getParameter("totalPrice");
    	String date = req.getParameter("date");
    	String status = req.getParameter("status");
    	String shipmentId = req.getParameter("shipmentId");
    	Order order;
		
    	try {
			order = manager.get(Integer.valueOf(id));
    		if(order==null) {
				resp.getWriter().println("<script>alert('�༭ʧ�ܣ����ݿ��޴�����¼')</script>");
        	}
        	if(isNotNullOrEmpty(customerId)) {
        		order.setCustomerId(Integer.valueOf(customerId));
        	}
        	if(isNotNullOrEmpty(orderItemsId)) {
        		order.setOrderItemsId(Integer.valueOf(orderItemsId));
        	}
        	if(isNotNullOrEmpty(totalPrice)) {
        		order.setTotalPrice(Double.valueOf(totalPrice));
        	}
        	if(isNotNullOrEmpty(date)) {
        		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    			Date parse = null;
    			try {
    				parse = sdf.parse(date);
    			} catch (ParseException e1) {
    				// TODO Auto-generated catch block
    				e1.printStackTrace();
    			}
        		order.setDate(parse);
        	}
        	if(isNotNullOrEmpty(status)) {
        		order.setStatus(Integer.valueOf(status));
        	}
        	if(isNotNullOrEmpty(shipmentId)) {
        		order.setStatus(Integer.valueOf(shipmentId));
        	}
			int row = manager.update(order);
			System.out.print(row);
			if(row>=1) {
				req.getRequestDispatcher(view).forward(req, resp);
				return ; 
			}
		} catch (SQLException e) {
			e.printStackTrace();
			resp.getWriter().println("<script>alert('�༭ʧ��"+e.getMessage()+"')</script>");
		}
    }
    
    
    
    private void create(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
    	String customerId = req.getParameter("customerId");
    	String orderItemsId = req.getParameter("orderItemsId");
    	String totalPrice = req.getParameter("totalPrice");
    	String date = req.getParameter("date");
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date parse = null;
		try {
			parse = sdf.parse(date);
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
    	String status = req.getParameter("status");
    	String shipmentId = req.getParameter("shipmentId");
    	
    	Order o = new Order(null,Integer.valueOf(customerId),Integer.valueOf(orderItemsId),Double.valueOf(totalPrice),parse,Integer.valueOf(status),Integer.valueOf(shipmentId));
    	try {
			int row = manager.create(o);
			if(row>=1) {
				req.getRequestDispatcher(view).forward(req, resp);
				return ;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			resp.getWriter().println("<script>alert('���ʧ��"+e.getMessage()+"')</script>");
		}
    }
    
    
    private void showList(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	try {
			ArrayList<Order> list = manager.search(null);
			System.out.println(list);
			//�������ݵ�request��
			req.setAttribute("list", list);
			//ͨ��ת����order.jsp��
			req.getRequestDispatcher(view2).forward(req, resp);
		} catch (SQLException e) {
			e.printStackTrace();
		}
    }
    
    
  
    private boolean isNotNullOrEmpty(String str) {
        if (str.equals("") || str == null) {
            return false;
        }
        return true;
    }


   
}
