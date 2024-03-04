package com.shoppingcart.Servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shoppingcart.connection.Db_connection;
import com.shoppingcart.dao.OrderDao;


@WebServlet("/RemoveOrder")
public class RemoveOrder extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id=Integer.parseInt(request.getParameter("orderId"));
		try {
			OrderDao dao=new OrderDao(Db_connection.getConnection());
			dao.cancelOrder(id);
			response.sendRedirect("orders.jsp");
		} catch (Exception e) {
			System.out.println(e);
		} 
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
