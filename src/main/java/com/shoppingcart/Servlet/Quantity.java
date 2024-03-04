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
 * Servlet implementation class Quantity
 */
@WebServlet("/Quantity")
public class Quantity extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String action=request.getParameter("action");
		int id=Integer.parseInt(request.getParameter("id"));
		
		ArrayList<Cart> list=(ArrayList<Cart>) request.getSession().getAttribute("cartList");
		if(action!=null){
			if(action.equals("inc")) {
				for(Cart cart:list) {
					if(cart.getProduct_id()==id) {
						int quantity=cart.getQuantity();
						quantity++;
						cart.setQuantity(quantity);
						break;
					}
				}
				response.sendRedirect("cart.jsp");
			}
			if(action.equals("dec")) {
				for(Cart cart:list) {
					if(cart.getProduct_id()==id && cart.getQuantity()>1) {
						int quantity=cart.getQuantity();
						quantity--;
						cart.setQuantity(quantity);
						break;
					}
				}
				response.sendRedirect("cart.jsp");
			}
		}
			else {
			response.sendRedirect("cart.jsp");
		}
	}

}
