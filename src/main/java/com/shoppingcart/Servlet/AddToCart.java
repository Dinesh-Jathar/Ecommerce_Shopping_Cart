package com.shoppingcart.Servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.shoppingcart.dto.Cart;

/**
 * Servlet implementation class AddToCart
 */
@WebServlet("/AddToCart")
public class AddToCart extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int id=Integer.parseInt(req.getParameter("id"));
		List<Cart>cart_List=new ArrayList<Cart>();
		Cart cart=new Cart();
		cart.setProduct_id(id);
		cart.setQuantity(1);
		
		PrintWriter out=resp.getWriter();
		HttpSession session =req.getSession();
		List<Cart> cartList=(ArrayList<Cart>)session.getAttribute("cartList");
		
		if(cartList==null){
			cart_List.add(cart);
			session.setAttribute("cartList", cart_List);
			resp.sendRedirect("index.jsp");
		}
		else{
			cart_List=cartList;
			boolean test=false;
			for(Cart c:cart_List) {
				if(c.getProduct_id()==id) {
					test=true;
					out.println("<h1 style='color:crimson; text-align:center'>Product Is Already Present In Cart<a href='cart.jsp' style='green'>Go To CartPage</a></h1>");
				}
			}
			if(!test) {
				cartList.add(cart);
				resp.sendRedirect("index.jsp");
			}
		}
	}
}
