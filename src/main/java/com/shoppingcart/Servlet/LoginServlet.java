package com.shoppingcart.Servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shoppingcart.connection.Db_connection;
import com.shoppingcart.dao.UserDao;
import com.shoppingcart.dto.User;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/user-login")
public class LoginServlet extends HttpServlet {
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		  String email=request.getParameter("email");
		  String password=request.getParameter("password");
		
		  Connection con;
			try {
				con = Db_connection.getConnection();
				UserDao dao=new UserDao(con) ;
				User user=dao.userLogin(email,password);
				request.getSession().setAttribute("auth", user);
				System.out.println(user);
				if(user==null) {
					RequestDispatcher rd=request.getRequestDispatcher("login.jsp");
					rd.include(request, response);
					PrintWriter out=response.getWriter();
					out.print("<section><div><form><p class=\"valid\">*Check the details again if you are new create a acoount first</p></form></div></section>");
					System.out.println("user null");
				}else {
					request.getSession().setAttribute("user", user);
					RequestDispatcher rd=request.getRequestDispatcher("index.jsp");
					rd.forward(request, response);
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
}
