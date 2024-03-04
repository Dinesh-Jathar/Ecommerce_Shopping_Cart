package com.shoppingcart.Servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shoppingcart.dto.Cart;

/**
 * Servlet implementation class RemoveCart
 */
@WebServlet("/RemoveCart")
public class RemoveCart extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int id=Integer.parseInt(request.getParameter("remove"));
		System.out.println(id);
		
		ArrayList<Cart> list=(ArrayList<Cart>) request.getSession().getAttribute("cartList");
		if(list!=null) {
			for(Cart c:list) {
				if(c.getProduct_id()==id) {
					list.remove(list.indexOf(c));
					break;
				}
			}
			response.sendRedirect("cart.jsp");
		}else {
			response.sendRedirect("cart.jsp");
		}
	}

}
