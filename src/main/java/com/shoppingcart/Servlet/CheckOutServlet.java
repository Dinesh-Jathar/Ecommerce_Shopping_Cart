package com.shoppingcart.Servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.shoppingcart.connection.Db_connection;
import com.shoppingcart.dao.OrderDao;
import com.shoppingcart.dto.Cart;
import com.shoppingcart.dto.User;

/**
 * Servlet implementation class CheckOutServlet
 */
@WebServlet("/CheckOutServlet")
public class CheckOutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		User user = (User) request.getSession().getAttribute("auth");
		HttpSession session = request.getSession();
		List<Cart> cartList = (ArrayList<Cart>) session.getAttribute("cartList");
		if (user != null && cartList != null) {

			try {
				OrderDao dao = new OrderDao(Db_connection.getConnection());
				boolean result=dao.checkOutOrders(cartList, user);
				if(result) {
					cartList.clear();
					response.sendRedirect("orders.jsp");
				}else {
					response.sendRedirect("index.jsp");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

		} else if (cartList == null) {
			response.sendRedirect("index.jsp");
		} else {
			response.sendRedirect("login.jsp");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
