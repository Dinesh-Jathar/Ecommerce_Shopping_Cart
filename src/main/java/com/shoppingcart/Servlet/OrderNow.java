package com.shoppingcart.Servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shoppingcart.connection.Db_connection;
import com.shoppingcart.dao.OrderDao;
import com.shoppingcart.dto.Cart;
import com.shoppingcart.dto.Order;
import com.shoppingcart.dto.User;

/**
 * Servlet implementation class Order
 */
@WebServlet("/Order")
public class OrderNow extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");

		Date date = new Date();

		User user = (User) request.getSession().getAttribute("auth");
		if (user != null) {
			int quan = Integer.parseInt(request.getParameter("quantity"));
			int id = Integer.parseInt(request.getParameter("id"));

			if (quan <= 0) {
				quan = 1;
			}
			Order orderDetails = new Order();
			orderDetails.setProduct_id(id);
			orderDetails.setQuantity(quan);
			orderDetails.setUserName(user.getUserName());
			orderDetails.setDate(dateFormat.format(date));
			try {
				OrderDao dao = new OrderDao(Db_connection.getConnection());
				
				boolean result=dao.insertOrder(orderDetails);
				if(result) {
					ArrayList<Cart> list=(ArrayList<Cart>) request.getSession().getAttribute("cartList");
					if(list!=null) {
						for(Cart c:list) {
							if(c.getProduct_id()==id) {
								list.remove(list.indexOf(c));
								break;
							}
						}
					}
					response.sendRedirect("orders.jsp");
				}else {
					PrintWriter writer=response.getWriter();
					writer.print("order failed");
				}
			} catch (Exception e) {
				System.out.println(e);
			}

		} else {
			response.sendRedirect("login.jsp");
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
}
