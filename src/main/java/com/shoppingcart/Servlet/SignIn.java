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

/**
 * Servlet implementation class SignIn
 */
@WebServlet("/SignIn")
public class SignIn extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name=request.getParameter("name");
		long mobno=Long.parseLong(request.getParameter("mobNo"));
		String email=request.getParameter("email");
		String password=request.getParameter("password");
		
		Connection con;
		try {
			con = Db_connection.getConnection();
			UserDao dao=new UserDao(con) ;
			boolean flag=dao.userSignup(email, name, mobno, password);
			if(flag) {
				RequestDispatcher rd=request.getRequestDispatcher("login.jsp");
				rd.forward(request, response);
			}
			else {
				RequestDispatcher rd=request.getRequestDispatcher("SignIn.jsp");
				rd.include(request, response);
				PrintWriter out=response.getWriter();
				out.print("<section><div><form><p class=\"valid\">*Check the details again this Record is alreay present</p></form></div></section>");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
